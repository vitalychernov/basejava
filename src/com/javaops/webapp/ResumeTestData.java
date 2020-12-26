package com.javaops.webapp;

import com.javaops.webapp.model.ContactType;
import com.javaops.webapp.model.Resume;
import com.javaops.webapp.model.SectionType;

public class ResumeTestData {

    public final static String U1 = "uuid1";
    public final static String U2 = "uuid2";
    public final static String U3 = "uuid3";
    public final static String U4 = "uuid4";

    public static Resume R1;
    public static Resume R2;
    public static Resume R3;
    public static Resume R4;

    static {
        R1 = new Resume(U1, "Григорий Кислин");
        R2 = new Resume(U2, "Name2");
        R3 = new Resume(U3, "Name3");
        R4 = new Resume(U4, "Name4");

/*        R1.addContact(ContactType.PHONE, "+7(921) 855-0482");
        R1.addContact(ContactType.SKYPE, "grigory.kislin");
        R1.addContact(ContactType.EMAIL, "gkislin@yandex.ru");
        R1.addContact(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
        R1.addContact(ContactType.GITHUB, "https://github.com/gkislin");
        R1.addContact(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473");
        R1.addContact(ContactType.WEBSITE, "http://gkislin.ru/");

        R1.addSection(SectionType.POSITION, new TextSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям"));
        R1.addSection(SectionType.PERSONAL, new TextSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры."));
        R1.addSection(SectionType.ACHIEVEMENT, new ListSection("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.",
                "Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа."));
        R1.addSection(SectionType.QUALIFICATIONS, new ListSection("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2",
                "Родной русский, английский \"upper intermediate\""));
        R1.addSection(SectionType.EXPERIENCE, new OrganizationSection(new Organization(
                "Java Online Projects",
                "https://javaops.ru/", new Organization.Position(
                LocalDate.of(2013, 10, 1),
                "Автор проекта",
                "Создание, организация и проведение Java онлайн проектов и стажировок."))));
        R1.addSection(SectionType.EDUCATION, new OrganizationSection(new Organization(
                "Coursera",
                null, new Organization.Position(
                LocalDate.of(2013, 3, 1),
                LocalDate.of(2013, 5, 1),
                "Functional Programming Principles in Scala\" by Martin Odersky",
                null))));*/
    }

    public static void main(String[] args) {
        System.out.println(R1.getFullName());
        System.out.println(ContactType.PHONE.getTitle() + R1.getContact(ContactType.PHONE));
        System.out.println(ContactType.SKYPE.getTitle() + R1.getContact(ContactType.SKYPE));
        System.out.println(ContactType.EMAIL.getTitle() + R1.getContact(ContactType.EMAIL));
        System.out.println(ContactType.LINKEDIN.getTitle() + R1.getContact(ContactType.LINKEDIN));
        System.out.println(ContactType.GITHUB.getTitle() + R1.getContact(ContactType.GITHUB));
        System.out.println(ContactType.STACKOVERFLOW.getTitle() + R1.getContact(ContactType.STACKOVERFLOW));
        System.out.println(ContactType.WEBSITE.getTitle() + R1.getContact(ContactType.WEBSITE));

        System.out.println(SectionType.POSITION.getTitle() + "\n" + R1.getSection(SectionType.POSITION));
        System.out.println(SectionType.PERSONAL.getTitle() + "\n" + R1.getSection(SectionType.PERSONAL));
        System.out.println(SectionType.ACHIEVEMENT.getTitle() + "\n" + R1.getSection(SectionType.ACHIEVEMENT));
        System.out.println(SectionType.EXPERIENCE.getTitle() + "\n" + R1.getSection(SectionType.EXPERIENCE));
        System.out.println(SectionType.EDUCATION.getTitle() + "\n" + R1.getSection(SectionType.EDUCATION));
    }
}
