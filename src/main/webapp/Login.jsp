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
    <link href="css/Login.css" rel="stylesheet"/>
</head>
<body>
<main class="form-signin">
    <form action="Login" method="POST" style="text-align: center;">
        <h1 class="h3 mb-3 fw-normal">
            <fmt:message key="label.login"/>
        </h1>
        <div class="form-floating">
            <input type="email" class="form-control" name="email" id="email"
                   placeholder="<fmt:message key="label.email" />">
            <label for="email">
                <fmt:message key="label.email"/>
            </label>
        </div>
        <div class="form-floating">
            <input type="password" class="form-control" name="password" id="password"
                   placeholder="<fmt:message key="label.password" />">
            <label for="password">
                <fmt:message key="label.password"/>
            </label>
        </div>
        <a style="float:left;" href="Settings">
            <fmt:message key="label.settings"/>
        </a>
        <a style="float: right;" href="Register">
            <fmt:message key="link.register"/>
        </a>
        <br>
        <input class="btn btn-primary" type="submit" name="SubmitButton"
               value="<fmt:message key="label.submit" />" id="submit_button">
        <c:if test="${loginFailed == true }">
            <p style="text-align: center; color: red;">
                <fmt:message key="warning.login"/>
            </p>
            <c:set var="loginFailed" scope="session" value="${false}"/>
        </c:if>
        <br>

    </form>
</main>
</body>
</html>
