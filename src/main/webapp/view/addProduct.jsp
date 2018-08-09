<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <title>addProduct</title>
</head>
<body>

<c:import  url="/blocks/header.jsp"/>
<h1 id="titleAddProduct">
    <fmt:message key="addProduct.addproduct" />
</h1>
<c:set var="product" value="${productTitle}" scope="request"/>
<c:choose>
    <c:when test="${product != null}">
        ${productTitle} <fmt:message key="addProduct.result" />
    </c:when>
</c:choose>
<div id="wrap">
<div id ="add">
    <form action="controller" method="post" accept-charset="utf-8">
        <input type ="hidden" name="command" value="addProduct" />
        <input type="text"  name="titleEn"  placeholder="title" required maxlength="30" ><br/>
        <input type="textarea"   name="descriptionEn" placeholder="description" required maxlength="150"><br/>
        <input type="text"  name="titleRu"  placeholder="Название" required maxlength="30" ><br/>
        <input type="textarea"   name="descriptionRu" placeholder="Описание" required maxlength="150"><br/>
        <input type="number" step=0.01  name="price"  placeholder="price" required maxlength="9"><br/>
        <select name="categoryId" required>
            <option disabled>Select category</option>
            <option value="1">Notebooks</option>
            <option value="2">Phones</option>
            <option value="3">Cameras</option>
        </select>
        <select name="producerId" required>
            <option disabled>Select producer</option>
            <option value="1">Apple</option>
            <option value="2">Samsung</option>
            <option value="3">Dell</option>
            <option value="4">Lenovo</option>
            <option value="5">Nikon</option>
            <option value="6">Canon</option>
        </select><br/>
        <input type="submit" value="<fmt:message key="addProduct.addproduct" />" ><br/>
    </form>
</div>
</div>
<c:import  url="/blocks/footer.jsp"/>
</body>
</html>
