package com.javaops.webapp;

import com.javaops.webapp.model.*;

import java.time.YearMonth;

public class ResumeTestData {

    public static void main(String[] args) {
        Resume resume = new Resume("Григорий Кислин");

        resume.addContact(ContactType.PHONE, "Тел.: +7(921) 855-0482");
        resume.addContact(ContactType.SKYPE, "Skype: grigory.kislin");
        resume.addContact(ContactType.EMAIL, "Почта: gkislin@yandex.ru");
        resume.addContact(ContactType.LINKEDIN, new Link("Профиль LinkedIn", "https://www.linkedin.com/in/gkislin"));
        resume.addContact(ContactType.GITHUB, new Link("Профиль GitHub", "https://github.com/gkislin"));
        resume.addContact(ContactType.STACKOVERFLOW, new Link("Профиль Stackoverflow", "https://stackoverflow.com/users/548473"));
        resume.addContact(ContactType.PERSONALSITE, new Link("Домашняя страница", "http://gkislin.ru/"));

        resume.addSection(SectionType.POSITION, new TextSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям"));
        resume.addSection(SectionType.PERSONAL, new TextSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры."));
        resume.addSection(SectionType.ACHIEVEMENT, new ListSection("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.",
                "Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа."));
        resume.addSection(SectionType.QUALIFICATIONS, new ListSection("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2",
                "Родной русский, английский \"upper intermediate\""));
        resume.addSection(SectionType.EXPERIENCE, new OrganizationSection(new Experience(
                "Java Online Projects",
                "https://javaops.ru/",
                YearMonth.of(2013, 10),
                null,
                "Автор проекта",
                "Создание, организация и проведение Java онлайн проектов и стажировок.")));
        resume.addSection(SectionType.EDUCATION, new OrganizationSection(new Experience(
                "Coursera",
                "https://www.coursera.org/learn/progfun1",
                YearMonth.of(2013, 3),
                YearMonth.of(2013, 5),
                "Functional Programming Principles in Scala\" by Martin Odersky",
                null)));

        System.out.println(resume.getFullName());
        System.out.println(resume.getContacts(ContactType.PHONE));
        System.out.println(resume.getContacts(ContactType.SKYPE));
        System.out.println(resume.getContacts(ContactType.EMAIL));
        System.out.println(resume.getContacts(ContactType.LINKEDIN));
        System.out.println(resume.getContacts(ContactType.GITHUB));
        System.out.println(resume.getContacts(ContactType.STACKOVERFLOW));
        System.out.println(resume.getContacts(ContactType.PERSONALSITE));

        System.out.println(resume.getSections(SectionType.POSITION));
        System.out.println(resume.getSections(SectionType.PERSONAL));
        System.out.println(resume.getSections(SectionType.ACHIEVEMENT));
        System.out.println(resume.getSections(SectionType.EXPERIENCE));
        System.out.println(resume.getSections(SectionType.EDUCATION));
    }
}
