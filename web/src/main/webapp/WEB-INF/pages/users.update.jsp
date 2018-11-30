<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="util/head.jsp"/>

    <title>Update user</title>
</head>
<body>
<h1 class="shadow-lg p-3 mb-5 bg-grey rounded bg-dark text-white" align="center">Update user</h1>
<div class="container">
    <div class="row">
        <div class="col-md-4"></div>
        <div class="col-md-4">
            <form:form action="${pageContext.request.contextPath}/users/${user.id}/update" modelAttribute="user">
                <div class="form-group">
                    <form:label path="login">Login:</form:label>
                    <form:input path="login" class="form-control" placeholder="${name}"/>
                </div>
                <div class="form-group">
                    <form:label path="surname">Unique Number:</form:label>
                    <form:input path="surname" class="form-control" placeholder="${name}"/>
                </div>
                <div class="form-group">
                    <form:label path="name">Price:</form:label>
                    <form:input path="name" class="form-control" placeholder="${name}"/>
                </div>
                <button type="submit" class="btn btn-dark">Update</button>
            </form:form>
        </div>
        <div class="col-md-4"></div>
    </div>
    <div class="row">
        <div class="col-md-4"></div>
        <div class="col-md-4">
            <c:if test="${not empty success}">
                <div class="alert alert-success" role="alert">
                    <c:out value="${success}"/>
                </div>
            </c:if>
        </div>
        <div class="col-md-4">
            <a href="${pageContext.request.contextPath}/users/${user.id}/password" class="btn btn-dark">
                Change password
            </a>
            <a href="${pageContext.request.contextPath}/users" class="btn btn-dark">
                Back to user
            </a>
        </div>
    </div>
</div>
<jsp:include page="util/jscripts.jsp"/>
</body>
</html>
