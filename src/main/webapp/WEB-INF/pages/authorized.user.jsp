<%@ page import="com.vakhnenko.departments.entity.Variables" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Java EE, Spring (MVC, Security, JPA), Hibernate">
    <meta name="author" content="http://vakhnenko.com">

    <meta property="og:title" content="Departments">
    <meta property="og:site_name" content="Departments">
    <meta property="og:url" content="http://app2.departments.pp.ua">
    <meta property="og:description" content="Java EE, Spring (MVC, Security, JPA), Hibernate">
    <meta property="og:image" content="http://vakhnenko.com/java.png">

    <title>Authorized user page</title>

    <!-- Bootstrap core CSS -->
    <spring:url value="/resources/css/bootstrap.css" var="bootstrap"/>
    <link href="${bootstrap}" rel="stylesheet"/>

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->

    <!-- Favicon -->
    <link rel="icon" type="image/x-icon" href="/resources/ico/favicon.ico">
</head>

<body>
<div class="container">
    <h1>Departments</h1>

    <!-- Header -->
    <form:form method="get" action="/login">
        <jsp:include page="header.jsp"/>
    </form:form>

    <!-- Table of context -->
    <form:form action="/authorized/user" method="post" modelAttribute="loggedUser">
        <div class="panel panel-primary">
            <div class="panel-heading">Authorized user page for "${loggedUser.username}".</div>
            <div class="panel-body">
                <div class="alert alert-success" role="alert">This is the page for the authorized user.
                    You can change the password:
                </div>

                <div class="panel panel-default">
                    <div class="panel-body">
                        Session:<br>
                        id: ${sessionParams["session_id"]}<br>
                        new: ${sessionParams["session_is_new"]}<br>
                        creation date: ${sessionParams["session_creation_date"]}<br>
                        last access date: ${sessionParams["session_last_access_date"]}<br>
                        counter: ${sessionParams["counter_session"]}<br>
                        <br>
                        Cookies:<br>
                        <c:set var="cookiesUsername" value="<%=Variables.cookiesUsername%>"/>
                        username cookie: ${cookiesUsername}<br>
                        usrname value: ${cookiesParams[cookiesUsername]}<br>
                        <c:set var="cookiesParrword" value="<%=Variables.cookiesParrword%>"/>
                        password cookie: ${cookiesParrword}<br>
                        password value: ${cookiesParams[cookiesParrword]}<br>
                        <c:set var="cookiesCounter" value="<%=Variables.cookiesCounter%>"/>
                        counter cookie: ${cookiesCounter}<br>
                        counter value: ${cookiesParams[cookiesCounter]}<br>
                    </div>
                </div>

                <c:choose>
                    <c:when test="${not empty noChangeMessage}">
                        <div class="alert alert-warning">
                            <strong>Warning!</strong> ${noChangeMessage}
                        </div>
                    </c:when>
                    <c:otherwise>
                        <spring:bind path="password">
                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                <form:input type="password" path="password" class="form-control"
                                            placeholder="Password" autofocus="true"></form:input>
                                <form:errors path="password" cssStyle="color: red"></form:errors>
                            </div>
                        </spring:bind>
                        <spring:bind path="confirmPassword">
                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                <form:input type="password" path="confirmPassword" class="form-control"
                                            placeholder="Confirm your password"></form:input>
                                <form:errors path="confirmPassword" cssStyle="color: red"></form:errors>
                            </div>
                        </spring:bind>
                    </c:otherwise>
                </c:choose>

                <c:if test="${empty noChangeMessage}">
                    <button type="submit" class="btn btn-info">Change password</button>
                </c:if>
                <button formaction="/departments" class="btn btn-info">Departments</button>
            </div>
        </div>
    </form:form>
</div>
</body>
</html>
