package com.javaops.webapp.model;

public enum SectionType {
    POSITION("Position"),
    PERSONAL("Personal"),
    OBJECTIVE("Objective"),
    ACHIEVEMENT("Achievement"),
    QUALIFICATIONS("Qualifications"),
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
