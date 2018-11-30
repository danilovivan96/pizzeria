<%@page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="util/head.jsp"/>
    <title>Browse Items</title>
</head>
<body>
<h1 class="shadow-lg p-3 mb-5 bg-grey rounded bg-dark text-white" align="center">Browse Items</h1>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-4"></div>
        <div class="col-md-4">
            <form action="${pageContext.request.contextPath}/items/upload" method="post" enctype="multipart/form-data">
                <div class="custom-file">
                    <input type="file" name="file" class="custom-file-input" id="file">
                    <label class="custom-file-label" for="file">Browse items</label>
                    <input type="submit" value="Browse items" class="btn btn-dark"/>
                </div>
            </form>
        </div>
        <div class="col-md-4"></div>
    </div>
    <div class="row">
        <div class="col-md-4"></div>
        <div class="col-md-4">
            <c:if test="${not empty message}">
                <div class="alert alert-danger" role="alert" align="center">
                    <c:out value="${message}"/>
                </div>
            </c:if>
        </div>
        <div class="col-md-4"></div>
    </div>
</div>
<jsp:include page="util/jscripts.jsp"/>
</body>
</html>