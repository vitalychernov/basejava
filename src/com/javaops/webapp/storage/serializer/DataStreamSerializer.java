package com.javaops.webapp.storage.serializer;

import com.javaops.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements Serialization {

    @Override
    public void doWrite(Resume resume, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());
            Map<ContactType, String> contacts = resume.getContacts();
            writeWithException(contacts.entrySet(), dos, entry -> {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            });
            Map<SectionType, AbstractSection> sections = resume.getSections();
            writeWithException(sections.entrySet(), dos, entry -> {
                SectionType key = entry.getKey();
                AbstractSection value = entry.getValue();
                dos.writeUTF(key.name());
                switch (key) {
                    case POSITION, PERSONAL -> dos.writeUTF(((TextSection) resume.getSection(key)).getText());
                    case ACHIEVEMENT, QUALIFICATIONS -> writeWithException(((ListSection) value).getItems(), dos, dos::writeUTF);
                    case EXPERIENCE, EDUCATION -> writeWithException(((OrganizationSection) value).getOrganizations(), dos, organization -> {
                        dos.writeUTF(organization.getWebSite().getName());
                        dos.writeUTF(organization.getWebSite().getUrl());
                        writeWithException(organization.getPositions(), dos, position -> {
                            dos.writeUTF(position.getStartDate().toString());
                            dos.writeUTF(position.getEndDate().toString());
                            dos.writeUTF(position.getTitle());
                            dos.writeUTF(position.getDescription());
                        });
                    });
                }
            });
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            readWithException(dis, () -> resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF()));
            readWithException(dis, () -> {
                SectionType key = SectionType.valueOf(dis.readUTF());
                resume.addSection(key, readSection(dis, key));
            });
            return resume;
        }
    }

    private AbstractSection readSection(DataInputStream dis, SectionType key) throws IOException {
        return switch (key) {
            case POSITION, PERSONAL -> new TextSection(dis.readUTF());
            case ACHIEVEMENT, QUALIFICATIONS -> new ListSection(readList(dis, dis::readUTF));
            case EXPERIENCE, EDUCATION -> new OrganizationSection(readList(dis, () ->
                    new Organization(new Link(dis.readUTF(), dis.readUTF()), readList(dis, () ->
                            new Organization.Position(
                                    LocalDate.parse(dis.readUTF()),
                                    LocalDate.parse(dis.readUTF()),
                                    dis.readUTF(),
                                    dis.readUTF()
                            )))));
        };
    }


    private <T> void writeWithException(Collection<T> collection, DataOutputStream dos, ElementWriter<T> writer) throws IOException {
        dos.writeInt(collection.size());
        for (T item : collection) {
            writer.write(item);
        }
    }

    private interface ElementWriter<T> {
        void write(T t) throws IOException;
    }

    private void readWithException(DataInputStream dis, ElementReader reader) throws IOException {
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            reader.read();
        }
    }

    private interface ElementReader {
        void read() throws IOException;
    }

    private <T> List<T> readList(DataInputStream dis, ListReader<T> reader) throws IOException {
        List<T> list = new ArrayList<>();
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            list.add(reader.read());
        }
        return list;
    }

    private interface ListReader<T> {
        T read() throws IOException;
    }
}
