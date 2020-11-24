package com.javaops.webapp.model;

import java.time.YearMonth;

public class Experience {

    private final Link webSite;
    private final YearMonth startDate;
    private final YearMonth endDate;
    private final String title;
    private final String description;

    public Experience(String name, String url, YearMonth startDate, YearMonth endDate, String title, String description) {
        this.webSite = new Link(name, url);
        this.startDate = startDate;
        this.endDate = endDate;
        this.title = title;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "webSite=" + webSite +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
