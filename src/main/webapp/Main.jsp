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
    <link href="css/Main.css" rel="stylesheet"/>
</head>
<body>
<main style="width:100%">
    <p style="margin-left:30px;font-size:1.6vw"><fmt:message key="label.welcomeBack"/><c:out
            value="${sessionScope.email}"/></p>
    <br>
    <div style="float:right;margin-right:30px">
        <select class="form-control" name="Sort" id="Sort" form="Form1">
            <option value="none" ${sort==null ? 'selected' : ''} disabled><fmt:message key="label.pickSort"/></option>
            <option value="" ${sort=='' ? 'selected' : ''}><fmt:message key="select.any"/></option>
            <option value="P.product_IDAsc" ${sort=='P.product_IDAsc' ? 'selected' : ''}><fmt:message
                    key="select.idAsc"/></option>
            <option value="P.product_IDDesc" ${sort=='P.product_IDDesc' ? 'selected' : ''}><fmt:message
                    key="select.idDesc"/></option>
            <option value="P.product_nameAsc" ${sort=='P.product_nameAsc' ? 'selected' : ''}><fmt:message
                    key="select.nameAsc"/></option>
            <option value="P.product_nameDesc" ${sort=='P.product_nameDesc' ? 'selected' : ''}><fmt:message
                    key="select.nameDesc"/></option>
            <option value="P.product_valueAsc" ${sort=='P.product_valueAsc' ? 'selected' : ''}><fmt:message
                    key="select.valueAsc"/></option>
            <option value="P.product_valueDesc" ${sort=='P.product_valueDesc' ? 'selected' : ''}><fmt:message
                    key="select.valueDesc"/></option>
            <option value="P.product_expiryAsc" ${sort=='P.product_expiryAsc' ? 'selected': ''}><fmt:message
                    key="select.expiryAsc"/></option>
            <option value="P.product_expiryDesc" ${sort=='P.product_expiryDesc' ? 'selected' : ''}><fmt:message
                    key="select.expiryDesc"/></option>
            <option value="P.product_discountAsc" ${sort=='P.product_discountAsc' ? 'selected' : ''}><fmt:message
                    key="select.discountAsc"/></option>
            <option value="P.product_discountDesc" ${sort=='P.product_discountDesc' ? 'selected' : ''}><fmt:message
                    key="select.discountDesc"/></option>
            <option value="U.emailAsc"><fmt:message key="select.ownerAsc"/></option>
            <option value="U.emailDesc"><fmt:message key="select.ownerDesc"/></option>
        </select>
    </div>
    <br>
    <div style="display: flex;width:100%">
        <div class="left-container">
            <form action="GetProducts" method="POST" id="Form1">
                <div class="form-check">
                    <label class="form-check-label" for="UserOnly"><fmt:message key="label.onlyUser"/></label>
                    <input class="form-check-inputl" type="checkbox" id="UserOnly"
                           name="UserOnly" ${userOnly == 'on' ? 'checked' : ''} >
                </div>
                <div class="form-group">
                    <input class="form-control" type="text" id="Name" name="Name" value="${name}"
                           placeholder="<fmt:message key="label.name"/>">
                </div>
                <div class="form-group">
                    <input class="form-control" type="number" min="0" step=".01" id="LowerValue" name="LowerValue"
                           value="${lowerValue}"
                           placeholder="<fmt:message key="label.lowerValue" />">
                </div>
                <div class="form-group">
                    <input class="form-control" type="number" min="0" step=".01" id="HigherValue" name="HigherValue"
                           value="${higherValue }"
                           placeholder="<fmt:message key="label.higherValue" />">
                </div>
                <div class="form-group">
                    <label for="LowerExpiry"><fmt:message key="label.lowerExpiry"/></label>
                    <br>
                    <input class="form-control" type="date" id="LowerExpiry" name="LowerExpiry" value="${lowerExpiry }"
                           placeholder="Lower expiry">
                </div>
                <div class="form-group">
                    <label for="HigherExpiry"><fmt:message key="label.higherExpiry"/></label>
                    <br>
                    <input class="form-control" type="date" id="HigherExpiry" name="HigherExpiry"
                           value="${higherExpiry }"
                           placeholder="Higher Expiry">
                </div>
                <div class="form-group">
                    <input class="form-control" type="number" id="LowerDiscount" name="LowerDiscount"
                           value="${lowerDiscount }"
                           placeholder="<fmt:message key="label.lowerDiscount" />">
                </div>
                <div class="form-group">
                    <input class="form-control" type="number" id="HigherDiscount" name="HigherDiscount"
                           value="${higherDiscount }"
                           placeholder="<fmt:message key="label.higherDiscount" />">
                </div>
                <div class="form-group">
                    <select class="form-control" name="Category" id="Category">
                        <option value="none" ${category==null ? 'selected' : ''} disabled><fmt:message
                                key="label.pickCategory"/></option>
                        <option value="" ${category=='' ? 'selected' : ''}><fmt:message key="select.any"/></option>
                        <option value="dairy" ${category=='dairy' ? 'selected' : ''}><fmt:message
                                key="select.dairy"/></option>
                        <option value="bakery" ${category=='bakery' ? 'selected' : ''}><fmt:message
                                key="select.bakery"/></option>
                    </select>
                </div>
                <div class="form-group">
                    <input class="btn btn-primary" type="submit" name="SubmitButton"
                           value="<fmt:message key="label.filter" />" id="submit_button">
                </div>
            </form>
            <div>
                <form action="AddProduct" class="form-group">
                    <input class="btn btn-success" type="submit" name="AddButton"
                           value="<fmt:message key="label.addProduct" />">
                </form>
            </div>
        </div>
        <div class="table-wrapper-scroll-y my-custom-scrollbar right-container">
            <table class="table table-striped mb-0">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col"><fmt:message key="label.name"/></th>
                    <th scope="col"><fmt:message key="label.value"/></th>
                    <th scope="col"><fmt:message key="label.expiryDate"/></th>
                    <th scope="col"><fmt:message key="label.category"/></th>
                    <th scope="col"><fmt:message key="label.discount"/></th>
                    <th scope="col"><fmt:message key="label.owner"/></th>
                    <th scope="col"></th>
                    <th scope="col"></th>
                </tr>
                </thead>
                <c:if test="${products!= null}">
                    <tbody>
                    <c:forEach items="${products}" var="product">
                        <tr style="background-color:${sessionScope.productId == product.getId() ? '#63ff69' : null}">
                            <td>${product.getId()} </td>
                            <td>${product.getName()} </td>
                            <td>${product.getValue()} </td>
                            <td>${product.getExpiry()} </td>
                            <td>
                                <c:if test="${product.getCategory()!=null}">
                                    <fmt:message key="select.${product.getCategory()}"/>
                                </c:if>
                            </td>
                            <td>${product.getDiscount()} </td>
                            <td>${product.getOwner()} </td>
                            <td>
                                <c:if test="${product.getOwner()==email }">
                                    <form action="DeleteProduct?productId=${product.getId()}" method="post">
                                        <button class="btn btn-danger" type="submit">
                                            <fmt:message key="label.deleteProduct"/>
                                        </button>
                                    </form>
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${product.getOwner()==email }">
                                    <form action="UpdateProduct?productId=${product.getId()}&productUpdated=false"
                                          method="post">
                                        <button class="btn btn-info" type="submit">
                                            <fmt:message key="label.updateProduct"/>
                                        </button>
                                    </form>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </c:if>
            </table>
        </div>
        <br>
    </div>
    <br>
    <div style="margin-left:30px;font-size:1.2vw;font-weight:600">
        <a style="padding-right: 10px;" href="Settings"><fmt:message key="label.settings"/></a>
        <a href="Logout"><fmt:message key="label.logout"/></a>
    </div>
    <c:choose>
        <c:when test="${sessionScope.productSuccess == true }">
            <p style="text-align: center; color: green;">
                <fmt:message key="message.success"/>
            </p>
            <c:set var="productSuccess" scope="session" value="${false}"/>
        </c:when>
        <c:when test="${sessionScope.productFailure == true }">
            <p style="text-align: center; color: red;">
                <fmt:message key="warning.failure"/>
            </p>
            <c:set var="productFailure" scope="session" value="${false}"/>
        </c:when>
    </c:choose>
    <c:set var="productId" scope="session" value="${null}"/>
    <p><c:out value="${ userOnly}"/>
</main>
</body>
</html>