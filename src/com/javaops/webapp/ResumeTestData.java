package com.javaops.webapp;

import com.javaops.webapp.model.*;

import java.time.YearMonth;

public class ResumeTestData {

    public static void main(String[] args) {
        Resume resume = new Resume("Григорий Кислин");

        resume.addContact(ContactType.PHONE, "+7(921) 855-0482");
        resume.addContact(ContactType.SKYPE, "grigory.kislin");
        resume.addContact(ContactType.EMAIL, "gkislin@yandex.ru");

        resume.addSection(SectionType.POSITION, new TextSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям"));
        resume.addSection(SectionType.PERSONAL, new TextSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры."));
        resume.addSection(SectionType.ACHIEVEMENT, new ListSection(
                "С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.",
                "Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk."));
        resume.addSection(SectionType.QUALIFICATIONS, new ListSection("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2",
                "Version control: Subversion, Git, Mercury, ClearCase, Perforce"));

        resume.addSection(SectionType.EXPERIENCE, new WorkExperienceEducationSection(new Organization(
                "Java Online Projects",
                "https://javaops.ru/",
                YearMonth.of(2013, 10),
                null,
                "Автор проекта",
                "Создание, организация и проведение Java онлайн проектов и стажировок.")));
        resume.addSection(SectionType.EDUCATION, new WorkExperienceEducationSection(new Organization(
                "Coursera",
                "https://www.coursera.org/learn/progfun1",
                YearMonth.of(2013, 3),
                YearMonth.of(2013, 5),
                "Functional Programming Principles in Scala\" by Martin Odersky",
                null)));

        System.out.println(resume.getFullName());
        System.out.println(resume.getContacts());
        System.out.println(resume.getSections());

    }
}
