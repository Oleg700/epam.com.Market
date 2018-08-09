<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Products</title>
</head>
<body>
<c:import  url="/blocks/header.jsp"/>
<div id="wrap">
<c:forEach items="${listOfProducts}" var="product">
<div id="products">
       <h2>${product.productTitle}</h2>
    <fmt:message key="products.description" /> ${product.description}<br/>
    <fmt:message key="products.producer" />${product.product.producer.title}<br/>
    <fmt:message key="products.price" /> ${product.product.price} tg
    <form  method="POST" action="controller">
            <input type ="hidden" name="command" value="makeOrder" />
            <input type ="hidden" name="productTitle" value="${product.productTitle}" />
            <input type ="hidden" name="productId" value="${product.productId}" />
        <input type="submit"  value=" <fmt:message key="products.order" />">
    </form>
    </div>
    <div id="separation"></div>
</c:forEach>
</div>
<c:import  url="/blocks/footerMenu.jsp"/>
</body>
</html>
