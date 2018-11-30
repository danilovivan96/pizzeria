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
<h1 class="shadow-lg p-3 mb-5 bg-grey rounded bg-dark text-white" align="center">Items</h1>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col"></th>
                    <th scope="col">Name</th>
                    <th scope="col">Description</th>
                    <th scope="col">Price</th>
                    <th scope="col">Quantity</th>
                    <th scope="col"></th>
                </tr>
                </thead>
                <c:forEach items="${items}" var="item">
                    <form action="${pageContext.request.contextPath}/orders/creating" method="post">
                        <tr>
                            <th scope="row"><input type="hidden" name="id" value="${item.id}"></th>
                            <td>${item.name}</td>
                            <td>${item.description}</td>
                            <td>${item.price}</td>
                            <td>
                                <select name="quantity">
                                    <c:forEach var="i" begin="1" end="20">
                                        value="${i}"/>

                                        <option value="${i}">${i}</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <td>
                                <button type="submit" class="btn btn-dark">Add to order</button>
                            </td>
                        </tr>
                    </form>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <div class=" row">
        <div class="col-md-4">
            <c:if test="${not empty size}">
                <form action="${pageContext.request.contextPath}orders/bucket" method="get">
                    <button type="submit" class="btn btn-dark">
                        Bucket <span class="badge badge-light">${size}</span>
                    </button>
                </form>
            </c:if>
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
            <a href="${pageContext.request.contextPath}/orders" class="btn btn-dark">Show Orders</a>
            <a href="${pageContext.request.contextPath}/news" class="btn btn-dark">Show news</a>
            <a href="${pageContext.request.contextPath}/logout" class="btn btn-dark">Log Out</a>
        </div>
    </div>
</div>
<jsp:include page="util/jscripts.jsp"/>
</body>
</html>
