<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="i18n.messages"/>

<!DOCTYPE html>

<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <!-- Bootstrap CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet"/>
    <link href="css/Register.css" rel="stylesheet"/>
</head>
<body>
<main class="form-signin">
    <form action="Register" method="POST" style="text-align: center;">
        <h1 class="h3 mb-3 fw-normal">
            <fmt:message key="label.register"/>
        </h1>
        <div class="form-floating">
            <input type="text" class="form-control" name="email" id="email"
                   placeholder="<fmt:message key="label.email" />">
            <label for="email"><fmt:message key="label.email"/></label>
        </div>
        <div class="form-floating">
            <input class="form-control" type="password" name="password" id="password"
                   placeholder="<fmt:message key="label.password" />">
            <label for="password">
                <fmt:message key="label.password"/>
            </label>
        </div>
        <div class="form-floating">
            <input class="form-control" type="password" name="passwordC" id="passwordC"
                   placeholder="<fmt:message key="label.passwordC" />">
            <label for="passwordC">
                <fmt:message key="label.passwordC"/>
            </label>
        </div>
        <input class="w-100 btn btn-lg btn-primary" type="submit" name="SubmitButton"
               id="submit_button" value="<fmt:message key="label.submit" />">
        <div class="h5 mb-3 fw-normal" style="text-align: center;">
            <a href="Login">
                <fmt:message key="label.back"/>
            </a>
        </div>
        <br>
        <a style="padding-right: 10px;" href="Settings"><fmt:message key="label.settings"/></a>
        <c:choose>
            <c:when test="${samePassword == true }">
                <p style="text-align: center; color: red;">
                    <fmt:message key="warning.same"/>
                </p>
                <c:set var="samePassword" scope="session" value="${false}"/>
            </c:when>
            <c:when test="${existingEmail == true }">
                <p style="text-align: center;color:red;">
                    <fmt:message key="warning.exists"/>
                </p>
                <c:set var="existingEmail" scope="session" value="${false}"/>
            </c:when>
            <c:when test="${badEmail == true }">
                <p style="text-align: center;color:red;">
                    <fmt:message key="warning.badEmail"/>
                </p>
                <c:set var="badEmail" scope="session" value="${false}"/>
            </c:when>
            <c:when test="${badPassword == true }">
                <p style="text-align: center;color:red;">
                    <fmt:message key="warning.badPassword"/>
                </p>
                <c:set var="badPassword" scope="session" value="${false}"/>
            </c:when>
            <c:when test="${registerSuccess == true }">
                <p style="text-align: center;color:green;">
                    <fmt:message key="message.success"/>
                </p>
                <c:set var="registerSuccess" scope="session" value="${false}"/>
            </c:when>
            <c:when test="${registerFailure == true }">
                <p style="text-align: center;color:red;">
                    <fmt:message key="warning.exists"/>
                </p>
                <c:set var="registerFailure" scope="session" value="${false}"/>
            </c:when>
        </c:choose>
    </form>
</main>
</body>
</html>
