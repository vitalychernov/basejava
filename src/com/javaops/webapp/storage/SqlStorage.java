package com.javaops.webapp.storage;

import com.javaops.webapp.exception.NotExistStorageException;
import com.javaops.webapp.model.ContactType;
import com.javaops.webapp.model.Resume;
import com.javaops.webapp.sql.SqlHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SqlStorage implements Storage {
    private final SqlHelper sqlHelper;

    public SqlStorage(String url, String user, String password) throws SQLException {
        sqlHelper = new SqlHelper(() -> DriverManager.getConnection(url, user, password));
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
            sqlHelper.execute("DELETE FROM contact WHERE resume_uuid=?", ps -> {
                ps.setString(1, resume.getUuid());
                ps.execute();
                return null;
            });
            insertContact(resume, connection);
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
            try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM resume WHERE uuid=?");
                 PreparedStatement psContacts = connection.prepareStatement("SELECT * FROM contact WHERE resume_uuid=?")) {

                ps.setString(1, uuid);
                ResultSet rs = ps.executeQuery();
                if (!rs.next()) {
                    throw new NotExistStorageException(uuid);
                }
                Resume resume = new Resume(uuid, rs.getString("full_name"));

                psContacts.setString(1, uuid);
                ResultSet rsContacts = psContacts.executeQuery();
                while (rsContacts.next())
                    doGet(resume, rsContacts);
                return resume;
            }
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
            try (PreparedStatement psContacts = connection.prepareStatement("SELECT * FROM contact")) {
                ResultSet rsContacts = psContacts.executeQuery();
                while (rsContacts.next()) {
                    doGet(resumes.get(rsContacts.getString("resume_uuid")), rsContacts);
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
        return null;
    }

    private void doGet(Resume resume, ResultSet rsContacts) throws SQLException {
        String cType = rsContacts.getString("type");
        String value = rsContacts.getString("value");
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
}