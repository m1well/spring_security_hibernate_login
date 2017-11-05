<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Security WebApp</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link href="<c:url value='/assets/css/bootstrap.css' />" rel="stylesheet"></link>
    <link href="<c:url value='/assets/css/custom.css' />" rel="stylesheet"></link>
</head>

<body>

<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand">Security WebApp</a>
        </div>
        <div class="navbar-right">Dear <strong>${loggedinuser}</strong>, welcome to this Security WebApp!
            <a href="<c:url value="/list" />"><button class="btn btn-default list-button">Users List</button></a>
            <a href="<c:url value="/logout" />"><button class="btn btn-default logout-button">Logout</button></a>
        </div>
    </div>
</nav>

<div class="container">

    <c:if test="${param.newuser != null}">
        <p class="alert alert-success alert-dismissable">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">×</a>
            <strong>Success:</strong> ${success}
        </p>
    </c:if>
    <c:if test="${param.useredited != null}">
        <p class="alert alert-success alert-dismissable">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">×</a>
            <strong>Success:</strong> ${success}
        </p>
    </c:if>
    <c:if test="${param.userdeleted != null}">
        <p class="alert alert-warning alert-dismissable">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">×</a>
            <strong>Warning:</strong> ${success}
        </p>
    </c:if>

    <div class="panel list-panel panel-primary">

        <div class="panel-heading">
            <div class="panel-title pull-left">List of Users</div>

            <sec:authorize access="hasRole('ADMIN')">
                <a href="<c:url value="/newuser" />"><button class="btn btn-default newuser-button pull-right">Add new User</button></a>
            </sec:authorize>

            <div class="clearfix"></div>

        </div>

        <div class="panel-body">

            <table class="table table-hover">
                <thead>
                <tr>
                    <th>Username</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Email</th>
                    <sec:authorize access="hasRole('ADMIN')">
                        <th width="100"></th>
                    </sec:authorize>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${appUsers}" var="user">
                    <tr>
                        <td>${user.username}</td>
                        <td>${user.firstName}</td>
                        <td>${user.lastName}</td>
                        <td>${user.email}</td>
                        <td><a href="<c:url value='/edit-user-${user.username}' />" class="btn btn-default edit-button">edit</a></td>
                        <sec:authorize access="hasRole('ADMIN')">
                            <c:choose>
                                <c:when test="${user.username == loggedinuser}">
                                    <td><a href="<c:url value='/delete-user-${user.username}' />"
                                           class="btn btn-default delete-button" disabled="true">delete</a></td>
                                </c:when>
                                <c:otherwise>
                                    <td><a href="<c:url value='/delete-user-${user.username}' />"
                                           class="btn btn-default delete-button">delete</a></td>
                                </c:otherwise>
                            </c:choose>
                        </sec:authorize>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

        </div>

    </div>
</div>
</body>
</html>