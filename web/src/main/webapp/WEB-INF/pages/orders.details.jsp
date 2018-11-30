<%@page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="util/head.jsp"/>
    <title>Order</title>
</head>
<body>
<h1 class="shadow-lg p-3 mb-5 bg-grey rounded bg-dark text-white" align="center">Order Details</h1>
<div class="container">
    <div class="row">
        <div class="col-md-2"></div>
        <div class="col-md-8">
            <h4 align="center"> Items: </h4>
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">Name</th>
                    <th scope="col">Price</th>
                    <th scope="col">Quantity</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${order.positions}" var="item">
                    <tr>
                        <th>${item.name}</th>
                        <th>${item.price}</th>
                        <th>${item.quantity}</th>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="col-md-2"></div>
    </div>
    <div class="row">
        <div class="col-md-4">
            <h5>Total cost: ${order.cost}</h5>
            <h5>Created: ${order.created}</h5>
            <h5>Status: ${order.status}</h5>
        </div>
        <div class="col-md-4">
            <a href="${pageContext.request.contextPath}/items" class="btn btn-dark">Make new order</a>
        </div>
        <div class="col-md-4">
            <a href="${pageContext.request.contextPath}/orders" class="btn btn-dark">Back to order list</a>
        </div>
    </div>
</div>
<jsp:include page="util/jscripts.jsp"/>
</body>
</html>