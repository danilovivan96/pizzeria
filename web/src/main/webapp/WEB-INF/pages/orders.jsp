<%@page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="util/head.jsp"/>
    <title>Orders</title>
</head>
<body>
<h1 class="shadow-lg p-3 mb-5 bg-grey rounded bg-dark text-white" align="center">Orders</h1>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col"></th>
                    <th scope="col">Date of creation</th>
                    <th scope="col">Total cost</th>
                    <th scope="col">Status</th>
                    <th scope="col"></th>
                    <th scope="col"></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${orders}" var="order">
                    <tr>
                        <form method="post" action="${pageContext.request.contextPath}/orders/status">
                            <th scope="row"><input type="hidden" name="id" value="${order.id}"></th>
                            <td>${order.created}</td>
                            <td>${order.cost}</td>
                            <td>
                                <security:authorize access="hasAuthority('SALE_USER_PERMISSION')">
                                    <select class="custom-select" name="new_status">
                                        <c:forEach items="${statuses}" var="status">
                                            <c:choose>
                                                <c:when test="${status==order.status}">
                                                    <option selected name="${status}"
                                                            value="${status}">${status}</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option name="${status}" value="${status}">${status}</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </select>
                                </security:authorize>
                                <security:authorize access="hasAuthority('CUSTOMER_PERMISSION')">
                                    ${order.status}
                                </security:authorize>
                            </td>
                            <td>
                                <a href="/orders/${order.id}" class="btn btn-dark">Show details</a>
                            </td>
                            <td>
                                <security:authorize access="hasAuthority('SALE_USER_PERMISSION')">
                                    <button type="submit" class="btn btn-dark">Change status</button>
                                </security:authorize>
                            </td>
                        </form>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <div class="row">
        <div class="col-md-4"></div>
        <div class="col-md-4">
            <c:if test="${pages>1}">
                <nav aria-label="Navigation">
                    <ul class="pagination">
                        <c:forEach var="page" begin="1" end="${pages}">
                            <li class="page-item">
                                <a class="page-link"
                                   href="${pageContext.request.contextPath}/orders&page=${page}">${page}</a>
                            </li>
                        </c:forEach>
                    </ul>
                </nav>
            </c:if>
        </div>
        <div class="col-md-4">
            <a href="${pageContext.request.contextPath}/items" class="btn btn-dark">Show Items</a>
            <a href="${pageContext.request.contextPath}/news" class="btn btn-dark">Show News</a>
        </div>
    </div>
    <jsp:include page="util/jscripts.jsp"/>
</body>
</html>