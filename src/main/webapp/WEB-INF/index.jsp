<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>

    <jsp:attribute name="header">
         Home
    </jsp:attribute>

    <jsp:attribute name="footer">
        <c:set var="addHomeLink" value="${false}" scope="request"/>
    </jsp:attribute>

    <jsp:body>


        <div class="row">
            <div class="col-sm-4"></div>
            <div class="col-sm-4">

                <h2>BMI Calculator</h2>
                <form method="post" action="${pageContext.request.contextPath}/fc/bmiresult">
                    <div class="form-group">
                        <label for="height">Your height:</label>
                        <br>
                        <input type="text" placeholder="cm" id="height" name="height" class="form-control">
                    </div>
                    <br>
                    <br>
                    <div class="form-group">
                        <label for="weight">Your weight:</label>
                        <br>
                        <input type="text" placeholder="kg" id="weight" name="weight" class="form-control">
                    </div>
                    <br>
                    <div>
                        <label for="Male">Mand</label>
                        <input type="radio" id="Male" name="gender" value="Male">
                        <label for="Female">Kvinde</label>
                        <input type="radio" id="Female" name="gender" value="Female">
                    </div>
                    <div>

                        <label for="sport">Your primary sport</label>
                        <select name="sport" id="sport">
                            <option value="1">Football</option>
                            <option value="2">Gymnastics</option>
                            <option value="3">Yoga</option>
                            <option value="4">Badminton</option>
                        </select>
                    </div>
                    <input type="checkbox" id="hobby1" name="hobby" value="1">
                    <label for="hobby1"> I value a healthy diet</label><br>
                    <input type="checkbox" id="hobby2" name="hobby" value="2">
                    <label for="hobby2"> I have a summerhouse</label><br>
                    <input type="checkbox" id="hobby3" name="hobby" value="3">
                    <label for="hobby3"> I have a pet</label><br>
                    <br>
                    <button type="submit" class="btn btn-primary">Calculate BMI</button>
                </form>
            </div>
            <div class="col-sm-4"></div>
        </div>
        <div>
            <c:if test="${sessionScope.role == 'employee' }">
            <p style="font-size: larger">This is what you can do,
                since your are logged in as an employee</p>
            <p><a href="fc/employeepage">Employee Page</a>
                </c:if>

                <c:if test="${sessionScope.role == 'customer' }">
            <p style="font-size: larger">This is what you can do, since your
                are logged in as a customer</p>
            <p><a href="fc/customerpage">Customer Page</a>
                </c:if>
        </div>

    </jsp:body>
</t:genericpage>