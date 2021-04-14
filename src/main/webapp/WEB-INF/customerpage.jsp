<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>
    <jsp:attribute name="header">
         Demo Page for Customer Roles
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>

    <jsp:body>
        <h1>Hello ${sessionScope.email} </h1>
        <p>Are you ready to calculate your bmi?</p>
        <p>Proceed to <a href="${pageContext.request.contextPath}">the front page</a> to begin.</p>
        <h3>Check your previous BMI data</h3>
        <p><a href="${pageContext.request.contextPath}/fc/mybmientries">List with previous BMI data</a></p>
        Role: ${sessionScope.role}
    </jsp:body>

</t:genericpage>

