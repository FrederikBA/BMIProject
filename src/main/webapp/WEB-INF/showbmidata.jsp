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
        This is a list of all BMI entries in the database so far:

        <table class="table table-striped">
            <thead>
            <th>ID</th>
            <th>BMI</th>
            <th>Height</th>
            <th>Weight</th>
            </thead>
            <c:forEach var="bmiEntry" items="${requestScope.bmiEntryList}">
                <tr>
                    <td>${bmiEntry.id}</td>
                    <td>${bmiEntry.bmi}</td>
                    <td>${bmiEntry.weight}</td>
                    <td>${bmiEntry  .height}</td>
                </tr>
            </c:forEach>
        </table>

    </jsp:body>
</t:genericpage>
