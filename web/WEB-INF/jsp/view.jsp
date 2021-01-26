<%@ page import="com.javaops.webapp.model.TextSection" %>
<%@ page import="com.javaops.webapp.model.ListSection" %>
<%@ page import="com.javaops.webapp.model.OrganizationSection" %>
<%@ page import="com.javaops.webapp.model.Organization" %>
<%@ page import="com.javaops.webapp.util.HtmlUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="с" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <h2>${resume.fullName}&nbsp;<a href="resume?uuid=${resume.uuid}&action=edit">Edit</a></h2>
    <p>
        <c:forEach var="contactEntry" items="${resume.contacts}">
            <jsp:useBean id="contactEntry"
                         type="java.util.Map.Entry<com.javaops.webapp.model.ContactType, java.lang.String>"/>
                <%=contactEntry.getKey().toHtml(contactEntry.getValue())%><br/>
        </c:forEach>
    <p>

    <p>
    <table>
        <c:forEach var="sectionEntry" items="${resume.sections}">
        <jsp:useBean id="sectionEntry"
                     type="java.util.Map.Entry<com.javaops.webapp.model.SectionType, com.javaops.webapp.model.AbstractSection>"/>
        <с:set var="type" value="${sectionEntry.key}"/>
        <c:set var="section" value="${sectionEntry.value}"/>
        <jsp:useBean id="section" type="com.javaops.webapp.model.AbstractSection"/>
        <tr>
            <td><h2>${type.title}</h2></td>
        </tr>
        <tr>
            <c:if test="${type == 'PERSONAL' || type == 'POSITION'}">
                <td>
                    <%=((TextSection) section).getText()%>
                </td>
            </c:if>
        </tr>
        <tr>
            <c:if test="${type == 'ACHIEVEMENT' || type == 'QUALIFICATIONS'}">
                <c:forEach var="item" items="<%=((ListSection) section).getItems()%>">
                    <ul>
                        <li>
                                ${item}
                        </li>
                    </ul>
                </c:forEach>
            </c:if>
        </tr>
        <tr>
            <c:if test="${type == 'EXPERIENCE' || type == 'EDUCATION'}">
            <c:forEach var="org" items="<%=((OrganizationSection) section).getOrganizations()%>">
        <tr>
            <td>
                <c:choose>
                    <c:when test="${empty org.webSite.url}"><h3>${org.webSite.name}</h3></c:when>
                    <c:otherwise>
                        <h3><a href="${org.webSite.url}">${org.webSite.name}</a></h3>
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
        <c:forEach var="position" items="${org.positions}">
            <jsp:useBean id="position" type="com.javaops.webapp.model.Organization.Position"/>
            <tr>
                <td>
                    <%=HtmlUtil.formatDates(position)%>
                </td>
                <td>
                    <b>${position.title}</b><br>${position.description}
                </td>
            </tr>
        </c:forEach>
        </c:forEach>
        </c:if>
    </table>
    </c:forEach>
    <br>
    <button onclick="window.history.back()">Back</button>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>