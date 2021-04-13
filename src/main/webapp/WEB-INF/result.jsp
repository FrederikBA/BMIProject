<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>

    <jsp:attribute name="header">
         Resultsite for calculation of BMI
    </jsp:attribute>

    <jsp:attribute name="footer">
        <c:set var="addHomeLink" value="${true}" scope="request"/>
    </jsp:attribute>

    <jsp:body>
        <div class="row">
            <div class="col-sm-4"></div>
            <div class="col-sm-4">
                <h2>BMI Result:</h2>

                <p>
                    Your weight is: ${requestScope.weight}
                    Your height is: ${requestScope.height}
                </p>

                <p>
                    Your BMI is: ${requestScope.bmi}
                </p>

                <p>
                    This puts you in the "${requestScope.category}" category.
                </p>
            </div>
            <div class="col-sm-4"></div>
        </div>

    </jsp:body>
</t:genericpage>