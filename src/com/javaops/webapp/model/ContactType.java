package com.javaops.webapp.model;

public enum ContactType {
    PHONE("Phone"),
    SKYPE("Skype"),
    EMAIL("Email"),
    LINKEDIN("LinkedIn"),
    GITHUB("Github"),
    STACKOVERFLOW("Stackoverflow"),
    PERSONALSITE("Personal Site");

    private final String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}

