<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>OrderInfo</title>
</head>
<body>
<c:import  url="/block/header.jsp"/>
<div id="wrap">
<div id="orderInfo">
   ${login}<fmt:message key="orderInfo.message" />
</div>
</div>
<c:import  url="/block/footer.jsp"/>
</body>
</html>
