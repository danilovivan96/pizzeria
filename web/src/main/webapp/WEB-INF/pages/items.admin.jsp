<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="util/head.jsp"/>
    <title>Items</title>
</head>

<body>
<h1 class="shadow-lg p-3 mb-5 bg-grey rounded bg-dark text-white" align="center">ITEMS</h1>
<div class="container">
    <c:if test="${not empty message}">
        <div class="row">
            <div class="col-md-4"></div>
            <div class="col-md-4">
                <div class="alert alert-danger" role="alert" align="center">
                    <c:out value="${message}"/>
                </div>
            </div>
            <div class="col-md-4"><a href="${pageContext.request.contextPath}/items" class="btn btn-dark">Show Items</a>
            </div>
        </div>
    </c:if>
    <form action="${pageContext.request.contextPath}/items/delete" method="post">
        <div class="row">
            <div class="col-md-12">
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Name</th>
                        <th scope="col">Description</th>
                        <th scope="col">Price</th>
                    </tr>
                    </thead>

                    <c:forEach items="${items}" var="item">
                        <tr>
                            <th scope="row"><input type="checkbox" name="ids" value="${item.id}"></th>
                            <td>${item.name}</td>
                            <td>${item.description}</td>
                            <td>${item.price}</td>
                            <td><a href="${pageContext.request.contextPath}/items/${item.id}/updating"
                                   class="btn btn-dark">Update</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <div class=" row">
            <div class="col-md-4">
                <a href="${pageContext.request.contextPath}/items/browse" class="btn btn-dark"> Browse new Items</a>
            </div>
            <div class="col-md-4">
                <nav aria-label="Navigation">
                    <ul class="pagination">
                        <c:forEach var="page" begin="1" end="${pages}">
                            <li class="page-item">
                                <a class="page-link"
                                   href="${pageContext.request.contextPath}/items?page=${page}" }>${page}</a>
                            </li>
                        </c:forEach>
                    </ul>
                </nav>
            </div>
            <div class="col-md-4">
                <button type="submit" class="btn btn-dark">Delete Items</button>
                <a href="${pageContext.request.contextPath}/orders" class="btn btn-dark"> Show orders</a>
                <a href="${pageContext.request.contextPath}/logout" class="btn btn-dark"> Log Out</a>
            </div>
        </div>
    </form>
</div>
<jsp:include page="util/jscripts.jsp"/>
</body>
</html>
