<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Security WebApp</title>
    <link href="<c:url value='/assets/css/bootstrap.css' />" rel="stylesheet"></link>
    <link href="<c:url value='/assets/css/custom.css' />" rel="stylesheet"></link>
</head>

<body>

<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand">Security WebApp</a>
        </div>
        <div class="navbar-right">Dear <strong>${loggedinuser}</strong>, You are not authorized to access this page!
            <a href="<c:url value="/list" />"><button class="btn btn-default list-button">Users List</button></a>
            <a href="<c:url value="/logout" />"><button class="btn btn-default logout-button">Logout</button></a>
        </div>
    </div>
</nav>

</body>

</html>