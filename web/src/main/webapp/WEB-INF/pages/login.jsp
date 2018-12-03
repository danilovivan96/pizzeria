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
<div class="container-fluid">
    <div class="row">
        <div class="col-md-12 text-center">
            <img src="${pageContext.request.contextPath}/resources/img/welcome.jpg">
        </div>
    </div>
    <div class="row">
        <div class="col-md-2"></div>
        <div class="col-md-3">
            <div>
                <h4 class="mb-3" align="center">
                    Join us right now!
                </h4>
                <img class="img-thumbnail"
                     src="${pageContext.request.contextPath}/resources/img/pizzer.jpg">
            </div>
            <div class="pt-3">
                <a class="btn btn-success btn-block btn-lg"
                   href="${pageContext.request.contextPath}/registration">Registration</a>
            </div>
        </div>
        <div class="col-md-2 ">
            <img class="pt-5" src="${pageContext.request.contextPath}/resources/img/OR.jpg">
        </div>
        <div class="col-md-3">
            <h4 class="mb-5">
                Order your favourite pizza!
            </h4>
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
                </div>
                <div class="form-group pt-3">
                    <button type="submit" class="btn btn-lg btn-success btn-block  ">Log in</button>
                </div>
            </form>
        </div>
        <div class="col-md-2"></div>
    </div>
</div>
<jsp:include page="util/jscripts.jsp"/>
</body>
</html>