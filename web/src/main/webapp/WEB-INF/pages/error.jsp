<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="util/head.jsp"/>

    <title>Error</title>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-4"></div>
        <div class="col-md-4">
            <h3 align=>Something goes wrong.</h3>
            <img class="img-fluid"
                 src="${pageContext.request.contextPath}/resources/img/error300x240.jpg"/>
            <c:choose>
            <c:when test="${not empty message}">
                <div class="alert alert-success" role="alert">
                    <c:out value="${message}"/>
                </div>
            </c:when>
            <c:otherwise>
            <h3>We make everything to resolve mistakes.</h3></div>
        </c:otherwise>
        </c:choose>
        <div class="col-md-4"></div>
    </div>
</div>
</body>
</html>
