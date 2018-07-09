<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<!DOCTYPE html>
<html lang="en">
<head>
</head>
<body>
<div class="panel panel-default">
    <div class="panel-body">
        <a href="https://github.com/ovakhnenko/departments-spring" target="_blank" class="text-left">
            <img src="/resources/github.png" width="35">
        </a>
        <sec:authorize var="loggedIn" access="isAuthenticated()"/>
        <c:if test="${not loggedIn}">
            <button formaction="/registration" class="btn btn btn-info" style="float: right">
                <span class="glyphicon glyphicon-leaf"></span>
                &nbsp;Registration&nbsp;</button>
            <button formaction="/login" style="float: right" type="submit" class="btn btn-info">
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <span class="glyphicon glyphicon-ok"></span>
                &nbsp;Login&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            </button>
        </c:if>
        <c:if test="${loggedIn}">
            <sec:authentication var="username" property="principal.username"/>
            <button formaction="/logout" type="submit" class="btn btn-danger" style="float: right">
                <span class="glyphicon glyphicon-off"></span>
                &nbsp;Logout ${username}
            </button>
        </c:if>
    </div>
</div>
<br>
</body>
</html>
