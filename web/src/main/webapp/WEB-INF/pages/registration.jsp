<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="util/head.jsp"/>
    <title>Registration</title>
</head>
<body>
<div class="container-fluid">
    <div class="row ">
        <div class="col-md-12" align="center">
            <img class="img-fluid"
                 src="${pageContext.request.contextPath}/resources/img/registration.png">
        </div>
    </div>
    <div class="row">
        <div class="col-md-2"></div>
        <div class="col-md-3">
            <form:form action="${pageContext.request.contextPath}/registration" method="post" modelAttribute="user">
                <div class="form-group">
                    <form:label path="name">Name:</form:label>
                    <form:errors path="name" cssClass="error"/>
                    <form:input path="name" class="form-control"/>
                </div>
                <div class="form-group">
                    <form:label path="surname">Surname:</form:label>
                    <form:errors path="surname" cssClass="error"/>
                    <form:input path="surname" class="form-control"/>
                </div>
                <div class="form-group">
                    <form:label path="login">Login:</form:label>
                    <form:errors path="login" cssClass="error"/>
                    <form:input path="login" class="form-control"/>
                </div>
                <div class="form-group">
                    <form:label path="password">Password:</form:label>
                    <form:errors path="password" cssClass="error"/>
                    <form:input path="password" class="form-control" type="password"/>
                </div>
                <button type="submit" class="btn btn-lg btn-success mt-3">Registration</button>
            </form:form>
        </div>
        <div class="col-md-2"></div>
        <div class="col-md-3">
            <h2 class="mb-3 mt-lg-5" align="center">
                Please, fill all fields
            </h2>
            <img class="img-fluid mt-4 mb-3"
                 src="${pageContext.request.contextPath}/resources/img/right_cat.png">
            <a style="float:right" class="btn btn-lg btn-success mt-5" href="${pageContext.request.contextPath}/login">To
                log in page</a>
        </div>
        <div class="col-md-2"></div>
    </div>
</div>
<jsp:include page="util/jscripts.jsp"/>
</body>
</html>