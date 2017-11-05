<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Security WebApp</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link href="<c:url value='/assets/css/bootstrap.css' />" rel="stylesheet"></link>
    <link href="<c:url value='/assets/css/custom.css' />" rel="stylesheet"></link>
</head>

<body>

<div class="container">

    <div class="panel login-panel panel-primary">

        <div class="panel-heading">
            <div class="form-signin-heading">WebApp Login</div>
        </div>

        <div class="panel-body">

            <form class="form-signin" action="${loginUrl}" method="post">

                <c:if test="${param.error != null}">
                    <p class="alert alert-danger alert-dismissable">
                        <a href="#" class="close" data-dismiss="alert" aria-label="close">×</a>
                        <strong>Error:</strong> Invalid username and password!
                    </p>
                </c:if>

                <c:if test="${param.logout != null}">
                    <p class="alert alert-success alert-dismissable">
                        <a href="#" class="close" data-dismiss="alert" aria-label="close">×</a>
                        <strong>Succes:</strong> You have been logged out successfully!
                    </p>
                </c:if>

                <input type="text" id="username" name="username" class="form-control" placeholder="Enter Username here" required=""
                       autofocus="">

                <input type="password" id="password" name="password" class="form-control" placeholder="Enter Password here" required="">

                <div class="checkbox">
                    <label>
                        <input type="checkbox" id="rememberme" name="rememberme" value="remember-me"> Remember me
                    </label>
                </div>

                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>

            </form>

        </div>

    </div>

</div>

</body>

</html>