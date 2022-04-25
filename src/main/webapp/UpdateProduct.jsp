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
    <link href="css/UpdateProduct.css" rel="stylesheet"/>
</head>
<body class="text-center">
<main class="form-signin">
    <form action="UpdateProduct" method="POST" style="text-align: center;">
        <h1 class="h3 mb-3 fw-normal">
            <fmt:message key="label.updateProduct"/>
        </h1>
        <div class="form-floating">
            <input type="text" class="form-control" name="Name" id="Name"
                   placeholder="<fmt:message key="label.name" />">
            <label for="Name"><fmt:message key="label.name"/></label>
        </div>
        <div class="form-floating">
            <input class="form-control" type="number" min="0" step=".01" name="Value" id="Value"
                   placeholder="<fmt:message key="label.value" />">
            <label for="Value">
                <fmt:message key="label.value"/>
            </label>
        </div>
        <div class="form-floating">
            <input class="form-control" type="date" name="Expiry" id="Expiry"
                   placeholder="<fmt:message key="label.expiryDate" />">
            <label for="Expiry">
                <fmt:message key="label.expiryDate"/>
            </label>
        </div>
        <div class="form-floating">
            <input class="form-control" type="number" min="0" name="Discount" id="Discount"
                   placeholder="<fmt:message key="label.discount" />">
            <label for="Discount">
                <fmt:message key="label.discount"/>
            </label>
        </div>
        <div class="form-floating">
            <select name="Category" id="Category">
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
            <c:when test="${noName == true }">
                <p style="text-align: center; color: red;">
                    <fmt:message key="warning.noName"/>
                </p>
                <c:set var="noName" scope="session" value="${false}"/>
            </c:when>
            <c:when test="${productFailure == true }">
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
