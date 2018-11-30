<%@page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="util/head.jsp"/>
    <title>Create item</title>
</head>
<body>
<h1 class="shadow-lg p-3 mb-5 bg-grey rounded bg-dark text-white" align="center">Create item</h1>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-4"></div>
        <div class="col-md-4">
            <form:form action="${pageContext.request.contextPath}/items/create" modelAttribute="item" method="post">
                <div class="form-group">
                    <form:label path="uniqueNum">Unique Number:</form:label>
                    <form:input path="uniqueNum" class="form-control" placeholder="Enter UniNumber:"/>
                </div>
                <div class="form-group">
                    <form:label path="name">Name:</form:label>
                    <form:input path="name" class="form-control" placeholder="Enter Name:"/>
                </div>
                <div class="form-group">
                    <form:label path="description">Name:</form:label>
                    <form:input path="description" class="form-control" placeholder="Description:"/>
                </div>
                <div class="form-group">
                    <form:label path="price">Price:</form:label>
                    <form:input path="price" class="form-control" placeholder="100.000"/>
                </div>
                <button type="submit" class="btn btn-dark">Create Item</button>
            </form:form>
        </div>
        <div class="col-md-4"></div>
    </div>
</div>
<jsp:include page="util/jscripts.jsp"/>
</body>
</html>