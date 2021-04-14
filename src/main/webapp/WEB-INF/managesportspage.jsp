<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>
    <jsp:attribute name="header">
         List of BMI entries
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>
    <jsp:body>
        <h1>Hello ${sessionScope.email} </h1>
        This is a list of all Sports:
        <form method="post" action="${pageContext.request.contextPath}/fc/managesports">
            <table class="table table-striped">
                <thead>
                <th>ID</th>
                <th>Name</th>
                </thead>
                <c:forEach var="sport" items="${applicationScope.sportList}">
                    <tr>
                        <td>${sport.sportId}</td>
                        <td>${sport.sportName}</td>
                        <td>
                            <button class="btn btn-danger btn-sm" type="submit" name="delete" value="${sport.sportId}">Remove</button>
                        <td>
                            <button class="btn btn-primary btn-sm" type="submit" name="edit" value="${sport.sportId}">Edit</button>
                        </td>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <c:if test="${not empty requestScope.error}">
                <p style="font-size: large; color: red;">${requestScope.error}</p>
            </c:if>
        </form>
    </jsp:body>
</t:genericpage>
