<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>
    <jsp:attribute name="header">
         Demo Page for Employee Roles
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>
    <jsp:body>
        <h1>Hello ${sessionScope.email} </h1>
        You are now logged in as a EMPLOYEE of our wonderful site.

        <p><a href="${pageContext.request.contextPath}/fc/bmientries">Proceed to the BMI data page</a></p>
        <p><a href="${pageContext.request.contextPath}/fc/managesports">Proceed to the sports managing page</a></p>


    </jsp:body>
</t:genericpage>
