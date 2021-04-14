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

        <table>
            <thead>
            <th>ID</th>
            <th>Name</th>
            </thead>
            <c:forEach var="sport" items="${applicationScope.sportList}">
                <tr>
                    <td>${sport.sportId}</td>
                    <td>${sport.sportName}</td>
                </tr>
            </c:forEach>
        </table>

    </jsp:body>
</t:genericpage>
