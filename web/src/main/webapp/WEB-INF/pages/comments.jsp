<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="util/head.jsp"/>
    <title>Comments</title>
</head>
<body>
<h1 class="shadow-lg p-3 mb-5 bg-grey rounded bg-dark text-white" align="center">Comments</h1>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col"></th>
                    <th scope="col">Author</th>
                    <th scope="col">Text</th>
                    <th scope="col">Created</th>
                    <th scope="col"></th>
                </tr>
                </thead>
                <c:forEach items="${comments}" var="comment">
                    <tr>
                        <td></td>
                        <td>${comment.user}</td>
                        <td>${comment.content}</td>
                        <td>${comment.created}</td>
                        <td>
                            <security:authorize access="hasAuthority('SALE_USER_PERMISSION')">
                                <a href="${pageContext.request.contextPath}/news/comments/${comment.id}/delete"
                                   class="btn btn-dark">Delete</a>
                            </security:authorize>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <div class="row">
        <c:if test="${not empty message}">
            <div class="alert alert-success" role="alert" align="center">
                <c:out value="${message}"/>
            </div>
        </c:if>
        <div class="col-md-4"></div>
        <div class="col-md-4">
            <security:authorize access="hasAuthority('CUSTOMER_PERMISSION')">
                <form:form action="${pageContext.request.contextPath}/news/${news}/comments/add" modelAttribute="newComment">
                    <div class="form-group">
                        <form:label path="content">Add your comment:</form:label>
                        <form:input path="content" class="form-control"/>
                    </div>
                    <button type="submit" class="btn btn-dark">Add comment</button>
                </form:form>
            </security:authorize>
        </div>
        <div class="col-md-4">
            <a href="${pageContext.request.contextPath}/news" class="btn btn-dark">Back to news</a>
        </div>
    </div>
    <jsp:include page="util/jscripts.jsp"/>
</body>
</html>