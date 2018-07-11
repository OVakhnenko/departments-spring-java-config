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
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Authorized user page</title>

    <style type="text/css">
        label {
            display: block;
            width: 180px;
        }
    </style>

    <!-- Bootstrap core CSS -->
    <spring:url value="/resources/css/bootstrap.css" var="bootstrap"/>
    <link href="${bootstrap}" rel="stylesheet"/>

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->

    <!-- Favicon -->
    <link rel="icon" type="image/x-icon" href="/resources/favicon.ico">
</head>

<body>
<div class="container">
    <h1>Departments</h1>

    <!-- Header -->
    <form:form method="get" action="/login">
        <jsp:include page="header.jsp"/>
    </form:form>

    <!-- Table of context -->
    <form:form action="/authorized/admin/change/user" method="post" modelAttribute="user">
        <div class="panel panel-primary">
            <div class="panel-heading">&nbsp;</div>
            <div class="panel-body">
                <div class="alert alert-success" role="alert">Change password for "${user.username}"</div>

                <c:choose>
                    <c:when test="${not empty noChangeMessage}">
                        <div class="alert alert-warning">
                            <strong>Warning!</strong> ${noChangeMessage}
                        </div>
                    </c:when>
                    <c:otherwise>
                        <form:input path="username" id="username" readonly="true"
                                    disabled="true" hidden="true"></form:input>
                        <form:hidden path="username"/>

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
                <button formaction="/authorized/admin" class="btn btn-info">Admin page</button>
            </div>
        </div>
    </form:form>
</div>
</body>
</html>
