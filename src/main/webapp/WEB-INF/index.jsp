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
                <div class="text-center">
                    <h2>BMI Calculator</h2>
                </div>
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
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" id="Male" name="gender" value="Male">
                        <label class="form-check-label" for="Male">Male</label>
                    </div>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" id="Female" name="gender" value="Female">
                        <label class="form-check-label" for="Female">Female</label>
                    </div>
                    <div class="form-group mt-3 mb-3">
                        <label class="form-check-label" for="sport">Your primary sport</label>
                        <select class="form-control" name="sport" id="sport">
                            <c:forEach var="sport" items="${applicationScope.sportList}">
                                <option value="${sport.sportId}">${sport.sportName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" id="hobby1" name="hobby" value="1">
                        <label class="form-check-label" for="hobby1"> I value a healthy diet</label><br>
                        <input class="form-check-input" type="checkbox" id="hobby2" name="hobby" value="2">
                        <label class="form-check-label" for="hobby2"> I have a summerhouse</label><br>
                        <input class="form-check-input" type="checkbox" id="hobby3" name="hobby" value="3">
                        <label class="form-check-label" for="hobby3"> I have a pet</label><br>
                        <br>
                    </div>
                    <div class="text-center">
                        <button type="submit" class="btn btn-primary">Calculate BMI</button>
                    </div>
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