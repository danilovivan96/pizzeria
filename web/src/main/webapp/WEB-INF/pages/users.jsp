<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="util/head.jsp"/>
    <title>Users</title>
</head>
<body>
<h1 class="shadow-lg p-3 mb-5 bg-grey rounded bg-dark text-white" align="center">Users</h1>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col"></th>
                    <th scope="col">Login</th>
                    <th scope="col">Name</th>
                    <th scope="col">Surname</th>
                    <th scope="col">Role</th>
                    <th scope="col"></th>
                    <th scope="col"></th>
                    <th scope="col"></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${users}" var="user">
                    <tr <c:if test="${user.enable==false}">class="text-light bg-secondary"</c:if>>
                        <form action="/users/${user.id}/role/update">
                            <td><a href="${pageContext.request.contextPath}/users/${user.id}/updating"
                                   class="btn btn-dark">Update</a></td>
                            <td>${user.login}</td>
                            <td>${user.name}</td>
                            <td>${user.surname}</td>
                            <td><select class="custom-select" name="new_role">
                                <c:forEach items="${roles}" var="role">
                                    <c:choose>
                                        <c:when test="${role.name==user.role}">
                                            <option selected value="${role.id}">${role.name}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${role.id}">${role.name}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select></td>
                            <td>
                                <button type="submit" class="btn btn-dark">Change Role</button>
                            </td>
                            <td>
                                <c:if test="${user.enable==false}">
                                    <a href="/users/${user.id}/enable" class="btn btn-dark">Enable</a>
                                </c:if>
                                <c:if test="${user.enable==true}">
                                    <a href="/users/${user.id}/enable" class="btn btn-dark">Disable</a>
                                </c:if>
                            </td>
                            <td>
                                <a href="/users/${user.id}/delete" class="btn btn-dark">Delete</a>
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
            <nav aria-label="Navigation">
                <ul class="pagination">
                    <c:forEach var="page" begin="1" end="${pages}">
                        <li class="page-item">
                            <a class="page-link"
                               href="${pageContext.request.contextPath}/users?page=${page}" }>${page}</a>
                        </li>
                    </c:forEach>
                </ul>
            </nav>
        </div>
        <div class="col-md-4">
            <a href="${pageContext.request.contextPath}/logout" class="btn btn-dark">Log Out</a>
        </div>
    </div>
</div>
<jsp:include page="util/jscripts.jsp"/>
</body>
</html>