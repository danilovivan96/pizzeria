<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="util/head.jsp"/>
    <title>Bucket</title>
</head>

<body>
<h1 class="shadow-lg p-3 mb-5 bg-grey rounded bg-dark text-white" align="center">Bucket</h1>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col"></th>
                    <th scope="col">Item</th>
                    <th scope="col">Quantity</th>
                    <th scope="col">Price</th>
                    <th scope="col"></th>
                </tr>
                </thead>
                <c:forEach items="${bucket.positions}" var="position">
                    <form action="${pageContext.request.contextPath}/orders/${bucket.id}/bucket/remove" method="post">
                        <tr>
                            <th scope="row"><input type="hidden" name="id" value="${position.id}"></th>
                            <td>${position.name}</td>
                            <td>${position.quantity}</td>
                            <td>${position.price}</td>
                            <td>
                                <button type="submit" class="btn btn-dark">Remove</button>
                            </td>
                        </tr>
                    </form>
                </c:forEach>
                </tbody>
                <tfoot>
                <tr>
                    <th></th>
                    <th></th>
                    <th>TOTAL PRICE:</th>
                    <th>${bucket.cost}</th>
                </tr>
                </tfoot>
            </table>
        </div>
    </div>
    <div class=" row">
        <div class="col-md-4"></div>
        <div class="col-md-4"></div>
        <div class="col-md-4">
            <a href="${pageContext.request.contextPath}/orders/${bucket.id}/save" class="btn btn-dark">
                Save Order
            </a>
        </div>
    </div>
</div>
<jsp:include page="util/jscripts.jsp"/>
</body>
</html>
