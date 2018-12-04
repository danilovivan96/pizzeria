<%@page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
            <button type="button" class="btn btn-dark" data-toggle="modal" data-target="#exampleModal">Add new
                Comment
            </button>
            <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
                 aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Your comment:</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <form:form action="${pageContext.request.contextPath}/news/${id}/comments/add"
                                   modelAttribute="newComment"
                                   method="post">
                            <div class="modal-body">
                                <div class="form-group">
                                    <form:textarea class="form-control" id="Comment" path="content"/>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-success" data-dismiss="modal">Cancel</button>
                                <button type="submit" class="btn btn-dark">Save</button>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <a href="${pageContext.request.contextPath}/news" class="btn btn-dark">Back to news</a>
        </div>
    </div>
    <jsp:include page="util/jscripts.jsp"/>
</body>
</html>