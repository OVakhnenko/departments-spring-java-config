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

    <!-- Table of context -->
    <div class="panel panel-primary">
        <div class="panel-heading">List of Employees</div>
        <div class="panel-body">

            <div class="alert alert-info" role="alert">List of employees of the department ${department.name}</div>

            <table class="table table-striped">
                <thead>
                <tr>
                    <th>Name</th>
                    <th>Age</th>
                    <th>Type</th>
                    <th width="60">Edit</th>
                    <th width="60">Delete</th>
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
                            <td>
                                <a href="/department/${department.department_id}/employee/${tmpEmployee.employee_id}">${tmpEmployee.name}</a>
                            </td>
                            <td>${tmpEmployee.age}</td>
                            <td>${tmpEmployee.type}</td>
                            <td>
                                <a href="<c:url value='/edit/department/${department.department_id}/employee/${tmpEmployee.employee_id}'/>"><span
                                        class="glyphicon glyphicon-edit"></span></a></td>
                            <td>
                                <a href="<c:url value='/remove/department/${department.department_id}/employee/${tmpEmployee.employee_id}'/>"><span
                                        class="glyphicon glyphicon-remove-sign"></span></a></td>
                        </tr>
                    </c:forEach>
                </c:if>
                </tbody>
            </table>
            <c:set var="employeeID" scope="session" value="${employee.employee_id}"/>
            <c:if test="${employeeID == 0}">
                <div class="alert alert-info" role="alert">New Employee</div>
            </c:if>
            <c:if test="${employeeID > 0}">
                <div class="alert alert-info" role="alert">Edit Employee</div>
            </c:if>

            <table>
                <c:url var="formAction"
                       value="/add/department/${department.department_id}/employee/${employee.employee_id}"/>
                <form:form method="POST" commandName="employee" action="${formAction}">
                    <tr>
                        <td>
                            <label for="idDepInput">Department id:</label>
                            <form:input path="department.department_id" id="idDepInput" readonly="true"
                                        disabled="true"></form:input>
                            <form:hidden path="department.department_id"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="nameDepInput">Department name:</label>
                            <form:input path="department.name" id="nameDepInput" readonly="true"
                                        disabled="true"></form:input>
                            <form:hidden path="department.name"/>
                        </td>
                    </tr>
                    <c:if test="${employee.employee_id > 0}">
                        <tr>
                            <td>
                                <label for="idEmpInput">Employee id:</label>
                                <form:input path="employee_id" id="idEmpInput" readonly="true"
                                            disabled="true"></form:input>
                                <form:hidden path="employee_id"/>
                            </td>
                        </tr>
                        <tr>
                    </c:if>
                    <tr>
                        <td>
                            <label for="nameInput">Employee name:</label>
                            <form:input path="name" id="nameInput"></form:input>
                        </td>
                        <td><form:errors path="name" cssStyle="color: #ff0000;" element="div"></form:errors></td>
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
                        <td>
                            <label for="languageInput">Employee language:</label>
                            <form:input path="language" id="languageInput"></form:input>
                        </td>
                        <td><form:errors path="language" cssStyle="color: #ff0000;" element="div"></form:errors></td>
                    </tr>
                    <tr>
                        <td>
                            <label for="methodologyInput">Employee methodology:</label>
                            <form:input path="methodology" id="methodologyInput"></form:input>
                        </td>
                        <td><form:errors path="methodology" cssStyle="color: #ff0000;" element="div"></form:errors></td>
                    </tr>
                    <td>&nbsp;</td>
                    <tr>
                        <td>
                            <c:if test="${employeeID == 0}">
                                <button type="submit" class="btn btn-info">Add employee</button>
                                <button formaction="/departments" type="submit" class="btn btn-info">Departments
                                </button>
                            </c:if>
                            <c:if test="${employeeID > 0}">
                                <button type="submit" class="btn btn-info">Update employee</button>
                                <button formaction="/cancel/department/${department.department_id}/employee"
                                        type="submit"
                                        class="btn btn-info">Cancel
                                </button>
                            </c:if>
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
