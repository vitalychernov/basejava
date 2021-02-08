package com.javaops.webapp.web;

import com.javaops.webapp.Config;
import com.javaops.webapp.model.*;
import com.javaops.webapp.storage.Storage;
import com.javaops.webapp.util.DateUtil;
import com.javaops.webapp.util.HtmlUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ResumeServlet extends HttpServlet {

    private Storage storage; // = Config.get().getStorage();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        storage = Config.get().getStorage();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String uuid = request.getParameter("uuid");
        String fullName = request.getParameter("fullName");

        final boolean isCreate = (uuid == null || uuid.length() == 0);
        Resume resume;
        if (isCreate) {
            resume = new Resume(fullName);
        } else {
            resume = storage.get(uuid);
            resume.setFullName(fullName);
        }

        for (ContactType type : ContactType.values()) {
            String value = request.getParameter(type.name());
            if (HtmlUtil.isEmpty(value)) {
                resume.getContacts().remove(type);
            } else {
                resume.setContact(type, value);
            }
        }
        for (SectionType type : SectionType.values()) {
            String value = request.getParameter(type.name());
            String[] values = request.getParameterValues(type.name());

            String notNullValue = value.replaceAll("\resume", "");
            List<String> notNullValues = new ArrayList<>();
            for (String s : notNullValue.split("\n")) {
                if (s.trim().length() > 0) {
                    notNullValues.add(s);
                }
            }

            if (HtmlUtil.isEmpty(value) && values.length < 2) {
                resume.getSections().remove(type);
            }
            switch (type) {
                case POSITION, PERSONAL -> resume.setSection(type, new TextSection(value));
                case ACHIEVEMENT, QUALIFICATIONS -> resume.setSection(type, new ListSection(notNullValues));
                case EDUCATION, EXPERIENCE -> {
                    List<Organization> orgs = new ArrayList<>();
                    String[] urls = request.getParameterValues(type.name() + "url");
                    for (int i = 0; i < values.length; i++) {
                        String name = values[i];
                        if (!HtmlUtil.isEmpty(name)) {
                            List<Organization.Position> positions = new ArrayList<>();
                            String pfx = type.name() + i;
                            String[] startDates = request.getParameterValues(pfx + "startDate");
                            String[] endDates = request.getParameterValues(pfx + "endDate");
                            String[] titles = request.getParameterValues(pfx + "title");
                            String[] descriptions = request.getParameterValues(pfx + "description");
                            for (int j = 0; j < titles.length; j++) {
                                if (!HtmlUtil.isEmpty(titles[j])) {
                                    positions.add(new Organization.Position(DateUtil.parse(startDates[j]), DateUtil.parse(endDates[j]), titles[j], descriptions[j]));
                                }
                            }
                            orgs.add(new Organization(new Link(name, urls[i]), positions));
                        }
                    }
                    resume.setSection(type, new OrganizationSection(orgs));
                }
            }
        }
        if (isCreate) {
            storage.save(resume);
        } else {
            storage.update(resume);
        }
        response.sendRedirect("resume");
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String uuid = request.getParameter("uuid");
        String action = request.getParameter("action");
        if (action == null) {
            request.setAttribute("resumes", storage.getAllSorted());
            request.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(request, response);
            return;
        }
        Resume resume;
        switch (action) {
            case "delete" -> {
                storage.delete(uuid);
                response.sendRedirect("resume");
                return;
            }
            case "add" -> resume = Resume.EMPTY;
            case "view" -> resume = storage.get(uuid);
            case "edit" -> {
                resume = storage.get(uuid);
                for (SectionType type : new SectionType[]{SectionType.EXPERIENCE, SectionType.EDUCATION}) {
                    OrganizationSection section = (OrganizationSection) resume.getSection(type);
                    List<Organization> emptyFirstOrganizations = new ArrayList<>();
                    emptyFirstOrganizations.add(Organization.EMPTY);
                    if (section != null) {
                        for (Organization org : section.getOrganizations()) {
                            List<Organization.Position> emptyFirstPositions = new ArrayList<>();
                            emptyFirstPositions.add(Organization.Position.EMPTY);
                            emptyFirstPositions.addAll(org.getPositions());
                            emptyFirstOrganizations.add(new Organization(org.getWebSite(), emptyFirstPositions));
                        }
                    }
                    resume.setSection(type, new OrganizationSection(emptyFirstOrganizations));
                }
            }
            default -> throw new IllegalArgumentException("Action " + action + " is illegal");
        }
        request.setAttribute("resume", resume);
        request.getRequestDispatcher(
                ("view".equals(action) ? "/WEB-INF/jsp/view.jsp" : "/WEB-INF/jsp/edit.jsp")
        ).forward(request, response);
    }
}