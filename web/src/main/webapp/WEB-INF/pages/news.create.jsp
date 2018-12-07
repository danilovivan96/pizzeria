<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="util/head.jsp"/>
    <title>Add News</title>
</head>
<body>
<h1 class="shadow-lg p-3 mb-5 bg-grey rounded bg-dark text-white" align="center">Create News</h1>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-4"></div>
        <div class="col-md-4">
            <form:form action="${pageContext.request.contextPath}/news/create" modelAttribute="news" method="post">
                <div class="form-group">
                    <form:label path="tittle">Tittle:</form:label>
                    <form:errors path="tittle" cssClass="error"/>
                    <form:input path="tittle" class="form-control" placeholder="Tittle:"/>
                </div>
                <div class="form-group">
                    <form:label path="content">Content:</form:label>
                    <form:errors path="content" cssClass="error"/>
                    <form:input path="content" class="form-control" placeholder="Content:"/>
                </div>
                <button type="submit" class="btn btn-lg btn-block btn-success">Create News</button>
            </form:form>
        </div>
        <div class="col-md-4"></div>
    </div>
</div>
<jsp:include page="util/jscripts.jsp"/>
</body>
</html>