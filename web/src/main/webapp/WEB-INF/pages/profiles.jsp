<%@page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="util/head.jsp"/>
    <title>Profile</title>
</head>
<body>
<h1 class="shadow-lg p-3 mb-5 bg-grey rounded bg-dark text-white" align="center">Profile</h1>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-3"></div>
        <div class="col-md-6"><h3>Please, fill you profile for we may to deliver you orders and contact with you</h3>
        </div>
        <div class="col-md-3"></div>
    </div>
    <div class="row">
        <div class="col-md-4"></div>
        <div class="col-md-4">
            <form:form action="${pageContext.request.contextPath}/profiles/save" modelAttribute="profile" method="post">
                <div class="form-group">
                    <form:label path="address">Address:</form:label>
                    <form:errors path="address" cssClass="error"/>
                    <form:input path="address" class="form-control" placeholder="1, 110 Hezavisimosti, Minsk"/>
                </div>
                <div class="form-group">
                    <form:label path="telephone">Telephone:</form:label>
                    <form:errors path="telephone" cssClass="error"/>
                    <form:input path="telephone" class="form-control" placeholder="+375*********"/>
                </div>
                <button type="submit" class="btn btn-dark">Save profile</button>
            </form:form>
        </div>
        <div class="col-md-4"></div>
    </div>
</div>
<jsp:include page="util/jscripts.jsp"/>
</body>
</html>