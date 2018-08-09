<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<c:import  url="/blocks/header.jsp"/>
<div id="wrap">
<div id="registerTitle"> <fmt:message key="register.register" /></div>
<form name="RegisterForm" method="POST" action="controller">
    <input type ="hidden" name="command" value="register" />
    <div id="register">
        <fmt:message key="register.name" />
        <input type="text" name="name" required maxlength="15"><br/>
        <fmt:message key="register.lastName" />
        <input type="text" name="surname" required maxlength="15"> <br/>
        <fmt:message key="register.login" />
        <input type="text" name="login" required maxlength="15"><br/>
        <fmt:message key="register.password" />
        <input type="password" name="password" required maxlength="40"><br/>
        <fmt:message key="register.email" />
        <input type="email" name="email" required maxlength="40"><br/>
        <input type="hidden" name="role" value="user">
        <input type="hidden" name="access" value="allowed">
        <input type="submit" value=" <fmt:message key="register.send" />" ><br/>
    </div>
    <div id="errorMessage">
    ${errorEmail}
    </div>
</form>
</div>
<c:import  url="/blocks/footer.jsp"/>
</body>
</html>
