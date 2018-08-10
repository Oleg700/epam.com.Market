<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login</title>
</head>
<body>
<c:import  url="/block/header.jsp"/>
<div id="wrap">
<form name="LoginForm" method="POST" action="controller">
    <div id="loginPage">
        <input type="hidden" name="command" value="login"><br/>
        <input type="text" name="login" placeholder="login" maxlength="30"><br/>
        <input type="password" name="password" placeholder="password" maxlength="40"><br/>
        <input type="submit" value="<fmt:message key="login.send" />" ><br/>
    </div>
</form>

<div id="errorMessage">
    ${loginError}
    ${accessError}
    ${errorLogin}
</div>
</div>
<c:import  url="/block/footer.jsp"/>
</body>
</html>
