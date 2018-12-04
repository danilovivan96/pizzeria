<%@page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="util/head.jsp"/>
    <title>Registration</title>
</head>
<body>
<h1 class="shadow-lg p-3 mb-5 bg-grey rounded bg-dark text-white" align="center">WELCOME!</h1>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-4"></div>
        <div class="col-md-4 ">
            <img class="img-fluid "
                 src="${pageContext.request.contextPath}/resources/img/pizza-maker-52557_640.jpg">
            <form:form action="${pageContext.request.contextPath}/register" method="post" modelAttribute="user">
                <div class="form-group">
                    <form:label path="name">Name:</form:label>
                    <form:input path="name" class="form-control" placeholder="Enter Name:"/>
                </div>
                <div class="form-group">
                    <form:label path="surname">Surname:</form:label>
                    <form:input path="surname" class="form-control" placeholder="Enter Surname:"/>
                </div>
                <div class="form-group">
                    <form:label path="login">Login:</form:label>
                    <form:input path="login" class="form-control" placeholder="Enter login:"/>
                </div>
                <div class="form-group">
                    <form:label path="password">Password:</form:label>
                    <form:input path="password" class="form-control" type="password" placeholder="Enter password:"/>
                </div>
                <button type="submit" class="btn btn-dark">Registrar</button>
            </form:form>
        </div>
        <div class="col-md-4"></div>
    </div>
</div>
<jsp:include page="util/jscripts.jsp"/>
</body>
</html>