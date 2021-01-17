package com.javaops.webapp.storage;

import com.javaops.webapp.exception.NotExistStorageException;
import com.javaops.webapp.model.*;
import com.javaops.webapp.sql.SqlHelper;

import java.sql.*;
import java.util.*;

public class SqlStorage implements Storage {
    private final SqlHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }
        sqlHelper = new SqlHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    @Override
    public void update(Resume resume) {
        sqlHelper.transactionalExecute(connection -> {
            try (PreparedStatement ps = connection.prepareStatement("UPDATE resume SET full_name = ? WHERE uuid = ?")) {
                ps.setString(1, resume.getFullName());
                ps.setString(2, resume.getUuid());
                if (ps.executeUpdate() != 1) {
                    throw new NotExistStorageException(resume.getUuid());
                }
            }
            doDelete(resume, connection, "DELETE FROM contact WHERE resume_uuid=?");
            doDelete(resume, connection, "DELETE FROM section WHERE resume_uuid=?");
            insertContact(resume, connection);
            insertSection(resume, connection);
            return null;
        });
    }

    @Override
    public void clear() {
        sqlHelper.execute("DELETE FROM resume", ps -> {
            ps.execute();
            return null;
        });
    }

    @Override
    public void save(Resume resume) {
        sqlHelper.transactionalExecute(connection -> doSave(resume, connection));
    }

    @Override
    public Resume get(String uuid) {
        return sqlHelper.transactionalExecute(connection -> {
            Resume resume;

            try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM resume WHERE uuid=?")) {
                ps.setString(1, uuid);
                ResultSet rs = ps.executeQuery();
                if (!rs.next()) {
                    throw new NotExistStorageException(uuid);
                }
                resume = new Resume(uuid, rs.getString("full_name"));
            }
            try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM contact WHERE resume_uuid=?")) {
                ps.setString(1, uuid);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    doGet(resume, rs);
                }
            }
            try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM section WHERE resume_uuid=?")) {
                ps.setString(1, uuid);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    createSection(rs, resume);
                }
            }
            return resume;
        });
    }

    @Override
    public void delete(String uuid) {
        sqlHelper.execute("DELETE FROM resume WHERE resume.uuid = ?", ps -> {
            ps.setString(1, uuid);
            if (ps.executeUpdate() == 0) {
                throw new NotExistStorageException(uuid);
            }
            return null;
        });
    }

    @Override
    public List<Resume> getAllSorted() {
        return sqlHelper.transactionalExecute(connection -> {
            Map<String, Resume> resumes = new LinkedHashMap<>();

            try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM resume r ORDER BY full_name, uuid")) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String uuid = rs.getString("uuid");
                    resumes.put(uuid, new Resume(uuid, rs.getString("full_name")));
                }
            }
            try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM contact")) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    doGet(resumes.get(rs.getString("resume_uuid")), rs);
                }
            }
            try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM section")) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    createSection(rs, resumes.get(rs.getString("resume_uuid")));
                }
            }
            return new ArrayList<>(resumes.values());
        });
    }

    @Override
    public int size() {
        return sqlHelper.execute("SELECT count(*) FROM resume", ps -> {
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);
        });
    }

    private Object doSave(Resume resume, Connection connection) throws SQLException {
        String uuid = resume.getUuid();
        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO resume (uuid, full_name) VALUES (?,?)")) {
            ps.setString(1, uuid);
            ps.setString(2, resume.getFullName());
            ps.execute();
        }
        insertContact(resume, connection);
        insertSection(resume, connection);
        return null;
    }

    private void doGet(Resume resume, ResultSet rs) throws SQLException {
        String cType = rs.getString("type");
        String value = rs.getString("value");
        if (value != null) {
            resume.addContact(ContactType.valueOf(cType), value);
        }
    }

    private void insertContact(Resume resume, Connection connection) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO contact (resume_uuid, type, value) VALUES (?,?,?)")) {
            for (Map.Entry<ContactType, String> entry : resume.getContacts().entrySet()) {
                ps.setString(1, resume.getUuid());
                ps.setString(2, entry.getKey().name());
                ps.setString(3, entry.getValue());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private void insertSection(Resume resume, Connection connection) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO section(resume_uuid, type, value) VALUES (?,?,?)")) {
            for (Map.Entry<SectionType, AbstractSection> entry : resume.getSections().entrySet()) {
                ps.setString(1, resume.getUuid());
                ps.setString(2, entry.getKey().name());
                String value = null;
                AbstractSection section = entry.getValue();
                SectionType type = entry.getKey();
                switch (type) {
                    case POSITION, PERSONAL -> value = ((TextSection) section).getText();
                    case ACHIEVEMENT, QUALIFICATIONS -> {
                        String str = "";
                        for (String s : ((ListSection) section).getItems()) {
                            if (s.trim().length() > 0) {
                                str = str.concat(s).concat("\n");
                            }
                        }
                        value = str;
                        value = value.substring(0, value.length() - 1);
                    }
                    case EXPERIENCE, EDUCATION -> {
                    }
                }
                ps.setString(3, value);
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private void createSection(ResultSet rs, Resume resume) throws SQLException {
        AbstractSection section;
        SectionType type = SectionType.valueOf(rs.getString("type"));
        section = switch (type) {
            case POSITION, PERSONAL -> new TextSection(rs.getString("value"));
            case ACHIEVEMENT, QUALIFICATIONS -> new ListSection(new ArrayList<>(Arrays.asList(rs.getString("value").split("\n"))));
            case EXPERIENCE, EDUCATION -> new OrganizationSection();
        };
        resume.addSection(type, section);
    }

    private void doDelete(Resume resume, Connection connection, String s) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(s)) {
            ps.setString(1, resume.getUuid());
            ps.execute();
        }
    }
}