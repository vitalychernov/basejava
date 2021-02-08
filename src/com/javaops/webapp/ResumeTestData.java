package com.javaops.webapp;

import com.javaops.webapp.model.*;

import java.time.LocalDate;
import java.util.UUID;

public class ResumeTestData {

    public final static String U1 = UUID.randomUUID().toString();
    public final static String U2 = UUID.randomUUID().toString();
    public final static String U3 = UUID.randomUUID().toString();
    public final static String U4 = UUID.randomUUID().toString();

    public static Resume R1;
    public static Resume R2;
    public static Resume R3;
    public static Resume R4;

    static {
        R1 = new Resume(U1, "Vitaly Chernov");
        R2 = new Resume(U2, "Tom Smith");
        R3 = new Resume(U3, "Name1");
        R4 = new Resume(U4, "Name3");

        R1.setContact(ContactType.PHONE, "786-495-3599");
        R1.setContact(ContactType.EMAIL, "chernovitaly@gmail.com");
        R1.setContact(ContactType.LINKEDIN, "https://www.linkedin.com/in/vitaly-chernov/");
        R1.setContact(ContactType.GITHUB, "https://github.com/vitalychernov/basejava");
        R1.setSection(SectionType.POSITION, new TextSection("Java developer"));
        R1.setSection(SectionType.QUALIFICATIONS, new ListSection(
                "Java 8/11, Stream API, Collections, Multithreading",
                "Git, Maven",
                "JUnit (4-5), AssertJ",
                "Spring (JDBC, ORM, Tx, Cache, Data JPA, MVC, Security, Test), Spring Boot",
                "JPA/Hibernate",
                "Tomcat, HTTP, REST (Postman, cURL)",
                "XML, JSON (Jackson)",
                "IntelliJ IDEA",
                "SQL (Postgres, MySQL, HSQLDB, H2)",
                "Basic level: HTML+CSS+JavaScript (JQuery, Ajax, Bootstrap), JSP/Freemarker",
                "JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2",
                "Basic understanding of IP networking (routing and switching)",
                "Basic knowledge of network technologies (TCP/IP, Ethernet, LAN, WAN, etc.)",
                "Working knowledge of project management lifecycle, tools and techniques",
                "Excellent analytical, problem-solving, and decision-making skills",
                "Excellent collaboration and communication skills",
                "English – fluent, Russian – native"));
        R1.setSection(SectionType.EXPERIENCE, new OrganizationSection(new Organization(
                "Java Online Project",
                "https://javaops.ru/", new Organization.Position(
                LocalDate.of(2020, 10, 1),
                LocalDate.of(2021, 2, 1),
                "Co-development",
                "Developing Web App \"DataBase for Resumes\""))));

        R2.setContact(ContactType.PHONE, "777-777-7777");
        R2.setContact(ContactType.SKYPE, "Tom.Smith");
        R2.setContact(ContactType.EMAIL, "Tom.Smith@gmail.com");
        R2.setContact(ContactType.LINKEDIN, "https://www.linkedin.com/in/Tom.Smith");
        R2.setContact(ContactType.GITHUB, "https://github.com/Tom.Smith");
        R2.setContact(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/Tom.Smith");
        R2.setContact(ContactType.HOME_PAGE, "http://Tom.Smith.com/");

        R2.setSection(SectionType.POSITION, new TextSection("Position"));
        R2.setSection(SectionType.PERSONAL, new TextSection("Personal information"));
        R2.setSection(SectionType.ACHIEVEMENT, new ListSection("Achievement1", "Achievement2", "Achievement3"));
        R2.setSection(SectionType.QUALIFICATIONS, new ListSection("Skill1", "Skill2", "Skill3"));
        R2.setSection(SectionType.EXPERIENCE, new OrganizationSection(new Organization(
                "Organization",
                "https://organization.com/", new Organization.Position(
                LocalDate.of(2020, 1, 1),
                "Position",
                "Position description"))));
        R2.setSection(SectionType.EDUCATION, new OrganizationSection(new Organization(
                "Harvard",
                null, new Organization.Position(
                LocalDate.of(2016, 3, 1),
                LocalDate.of(2020, 5, 1),
                "Computer Since",
                null))));
    }

    public static void main(String[] args) {
        System.out.println(R1.getFullName());
        System.out.println(ContactType.PHONE.getTitle() + R1.getContact(ContactType.PHONE));
        System.out.println(ContactType.SKYPE.getTitle() + R1.getContact(ContactType.SKYPE));
        System.out.println(ContactType.EMAIL.getTitle() + R1.getContact(ContactType.EMAIL));
        System.out.println(ContactType.LINKEDIN.getTitle() + R1.getContact(ContactType.LINKEDIN));
        System.out.println(ContactType.GITHUB.getTitle() + R1.getContact(ContactType.GITHUB));
        System.out.println(ContactType.STACKOVERFLOW.getTitle() + R1.getContact(ContactType.STACKOVERFLOW));
        System.out.println(ContactType.HOME_PAGE.getTitle() + R1.getContact(ContactType.HOME_PAGE));

        System.out.println(SectionType.POSITION.getTitle() + "\n" + R1.getSection(SectionType.POSITION));
        System.out.println(SectionType.PERSONAL.getTitle() + "\n" + R1.getSection(SectionType.PERSONAL));
        System.out.println(SectionType.ACHIEVEMENT.getTitle() + "\n" + R1.getSection(SectionType.ACHIEVEMENT));
        System.out.println(SectionType.EXPERIENCE.getTitle() + "\n" + R1.getSection(SectionType.EXPERIENCE));
        System.out.println(SectionType.EDUCATION.getTitle() + "\n" + R1.getSection(SectionType.EDUCATION));
    }
}
