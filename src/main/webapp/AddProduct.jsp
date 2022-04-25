<%@page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page isELIgnored="false" %>


<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="i18n.messages"/>

<!DOCTYPE html>

<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <!-- Bootstrap CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet"/>
    <link href="css/AddProduct.css" rel="stylesheet"/>
</head>
<body>
<main class="form-signin">
    <form action="AddProduct" method="POST" style="text-align: center;">
        <h1 class="h3 mb-3 fw-normal">
            <fmt:message key="label.addProduct"/>
        </h1>
        <div class="form-floating">
            <input type="text" class="form-control" name="NameAdd" id="NameAdd"
                   placeholder="<fmt:message key="label.name" />">
            <label for="NameAdd">
                <fmt:message key="label.name"/>
            </label>
        </div>
        <div class="form-floating">
            <input class="form-control" type="number" min="0" step=".01" name="ValueAdd" id="ValueAdd"
                   placeholder="<fmt:message key="label.value" />">
            <label for="ValueAdd">
                <fmt:message key="label.value"/>
            </label>
        </div>
        <div class="form-floating">
            <input class="form-control" type="date" name="ExpiryAdd" id="ExpiryAdd"
                   placeholder="<fmt:message key="label.expiryDate" />">
            <label for="ExpiryAdd">
                <fmt:message key="label.expiryDate"/>
            </label>
        </div>
        <div class="form-floating">
            <input class="form-control" type="number" min="0" name="DiscountAdd" id="DiscountAdd"
                   placeholder="<fmt:message key="label.discount" />">
            <label for="DiscountAdd">
                <fmt:message key="label.discount"/>
            </label>
        </div>
        <div class="form-floating">
            <select name="CategoryAdd" id="CategoryAdd">
                <option value="none" selected disabled><fmt:message key="label.pickCategory"/></option>
                <option value=""><fmt:message key="select.any"/></option>
                <option value="dairy"><fmt:message key="select.dairy"/></option>
                <option value="bakery"><fmt:message key="select.bakery"/></option>
            </select>
        </div>
        <input class="w-100 btn btn-lg btn-primary" type="submit" name="SubmitButton"
               id="submit_button" value="<fmt:message key="label.submit" />">
        <div class="h5 mb-3 fw-normal" style="text-align: center;">
            <a href="Main">
                <fmt:message key="label.back"/>
            </a>
        </div>
        <br>
        <c:choose>
            <c:when test="${sessionScope.noName == true }">
                <p style="text-align: center; color: red;">
                    <fmt:message key="warning.noName"/>
                </p>
                <c:set var="noName" scope="session" value="${false}"/>
            </c:when>
            <c:when test="${sessionScope.productFailure == true }">
                <p style="text-align: center; color: red;">
                    <fmt:message key="warning.failure"/>
                </p>
                <c:set var="productFailure" scope="session" value="${false}"/>
            </c:when>
        </c:choose>
    </form>
</main>
</body>
</html>
