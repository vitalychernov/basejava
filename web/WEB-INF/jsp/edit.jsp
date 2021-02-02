<%@ page import="com.javaops.webapp.model.ContactType" %>
<%@ page import="com.javaops.webapp.model.SectionType" %>
<%@ page import="com.javaops.webapp.model.ListSection" %>
<%@ page import="com.javaops.webapp.model.TextSection" %>
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
    <form method="post" action="resume" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="uuid" value="${resume.uuid}">
        <dl>
            <dt>Имя:</dt>
            <dd><input type="text" name="fullName" size=50 value="${resume.fullName}"></dd>
        </dl>
        <h3>Контакты:</h3>
        <c:forEach var="type" items="<%=ContactType.values()%>">
            <dl>
                <dt>${type.title}</dt>
                <dd><input type="text" name="${type.name()}" size=30 value="${resume.getContact(type)}"></dd>
            </dl>
        </c:forEach>
        <h3>Секции:</h3>
        <c:forEach var="type" items="<%=SectionType.values()%>">
            <c:set var="section" value="${resume.getSection(type)}"/>
            <jsp:useBean id="section" type="com.javaops.webapp.model.AbstractSection"/>
            ${type.title}
            <c:if test="${type=='POSITION' || type == 'PERSONAL'}">
                <dl>
                    <dd>
                        <input type="text" name="${type.name()}" size="100" value="<%=((TextSection) section).getText()%>">
                    </dd>
                </dl>
            </c:if>
            <c:if test="${type=='QUALIFICATIONS' || type=='ACHIEVEMENT'}">
                <dl>
                    <dd>
                        <textarea name='${type.name()}' cols=91
                                  rows=5><%=String.join("\n", ((ListSection) section).getItems())%></textarea>
                    </dd>
                </dl>
            </c:if>
        </c:forEach>
        <hr>
        <button type="submit">Сохранить</button>
        <button onclick="window.history.back()">Отменить</button>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
