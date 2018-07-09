<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Departments</title>

    <style type="text/css">
        label {
            display: block;
            width: 100px;
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
        <div class="panel-heading">List of Departments</div>
        <div class="panel-body">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>Name</th>
                    <th width="60">Edit</th>
                    <th width="60">Delete</th>
                </tr>
                </thead>
                <tbody>
                <c:if test="${empty listDepartments}">
                    <tr>
                        <td>&lt;empty&gt;</td>
                    </tr>
                </c:if>
                <c:if test="${!empty listDepartments}">
                    <c:forEach items="${listDepartments}" var="department">
                        <tr>
                            <td><a href="/department/${department.department_id}">${department.name}</a></td>
                            <td><a href="<c:url value='/edit/department/${department.department_id}'/>"><span
                                    class="glyphicon glyphicon-edit"></span></a></td>
                            <td><a href="<c:url value='/remove/department/${department.department_id}'/>"><span
                                    class="glyphicon glyphicon-remove-sign"></span></a></td>
                        </tr>
                    </c:forEach>
                </c:if>
                </tbody>
            </table>
        </div>
    </div>

    <!-- Add/Edit department -->
    <div class="panel panel-primary">
        <c:if test="${department.department_id == 0}">
            <div class="panel-heading">New Department</div>
        </c:if>
        <c:if test="${department.department_id > 0}">
            <div class="panel-heading">Edit Department</div>
        </c:if>
        <div class="panel-body">
            <table>
                <form:form method="POST" commandName="department" action="/add/department">
                    <c:if test="${department.department_id > 0}">
                        <tr>
                            <td>
                                <label for="idInput">id:</label>
                                <form:input path="department_id" id="idInput" readonly="true"
                                            disabled="true"></form:input>
                                <form:hidden path="department_id"/>
                            </td>
                        </tr>
                        <tr>
                    </c:if>
                    <tr>
                        <td>
                            <label for="nameInput">Name:</label>
                            <form:input path="name" id="nameInput"></form:input>
                        </td>
                        <td><form:errors path="name" cssStyle="color: #ff0000;" element="div"></form:errors></td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td>
                            <c:if test="${department.department_id == 0}">
                                <button type="submit" class="btn btn-info">Add department</button>
                                <button formaction="/demonstration" type="submit" class="btn btn-info">Demonstration
                                    data
                                </button>
                                <button formaction="/delete/all" type="submit" class="btn btn-info">Delete all data
                                </button>
                            </c:if>
                            <c:if test="${department.department_id > 0}">
                                <button type="submit" class="btn btn-info">Update</button>
                                <button formaction="/cancel/department" type="submit" class="btn btn-info">Cancel
                                </button>
                            </c:if>
                        </td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                    </tr>
                    <c:if test="${not empty actionMessage}">
                        <div class="alert alert-success">
                            <strong>Success!</strong> ${actionMessage}
                        </div>
                    </c:if>
                </form:form>
            </table>
        </div>
    </div>

    <!-- Authorized access -->
    <div class="panel panel-primary">
        <div class="panel-heading">Authorized access</div>
        <div class="panel-body">
            <form:form method="get" commandName="department" action="/report/all">
                <button formaction="/public" type="submit" class="btn btn-info">Public page</button>
                <button formaction="/authorized/user" type="submit" class="btn btn-info">User page</button>
                <button formaction="/authorized/admin" type="submit" class="btn btn-info">Admin page</button>
                <sec:authorize var="loggedIn" access="isAuthenticated()"/>
                <c:if test="${not loggedIn}">
                    <button formaction="/login" type="submit" class="btn btn-info">&nbsp;&nbsp;&nbsp;&nbsp;Login&nbsp;&nbsp;&nbsp;&nbsp;</button>
                </c:if>
                <c:if test="${loggedIn}">
                    <button formaction="/logout" type="submit" class="btn btn-danger">&nbsp;&nbsp;&nbsp;&nbsp;Logout&nbsp;&nbsp;&nbsp;&nbsp;</button>
                </c:if>
            </form:form>
        </div>
    </div>

    <!-- Reports -->
    <div class="panel panel-primary">
        <div class="panel-heading">Public reports</div>
        <div class="panel-body">
            <form:form method="POST" commandName="department" action="/report/all">
                <button formaction="/report/all" type="submit" class="btn btn-info">All employees</button>
                <button formaction="/report/age" type="submit" class="btn btn-info">Age of employees</button>
                <button formaction="/report/top" type="submit" class="btn btn-info">Top of employees</button>
            </form:form>
        </div>
    </div>
</div>
</body>
</html>
