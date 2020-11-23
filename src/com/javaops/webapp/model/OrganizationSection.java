package com.javaops.webapp.model;

import java.util.Arrays;
import java.util.List;

public class OrganizationSection extends AbstractSection {

    private final List<Experience> organizations;

    public OrganizationSection(List<Experience> organizations) {
        this.organizations = organizations;
    }

    public OrganizationSection(Experience... organizations) {
        this(Arrays.asList(organizations));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrganizationSection that = (OrganizationSection) o;

        return organizations != null ? organizations.equals(that.organizations) : that.organizations == null;
    }

    @Override
    public int hashCode() {
        return organizations != null ? organizations.hashCode() : 0;
    }

    public List<Experience> getOrganizations() {
        return organizations;
    }

    @Override
    public String toString() {
        return organizations.toString();
    }
}
