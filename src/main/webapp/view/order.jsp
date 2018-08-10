<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 18.07.2018
  Time: 3:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:import  url="/block/header.jsp"/>
<div id="wrap">
    <div id="showUsers">
        <table border="1">
            <tr>
                <td> <fmt:message key="order.orderId" /></td>
                <td> <fmt:message key="order.customerId" /></td>
                <td> <fmt:message key="order.login" /></td>
                <td> <fmt:message key="order.productId" /></td>
                <td> <fmt:message key="order.price" /></td>
                <td> <fmt:message key="order.orderDate" /></td>
                <td> <fmt:message key="order.orderTime" /></td>
                <tr>
        </table>
                <c:forEach items="${listOfOrders}" var="order">
                    <table border="1">
            <tr>
                <td> ${order.orderId}</td>
                <td> ${order.customer.customerId}</td>
                <td> ${order.customer.login}</td>
                <td> ${order.product.productId}</td>
                <td> ${order.product.price}</td>
                <td> ${order.orderDate}</td>
                <td> ${order.orderTime}</td>
            </tr>
        </table>
        </c:forEach>

    </div>
</div>
</body>
</html>
