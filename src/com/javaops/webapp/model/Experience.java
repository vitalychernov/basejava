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

    static class Link {
        private final String name;
        private final String url;

        public Link(String name, String url) {
            this.name = name;
            this.url = url;
        }

        public String getName() {
            return name;
        }

        public String getUrl() {
            return url;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Link link = (Link) o;

            if (name != null ? !name.equals(link.name) : link.name != null) return false;
            return url != null ? url.equals(link.url) : link.url == null;
        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + (url != null ? url.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "Link{" +
                    "name='" + name + '\'' +
                    ", url='" + url + '\'' +
                    '}';
        }
    }
}
