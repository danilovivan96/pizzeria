<%@page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="util/head.jsp"/>
    <title>Password</title>
</head>
<body>
<h1 class="shadow-lg p-3 mb-5 bg-grey rounded bg-dark text-white" align="center">Change password</h1>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-4"></div>
        <div class="col-md-4">
            <form:form action="${pageContext.request.contextPath}/users/${password.user}/password/change" method="post"
                       modelAttribute="password">
                <security:authorize access="hasAuthority('CUSTOMER_PERMISSION')">
                    <div class="form-group">
                        <form:label path="oldPassword">Old password:</form:label>
                        <form:input path="oldPassword" class="form-control" type="password"/>
                    </div>
                </security:authorize>
                <div class="form-group">
                    <form:label path="newPassword">New password:</form:label>
                    <form:input path="newPassword" class="form-control" type="password"/>
                </div>
                <div class="form-group">
                    <form:label path="replicateNewPassword">Repeat password</form:label>
                    <form:input path="replicateNewPassword" class="form-control" type="password"/>
                </div>
                <button type="submit" class="btn btn-dark">Change password</button>
            </form:form>
        </div>
        <div class="col-md-4">
            <a href="${pageContext.request.contextPath}/users/${password.user}/updating" class="btn btn-dark">Back to
                user</a>
            <a href="${pageContext.request.contextPath}/users" class="btn btn-dark">Back to all users</a>
        </div>
    </div>
</div>
<jsp:include page="util/jscripts.jsp"/>
</body>
</html>