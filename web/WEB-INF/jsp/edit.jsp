<%@page import="java.lang.String" %>
<%@ page import="com.javaops.webapp.model.*" %>
<%@ page import="com.javaops.webapp.util.DateUtil" %>
<%@ page import="com.javaops.webapp.util.HtmlUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <jsp:useBean id="resume" type="com.javaops.webapp.model.Resume" scope="request"/>
    <title>Resume ${resume.fullName}</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <form method="post" action="resume" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="uuid" value="${resume.uuid}">
        <dl>
            <dt>Name:</dt>
            <dd><input type="text" name="fullName" size=50 value="${resume.fullName}"></dd>
        </dl>
        <h3>Contacts:</h3>
        <c:forEach var="type" items="<%=ContactType.values()%>">
            <dl>
                <dt>${type.title}</dt>
                <dd><input type="text" name="${type.name()}" size=50 value="${resume.getContact(type)}"></dd>
            </dl>
        </c:forEach>
        <h3>Sections:</h3>
        <c:forEach var="type" items="<%=SectionType.values()%>">
            <jsp:useBean id="type" type="com.javaops.webapp.model.SectionType"/>
            <h4>${type.title}</h4>
            <c:choose>
                <c:when test="${type=='POSITION' || type == 'PERSONAL'}">
                    <dl>
                        <dd>
                            <input type="text" name="${type.name()}" size="100"
                                   value="<%=HtmlUtil.getText(type, resume)%>"/>
                        </dd>
                    </dl>
                </c:when>
                <c:when test="${type=='QUALIFICATIONS' || type=='ACHIEVEMENT'}">
                    <dl>
                        <dd>
                        <textarea name='${type.name()}' cols=91
                                  rows=5><%=HtmlUtil.getItems(type, resume)%></textarea>
                        </dd>
                    </dl>
                </c:when>
                <c:when test="${type=='EXPERIENCE' || type=='EDUCATION'}">
                    <c:forEach var="org" items="<%=HtmlUtil.getOrganizations(type, resume)%>"
                               varStatus="counter">
                        <dl>
                            <dt>Organization name:</dt>
                            <dd><input type="text" name='${type}' size=100 value="${org.webSite.name}"></dd>
                        </dl>
                        <dl>
                            <dt>Organization website:</dt>
                            <dd><input type="text" name='${type}url' size=100 value="${org.webSite.url}"></dd>
                            </dd>
                        </dl>
                        <br>
                        <div style="margin-left: 30px">
                            <c:forEach var="pos" items="${org.positions}">
                                <jsp:useBean id="pos" type="com.javaops.webapp.model.Organization.Position"/>
                                <dl>
                                    <dt>Start Date:</dt>
                                    <dd>
                                        <input type="text" name="${type}${counter.index}startDate" size=10
                                               value="<%=DateUtil.format(pos.getStartDate())%>" placeholder="MM/yyyy">
                                    </dd>
                                </dl>
                                <dl>
                                    <dt>End Date:</dt>
                                    <dd>
                                        <input type="text" name="${type}${counter.index}endDate" size=10
                                               value="<%=DateUtil.format(pos.getEndDate())%>" placeholder="MM/yyyy">
                                </dl>
                                <dl>
                                    <dt>Position:</dt>
                                    <dd><input type="text" name='${type}${counter.index}title' size=75
                                               value="${pos.title}">
                                </dl>
                                <dl>
                                    <dt>Description:</dt>
                                    <dd><textarea name="${type}${counter.index}description" rows=5
                                                  cols=75>${pos.description}</textarea></dd>
                                </dl>
                            </c:forEach>
                        </div>
                    </c:forEach>
                </c:when>
            </c:choose>
        </c:forEach>
        <button type="submit">Submit</button>
        <button type="reset" onclick="window.history.go(-1)">Cancel</button>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>