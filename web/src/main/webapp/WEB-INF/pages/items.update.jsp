<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="util/head.jsp"/>

    <title>Update Item</title>
</head>
<body>
<h1 class="shadow-lg p-3 mb-5 bg-grey rounded bg-dark text-white" align="center">Update Item</h1>
<div class="container-fluid">
    <div class="row">

        <div class="col-md-4"></div>
        <div class="col-md-4">
            <form:form action="${pageContext.request.contextPath}/items/${id}/update" modelAttribute="item">
            <form:errors path="*" cssClass="errorblock" element="div"/>
            <div class="form-group">
                <form:label path="name">Name:</form:label>
                <form:input path="name" class="form-control" placeholder="${name}"/>
            </div>
            <div class="form-group">
                <form:label path="description">Description:</form:label>
                <form:input path="description" class="form-control" placeholder="${name}"/>
            </div>
            <div class="form-group">
                <form:label path="uniqueNum">Unique Number:</form:label>
                <form:input path="uniqueNum" class="form-control" placeholder="${name}"/>
            </div>
            <div class="form-group">
                <form:label path="price">Price:</form:label>
                <form:input path="price" class="form-control" placeholder="${name}"/>
            </div>
            <button type="submit" class="btn btn-dark">Update</button>
        </div>
        <div class="col-md-4"></div>
        </form:form>
    </div>
    <div class="row">
        <div class="col-md-4"><a href="${pageContext.request.contextPath}/items" class="btn btn-dark">Back to Items</a>
        </div>
        <div class="col-md-4">
            <c:if test="${not empty success}">
                <div class="alert alert-success" role="alert">
                    <c:out value="${success}"/>
                </div>
            </c:if>
        </div>
        <div class="col-md-4"></div>
    </div>
</div>
<jsp:include page="util/jscripts.jsp"/>
</body>
</html>
