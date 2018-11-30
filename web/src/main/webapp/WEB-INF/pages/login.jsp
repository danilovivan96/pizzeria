<%@page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="util/head.jsp"/>
    <title>Welcome!</title>
</head>
<body>
<h1 class="shadow-lg p-3 mb-5 bg-grey rounded bg-dark text-white" align="center">WELCOME!</h1>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-2"></div>
        <div class="col-md-3">
            <h6 class="shadow-lg p-3 mb-5 bg-grey rounded bg-dark text-white" align="center">
                Make registration if you're first here:
            </h6>
            <img class="img-fluid"
                 src="${pageContext.request.contextPath}/resources/img/pizza-maker-52557_640.jpg">
                <a class="btn btn-dark" href="${pageContext.request.contextPath}/registration">Registration</a>
        </div>
        <div class="col-md-2"></div>
        <div class="col-md-3">
            <h6 class="shadow-lg p-3 mb-5 bg-grey rounded bg-dark text-white" align="center">
                Log in if you have an account:
            </h6>
            <c:if test="${not empty message}">
                <div class="alert alert-success" role="alert" align="center">
                    <c:out value="${message}"/>
                </div>
            </c:if>
            <form action="${pageContext.request.contextPath}/login" methodParam="user" method="post">
                <div class="form-group">
                    <label for="Login">Login:</label>
                    <input name="login" value="${login}" class="form-control" id="Login"
                           placeholder="Enter login">
                </div>
                <div class="form-group">
                    <label for="Password">Password:</label>
                    <input type="password" name="password" value="${password}" class="form-control" id="Password"
                           placeholder="Enter Password">
                    <button type="submit" class="btn btn-dark">Log in</button>
                </div>
            </form>
        </div>
        <div class="col-md-2"></div>
    </div>
</div>
<jsp:include page="util/jscripts.jsp"/>
</body>
</html>