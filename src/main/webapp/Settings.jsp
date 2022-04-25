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
    <link href="css/Settings.css" rel="stylesheet"/>
</head>
<body>
<main style="display:flex;text-align:center;margin:auto">
    <br>
    <div>
        <h1 class="h3 mb-3 fw-normal">
            <fmt:message key="label.language"/>
        </h1>
        <div style="font-size:1.2vw;font-weight:600">
            <a style="padding-left: 10px;" href="Settings?lang=ro">Rimana</a>
            <a style="padding-left: 10px;" href="Settings?lang=en">Engliza</a>
        </div>
        <br>
        <div>
            <a style="padding-left: 10px;" href="Logout"><fmt:message key="label.login"/></a>
            <a style="padding-left: 10px;" href="Register"><fmt:message key="label.register"/></a>
            <c:if test="${sessionScope.email!=null }">
                <a style="padding-left: 10px;" href="Main"><fmt:message key="label.main"/></a>
            </c:if>
        </div>
    </div>
</main>
</body>
</html>
