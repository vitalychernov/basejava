package com.javaops.webapp.storage.serializer;

import com.javaops.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements Serialization {

    @Override
    public void doWrite(Resume resume, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());
            Map<ContactType, String> contacts = resume.getContacts();
            dos.writeInt(contacts.size());
            for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }
            Map<SectionType, AbstractSection> sections = resume.getSections();
            dos.writeInt(sections.size());
            for (Map.Entry<SectionType, AbstractSection> entry : sections.entrySet()) {
                SectionType key = entry.getKey();
                AbstractSection section = entry.getValue();
                switch (key) {
                    case POSITION, PERSONAL -> dos.writeUTF(((TextSection) section).getText());
                    case ACHIEVEMENT, QUALIFICATIONS -> {
                        List<String> items = ((ListSection) section).getItems();
                        dos.writeInt(items.size());
                        for (String item : items) {
                            dos.writeUTF(item);
                        }
                    }
                    case EXPERIENCE, EDUCATION -> {
                        List<Organization> organizations = ((OrganizationSection) section).getOrganizations();
                        dos.writeInt(organizations.size());
                        for (Organization organization : organizations) {
                            dos.writeUTF(organization.getWebSite().getName());
                            if (organization.getWebSite().getUrl() != null) {
                                dos.writeUTF(organization.getWebSite().getUrl());
                            } else dos.writeUTF("null");
                            List<Organization.Position> positions = organization.getPositions();
                            dos.writeInt(positions.size());
                            for (Organization.Position position : positions) {
                                dos.writeUTF(position.getStartDate().toString());
                                if (position.getEndDate() != null) {
                                    dos.writeUTF(position.getEndDate().toString());
                                } else dos.writeUTF("null");
                                dos.writeUTF(position.getTitle());
                                if (position.getDescription() != null) {
                                    dos.writeUTF(position.getDescription());
                                } else dos.writeUTF("null");
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            int contactSize = dis.readInt();
            for (int i = 0; i < contactSize; i++) {
                resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }
            int sectionSize = dis.readInt();
            for (int i = 0; i < sectionSize; i++) {
                SectionType key = SectionType.valueOf(dis.readUTF());
                switch (key) {
                    case POSITION, PERSONAL -> resume.addSection(key, new TextSection(dis.readUTF()));
                    case ACHIEVEMENT, QUALIFICATIONS -> {
                        List<String> items = new ArrayList<>();
                        int listSectionSize = dis.readInt();
                        for (int j = 0; j < listSectionSize; j++) {
                            items.add(dis.readUTF());
                        }
                        resume.addSection(key, new ListSection(items));
                    }
                    case EXPERIENCE, EDUCATION -> {
                        List<Organization> organizations = new ArrayList<>();
                        int organizationSize = dis.readInt();
                        for (int j = 0; j < organizationSize; j++) {
                            String name = dis.readUTF();
                            String url = dis.readUTF();
                            List<Organization.Position> positions = new ArrayList<>();
                            int positionSize = dis.readInt();
                            for (int k = 0; k < positionSize; k++) {
                                String description = dis.readUTF();
                                positions.add(new Organization.Position(
                                        LocalDate.parse(dis.readUTF()),
                                        dis.readUTF().equals("null") ? null : LocalDate.parse(dis.readUTF()),
                                        dis.readUTF(),
                                        dis.readUTF().equals("null") ? null : description
                                ));
                            }
                            organizations.add(new Organization(name, url, positions));
                        }
                        resume.addSection(key, new OrganizationSection(organizations));
                    }
                }
            }
            return resume;
        }
    }
}