<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
        <div class="navbar-right">Dear <strong>${loggedinuser}</strong>, welcome to this Security WebApp!
            <a href="<c:url value="/list" />"><button class="btn btn-default list-button">Users List</button></a>
            <a href="<c:url value="/logout" />"><button class="btn btn-default logout-button">Logout</button></a>
        </div>
    </div>
</nav>

<div class="container">

    <div class="panel list-panel panel-primary">

        <div class="panel-heading">
            <div class="panel-title pull-left">User Registration Form</div>

            <div class="clearfix"></div>

        </div>

        <div class="panel-body">

            <form:form method="POST" modelAttribute="appUser" class="form-horizontal">
                <form:input type="hidden" path="id" id="id"/>

                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-4 control-lable" for="username">Username</label>
                        <div class="col-md-8">
                            <c:choose>
                                <c:when test="${edit}">
                                    <form:input type="text" path="username" id="username" class="form-control input-sm"
                                                readonly="true"/>
                                </c:when>
                                <c:otherwise>
                                    <form:input type="text" path="username" id="username" class="form-control input-sm"/>
                                    <div class="has-error">
                                        <form:errors path="username" class="help-inline"/>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-4 control-lable" for="firstName">First Name</label>
                        <div class="col-md-8">
                            <form:input type="text" path="firstName" id="firstName" class="form-control input-sm"/>
                            <div class="has-error">
                                <form:errors path="firstName" class="help-inline"/>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-4 control-lable" for="lastName">Last Name</label>
                        <div class="col-md-8">
                            <form:input type="text" path="lastName" id="lastName" class="form-control input-sm"/>
                            <div class="has-error">
                                <form:errors path="lastName" class="help-inline"/>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-4 control-lable" for="password">Password</label>
                        <div class="col-md-8">
                            <form:input type="password" path="password" id="password" class="form-control input-sm"/>
                            <div class="has-error">
                                <form:errors path="password" class="help-inline"/>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-4 control-lable" for="email">Email</label>
                        <div class="col-md-8">
                            <form:input type="text" path="email" id="email" class="form-control input-sm"/>
                            <div class="has-error">
                                <form:errors path="email" class="help-inline"/>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-4 control-lable" for="appAuthorities">Authorities</label>
                        <div class="col-md-8">
                            <c:choose>
                                <c:when test="${enableAuthorityList}">
                                    <form:select path="appAuthorities" items="${appAuthorities}" multiple="true" itemValue="id"
                                                 itemLabel="type" class="form-control input-sm"/>
                                    <div class="has-error">
                                        <form:errors path="appAuthorities" class="help-inline"/>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <form:select path="appAuthorities" items="${appAuthorities}" multiple="true" itemValue="id"
                                                 itemLabel="type" class="form-control input-sm" readonly="true"/>
                                    <div class="has-error">
                                        <form:errors path="appAuthorities" class="help-inline"/>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group">
                        <c:choose>
                            <c:when test="${edit}">
                                <div class="col-md-12">
                                    <input type="submit" value="Update" class="btn btn-default edit-button"/>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="col-md-12">
                                    <input type="submit" value="Register" class="btn btn-default edit-button"/>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>

            </form:form>

        </div>

    </div>

</div>

</body>
</html>