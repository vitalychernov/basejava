<%@ page import="com.javaops.webapp.model.TextSection" %>
<%@ page import="com.javaops.webapp.model.ListSection" %>
<%@ page import="com.javaops.webapp.model.OrganizationSection" %>
<%@ page import="com.javaops.webapp.util.HtmlUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <jsp:useBean id="resume" type="com.javaops.webapp.model.Resume" scope="request"/>
    <title>Резюме ${resume.fullName}</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <h2>${resume.fullName}&nbsp;<a href="resume?uuid=${resume.uuid}&action=edit"><img src="img/pencil.png"
                                                                                      alt="Edit"></a></h2>
    <p>
        <c:forEach var="contactEntry" items="${resume.contacts}">
            <jsp:useBean id="contactEntry"
                         type="java.util.Map.Entry<com.javaops.webapp.model.ContactType, java.lang.String>"/>
                <%=contactEntry.getKey().toHtml(contactEntry.getValue())%><br/>
        </c:forEach>
    <p>

    <table>
        <c:forEach var="sectionEntry" items="${resume.sections}">
            <jsp:useBean id="sectionEntry"
                         type="java.util.Map.Entry<com.javaops.webapp.model.SectionType, com.javaops.webapp.model.AbstractSection>"/>
            <c:set var="type" value="${sectionEntry.key}"/>
            <c:set var="section" value="${sectionEntry.value}"/>
            <jsp:useBean id="section" type="com.javaops.webapp.model.AbstractSection"/>
            <tr>
                <td><h3>${type.title}</h3></td>
            </tr>
            <tr>
                <c:if test="${type == 'POSITION' || type == 'PERSONAL'}">
                    <td>
                        <%=((TextSection) section).getText()%>
                    </td>
                </c:if>
            </tr>
            <c:if test="${type == 'ACHIEVEMENT' || type == 'QUALIFICATIONS'}">
                <tr>
                    <td>
                        <ul>
                            <c:forEach var="item" items="<%=((ListSection) section).getItems()%>">
                                <li>${item}</li>
                            </c:forEach>
                        </ul>
                    </td>
                </tr>
            </c:if>
            <c:if test="${type == 'EXPERIENCE' || type == 'EDUCATION'}">
                <c:forEach var="organization" items="<%=((OrganizationSection) section).getOrganizations()%>">
                    <tr>
                        <td>
                            <c:choose>
                                <c:when test="${empty organization.webSite.url}">
                                    <b>${organization.webSite.name}</b>
                                </c:when>
                                <c:otherwise>
                                    <b><a href="${organization.webSite.url}">${organization.webSite.name}</a></b>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                    <c:forEach var="position" items="${organization.positions}">
                        <jsp:useBean id="position" type="com.javaops.webapp.model.Organization.Position"/>
                        <tr>
                            <td>
                                <%=HtmlUtil.formatDates(position)%>
                            </td>
                        </tr>
                        <tr>
                            <td><b>${position.title}</b></td>
                        </tr>
                        <tr>
                            <td>${position.description}</td>
                        </tr>
                    </c:forEach>
                </c:forEach>
            </c:if>
        </c:forEach>
    </table>
    <br/>
    <button onclick="window.history.back()">Назад</button>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>