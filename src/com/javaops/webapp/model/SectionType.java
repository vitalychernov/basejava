package com.javaops.webapp.model;

public enum SectionType {
    POSITION("Objective"),
    PERSONAL("Personal"),
    ACHIEVEMENT("Achievement"),
    QUALIFICATIONS("Skills"),
    EXPERIENCE("Experience"),
    EDUCATION("Education");

    private final String title;

    SectionType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
