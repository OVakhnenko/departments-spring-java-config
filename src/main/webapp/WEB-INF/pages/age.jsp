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

    <title>Employees</title>

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
    <form:form method="get" commandName="department" action="/login">
        <jsp:include page="header.jsp"/>
    </form:form>

    <div class="panel panel-primary">
        <div class="panel-heading">Age of emploees</div>
        <div class="panel-body">

            <div class="alert alert-info" role="alert">List of employees whose age is ...</div>
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>Employee name</th>
                    <th>Employee age</th>
                    <th>Employee type</th>
                    <th>Deparment</th>
                </tr>
                </thead>
                <tbody>
                <c:if test="${empty listEmployees}">
                    <tr>
                        <td>&lt;empty&gt;</td>
                    </tr>
                </c:if>
                <c:if test="${!empty listEmployees}">
                    <c:forEach items="${listEmployees}" var="tmpEmployee">
                        <tr>
                            <td>${tmpEmployee.name}</td>
                            <td>${tmpEmployee.age}</td>
                            <td>${tmpEmployee.typeString()}</td>
                            <td>${employee.department.name}</td>
                        </tr>
                    </c:forEach>
                </c:if>
                </tbody>
            </table>
            <c:set var="employeeID" scope="session" value="${employee.employee_id}"/>
            <div class="alert alert-info" role="alert">Query parameters</div>

            <table>
                <form:form method="POST" commandName="employee" action="/report/age/form">
                    <tr>
                        <td>
                            <label for="deptInput">Department:</label>
                            <form:select path="department.department_id" id="deptInput">
                                <form:option value="-1" label="--- Select ---"/>
                                <form:options items="${mapDepartments}"/>
                            </form:select>
                        </td>
                        <td><form:errors path="type" cssStyle="color: #ff0000;" element="div"></form:errors></td>
                    </tr>
                    <tr>
                        <td>
                            <label for="ageInput">Employee age:</label>
                            <form:input path="age" id="ageInput"></form:input>
                        </td>
                        <td><form:errors path="age" cssStyle="color: #ff0000;" element="div"></form:errors></td>
                    </tr>
                    <tr>
                        <td>
                            <label for="typeInput">Employee type:</label>
                            <form:select path="type" id="typeInput">
                                <form:option value="NONE" label="--- Select ---"/>
                                <form:options items="${typeEmployee}"/>
                            </form:select>
                        </td>
                        <td><form:errors path="type" cssStyle="color: #ff0000;" element="div"></form:errors></td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td>
                            <button type="submit" class="btn btn-info">Generate report</button>
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
