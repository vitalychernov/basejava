package com.javaops.webapp;

import com.javaops.webapp.model.*;

import java.time.Month;
import java.util.UUID;

public class TestData {
    public static final String UUID_1 = UUID.randomUUID().toString();
    public static final String UUID_2 = UUID.randomUUID().toString();
    public static final String UUID_3 = UUID.randomUUID().toString();
    public static final String UUID_4 = UUID.randomUUID().toString();

    public static final Resume R1;
    public static final Resume R2;
    public static final Resume R3;
    public static final Resume R4;

    static {
        R1 = new Resume(UUID_1, "Vitaly Chernov");
        R2 = new Resume(UUID_2, "Test");
        R3 = new Resume(UUID_3, "Name3");
        R4 = new Resume(UUID_4, "Name4");

        R1.setContact(ContactType.PHONE, "786-495-3599");
        R1.setContact(ContactType.EMAIL, "chernovitaly@gmail.com");
        R1.setContact(ContactType.LINKEDIN, "https://www.linkedin.com/in/vitaly-chernov/");
        R1.setContact(ContactType.GITHUB, "https://github.com/vitalychernov/basejava");
        R1.setSection(SectionType.OBJECTIVE, new TextSection("Java developer"));
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
        R1.setSection(SectionType.EXPERIENCE,
                new OrganizationSection(
                        new Organization("Acuative", "http://acuative.com",
                                new Organization.Position(2018, Month.AUGUST, "IT Project Manager", "Responsible for providing Project Management as a service as well as coordination of circuit conversions from existing Broadband/Ethernet circuits to new provided circuits in the US and abroad. Beyond ordering, management, and delivery of the circuits and network equipment I also coordinate technicians for the demarc extensions and turn up of the new circuits "))));
        R1.setSection(SectionType.EDUCATION,
                new OrganizationSection(
                        new Organization("Java Online Project", "https://javaops.ru/",
                                new Organization.Position(2021, Month.MARCH, "Co-development of Spring 5/JPA Java Enterprise Web application \"Calories management\"", "Technology stack:\n" +
                                        "Spring Security, Spring MVC, Spring Data JPA, Spring Security Test, Hibernate ORM, Hibernate Validator, SLF4J, Json Jackson, JSP, JSTL, Apache Tomcat, WebJars, DataTables plugin, EHCACHE, PostgreSQL, JUnit, Hamcrest, jQuery, jQuery notification, Bootstrap.\n" +
                                        "Java Enterprise project with registration/authorization and role-based access rights (USER, ADMIN). Admin could create/edit/delete users, users - manage your profile and data (meals) via UI (AJAX) and REST with basic authorization. Meals could be filtered by date and time. Meal record color depends on daily calories sum exceeding \"Daily calorie limit\" (editable user's profile paramets). All REST interface covered with JUnit tests by Spring MVC Test и Spring Security Test."),
                                new Organization.Position(2020, Month.OCTOBER, 2021, Month.FEBRUARY, "Co-Development of Web application \"Resume Database\"", "Technology Stack\n" +
                                        "Java 8, GitHub/Git, JUnit, Logging, JSON, XML, SQL, PostgreSQL, Servlets, HTML, JSP, JSTL, Tomcat, Maven, etc."))));

        R2.setContact(ContactType.PHONE, "777-777-7777");
        R2.setContact(ContactType.EMAIL, "test@gmail.com");
        R2.setContact(ContactType.LINKEDIN, "https://www.linkedin.com/in/test");
        R2.setContact(ContactType.GITHUB, "https://github.com/test");
        R2.setContact(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/test");
        R2.setContact(ContactType.HOME_PAGE, "http://test.com/");

        R2.setSection(SectionType.EXPERIENCE,
                new OrganizationSection(
                        new Organization("Organization", "http://Organization.com",
                                new Organization.Position(2018, Month.JANUARY, "Current position", "Job description"),
                                new Organization.Position(2017, Month.JANUARY, 2018, Month.JANUARY, "Previous position", "Job description"))));
        R2.setSection(SectionType.EDUCATION,
                new OrganizationSection(
                        new Organization("Harvard", null,
                                new Organization.Position(2013, Month.JANUARY, 2015, Month.DECEMBER, "Student", "Programming & Development Associate Degree"),
                                new Organization.Position(2015, Month.MARCH, 2017, Month.JANUARY, "Student", "Programming & Development Bachelor Degree"))));
    }
}