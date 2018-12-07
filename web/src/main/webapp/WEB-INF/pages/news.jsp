<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="util/head.jsp"/>
    <title>News</title>
</head>

<body>
<h1 class="shadow-lg p-3 mb-5 bg-grey rounded bg-dark text-white" align="center">News</h1>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col"></th>
                    <th scope="col">Tittle</th>
                    <th scope="col">Content</th>
                    <th scope="col">Author</th>
                    <th scope="col">Created</th>
                    <th scope="col">#</th>
                </tr>
                </thead>
                <c:forEach items="${list}" var="news">
                    <form action="${pageContext.request.contextPath}/news/comments" method="get">
                        <tr>
                            <td>
                                <security:authorize access="hasAuthority('SALE_USER_PERMISSION')">
                                    <a href="${pageContext.request.contextPath}/news/${news.id}/delete"
                                       class="btn btn-dark">Delete</a>
                                </security:authorize>
                            </td>
                            <td>${news.tittle}</td>
                            <td>${news.content}</td>
                            <td>${news.author}</td>
                            <td>${news.created}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/news/${news.id}/comments"
                                   class="btn btn-dark"><span class="badge badge-light">&#9993 ${news.comments}</span> Show comments</a>
                            </td>
                        </tr>
                    </form>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <div class="row">
        <div class="col-md-4"></div>
        <div class="col-md-4"></div>
        <div class="col-md-4">
            <security:authorize access="hasAuthority('SALE_USER_PERMISSION')">
                <a href="${pageContext.request.contextPath}/news/add" class="btn btn-dark">Add news</a>
            </security:authorize>
            <a href="${pageContext.request.contextPath}/items" class="btn btn-dark">Show Items</a>
            <a href="${pageContext.request.contextPath}/orders" class="btn btn-dark">Show orders</a>
        </div>
    </div>
    <jsp:include page="util/jscripts.jsp"/>
</body>
</html>