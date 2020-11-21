package com.javaops.webapp.model;

import java.util.Arrays;
import java.util.List;

public class ListSection extends Section {

    private final List<String> items;

    public ListSection(List<String> items) {
        this.items = items;
    }

    public ListSection(String... items) {
        this(Arrays.asList(items));
    }

    @Override
    public String toString() {
        return items.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ListSection that = (ListSection) o;

        return items != null ? items.equals(that.items) : that.items == null;
    }

    @Override
    public int hashCode() {
        return items != null ? items.hashCode() : 0;
    }
}
