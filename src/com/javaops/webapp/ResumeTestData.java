package com.javaops.webapp;

import com.javaops.webapp.model.*;

import java.time.LocalDate;

public class ResumeTestData {

    public static Resume create(String uuid, String fullName) {
        Resume resume = new Resume(uuid, fullName);

        resume.addContact(ContactType.PHONE, "+7(921) 855-0482");
        resume.addContact(ContactType.SKYPE, "grigory.kislin");
        resume.addContact(ContactType.EMAIL, "gkislin@yandex.ru");
        resume.addContact(ContactType.LINKEDIN, new Link(ContactType.LINKEDIN.getTitle(), "https://www.linkedin.com/in/gkislin"));
        resume.addContact(ContactType.GITHUB, new Link(ContactType.GITHUB.getTitle(), "https://github.com/gkislin"));
        resume.addContact(ContactType.STACKOVERFLOW, new Link(ContactType.STACKOVERFLOW.getTitle(), "https://stackoverflow.com/users/548473"));
        resume.addContact(ContactType.PERSONALSITE, new Link(ContactType.PERSONALSITE.getTitle(), "http://gkislin.ru/"));

        resume.addSection(SectionType.POSITION, new TextSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям"));
        resume.addSection(SectionType.PERSONAL, new TextSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры."));
        resume.addSection(SectionType.ACHIEVEMENT, new ListSection("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.",
                "Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа."));
        resume.addSection(SectionType.QUALIFICATIONS, new ListSection("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2",
                "Родной русский, английский \"upper intermediate\""));
        resume.addSection(SectionType.EXPERIENCE, new OrganizationSection(new Organization(
                "Java Online Projects",
                "https://javaops.ru/", new Organization.Position(
                LocalDate.of(2013, 10, 1),
                null,
                "Автор проекта",
                "Создание, организация и проведение Java онлайн проектов и стажировок."))));
        resume.addSection(SectionType.EDUCATION, new OrganizationSection(new Organization(
                "Coursera",
                "https://www.coursera.org/learn/progfun1", new Organization.Position(
                LocalDate.of(2013, 3, 1),
                LocalDate.of(2013, 5, 1),
                "Functional Programming Principles in Scala\" by Martin Odersky",
                null))));

        System.out.println(resume.getFullName());
        System.out.println(ContactType.PHONE.getTitle() + resume.getContacts(ContactType.PHONE));
        System.out.println(ContactType.SKYPE.getTitle() + resume.getContacts(ContactType.SKYPE));
        System.out.println(ContactType.EMAIL.getTitle() + resume.getContacts(ContactType.EMAIL));
        System.out.println(resume.getContacts(ContactType.LINKEDIN));
        System.out.println(resume.getContacts(ContactType.GITHUB));
        System.out.println(resume.getContacts(ContactType.STACKOVERFLOW));
        System.out.println(resume.getContacts(ContactType.PERSONALSITE));

        System.out.println(SectionType.POSITION.getTitle() + "\n" + resume.getSections(SectionType.POSITION));
        System.out.println(SectionType.PERSONAL.getTitle() + "\n" + resume.getSections(SectionType.PERSONAL));
        System.out.println(SectionType.ACHIEVEMENT.getTitle() + "\n" + resume.getSections(SectionType.ACHIEVEMENT));
        System.out.println(SectionType.EXPERIENCE.getTitle() + "\n" + resume.getSections(SectionType.EXPERIENCE));
        System.out.println(SectionType.EDUCATION.getTitle() + "\n" + resume.getSections(SectionType.EDUCATION));
        return resume;
    }

    public static void main(String[] args) {
        create("UUID_1", "Григорий Кислин");
    }
}
