package com.javaops.webapp.model;

import java.util.Arrays;
import java.util.List;

public class WorkExperienceEducationSection extends Section {

    private final List<Organization> organizations;

    public WorkExperienceEducationSection(List<Organization> organizations) {
        this.organizations = organizations;
    }

    public WorkExperienceEducationSection(Organization... organizations) {
        this(Arrays.asList(organizations));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WorkExperienceEducationSection that = (WorkExperienceEducationSection) o;

        return organizations != null ? organizations.equals(that.organizations) : that.organizations == null;
    }

    @Override
    public int hashCode() {
        return organizations != null ? organizations.hashCode() : 0;
    }

    public List<Organization> getOrganizations() {
        return organizations;
    }

    @Override
    public String toString() {
        return organizations.toString();
    }
}
