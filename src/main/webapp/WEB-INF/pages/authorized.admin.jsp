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

    <title>Authorized admin page</title>

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
    <div class="panel panel-primary">
        <div class="panel-heading">Admin page</div>
        <div class="panel-body">

            <c:choose>
                <c:when test="${not empty ChangedMessage}">
                    <div class="alert alert-success">${ChangedMessage}
                    </div>
                </c:when>
                <c:when test="${not empty noDeleteMessage}">
                    <div class="alert alert-warning">${noDeleteMessage}
                    </div>
                </c:when>
                <c:when test="${not empty DeletedMessage}">
                    <div class="alert alert-success">${DeletedMessage}
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="alert alert-success" role="alert">This is the page for the admin only!
                    </div>
                </c:otherwise>
            </c:choose>

            <form:form method="POST" commandName="user" action="/departments">
                <!-- Table of context -->
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Username</th>
                        <th>Password</th>
                        <th width="60">Change</th>
                        <th width="60">Delete</th>
                    </tr>
                    </thead>
                    <c:forEach items="${users}" var="username">
                        <tr>
                            <td>${username.id}</td>
                            <td>${username.username}</td>
                            <td>${username.password}</td>

                            <td><a href="<c:url value='/authorized/admin/change/user/${username.id}'/>"><span
                                    class="glyphicon glyphicon-edit"></span></a></td>
                            <td><a href="<c:url value='/authorized/admin/delete/user/${username.id}'/>"><span
                                    class="glyphicon glyphicon-remove-sign" style="color: red"></span></a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

                <button formaction="/departments" type="submit" class="btn btn-info">Departments</button>
            </form:form>
        </div>
    </div>
</div>
</body>
</html>
