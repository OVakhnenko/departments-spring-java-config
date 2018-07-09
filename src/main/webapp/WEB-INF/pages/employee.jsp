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

    <title>Employee details</title>

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
    <form:form method="get" commandName="department" action="/login">
        <jsp:include page="header.jsp"/>
    </form:form>

    <!-- Table of context -->
    <div class="panel panel-primary">
        <div class="panel-heading">Employee</div>
        <div class="panel-body">
            <div class="alert alert-info" role="alert">Employee ${employee.name}</div>
            <table>
                <c:url var="formAction" value="/department/${department.department_id}"/>
                <form:form method="POST" commandName="employee" action="${formAction}">
                    <tr>
                        <td>Employee age: ${employee.age}</td>
                    </tr>
                    <c:if test="${employee.type eq 'D'}">
                        <tr>
                            <td>Employee type: Developer</td>
                        </tr>
                        <tr>
                            <td>Employee lang: ${employee.language}</td>
                        </tr>
                    </c:if>
                    <c:if test="${employee.type eq 'M'}">
                        <tr>
                            <td>Employee type: Manager</td>
                        </tr>
                        <tr>
                            <td>Employee meth: ${employee.methodology}</td>
                        </tr>
                    </c:if>
                    <tr>
                        <td>${employee.department.name}</td>
                    </tr>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td>
                            <button type="submit" class="btn btn-info">Employees</button>
                            <button formaction="/departments" type="submit" class="btn btn-info">Departments</button>
                        </td>
                    </tr>
                    <c:if test="${not empty actionMessage}">
                        <div class="alert alert-success">
                            <strong>Success!</strong> ${actionMessage}
                        </div>
                    </c:if>
                    <c:if test="${not empty actionError}">
                        <div class="alert alert-danger">
                            <strong>Error!</strong> ${actionError}
                        </div>
                    </c:if>
                </form:form>
            </table>
        </div>
    </div>
</div>
</body>
</html>
