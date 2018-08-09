<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Menu</title>
</head>
<body>

<c:import  url="/blocks/header.jsp"/>
${wrongAction}
<c:set var="login" value="${login}" scope="request"/>
<c:set var="name" value="${name}" scope="request"/>
<c:choose>
    <c:when test="${login != null}">
        <fmt:message key="menu.welcome" />${login}
    </c:when>
</c:choose>
<c:choose>
    <c:when test="${name != null}">
        ${name},  <fmt:message key="menu.registraationSuccess" />
    </c:when>
</c:choose>



<div id="wrap">
<div id="wrapper">
    <div id="column">
        <div id="bigArticle">
               <img src="/images/img2.jpg">
            <h2><fmt:message key="menu.notebooks" /></h2>
            <p><fmt:message key="menu.advertismentNotebooks" /></p>
               <form method="POST" action="controller">
                   <input type ="hidden" name="command" value="selectProducts" />
                   <input type ="hidden" name="category" value="1" />
                   <input type="submit"  value="<fmt:message key="menu.choose" />">
               </form>
        </div>
        <div class="clear"><br></div>
        <div class="article">

                <img src="../images/img5.jpg">
                <h2><fmt:message key="menu.phones" /></h2>
            <p><fmt:message key="menu.advertismentPhones" /></p>
            <form method="POST" action="controller">
                <input type ="hidden" name="command" value="selectProducts" />
                <input type ="hidden" name="category" value="2" />
                <input type="submit"  value="<fmt:message key="menu.choose" />">
            </form>
        </div>
        <div class="article">
            <img src="/images/img3.jpg">
            <h2><fmt:message key="menu.cameras" /></h2>
            <p><fmt:message key="menu.advertismentCameras" /></p>
            <form method="POST" action="controller">
                <input type ="hidden" name="command" value="selectProducts" />
                <input type ="hidden" name="category" value="3" />
                <input type="submit"  value="<fmt:message key="menu.choose" />">
            </form>
        </div>
    </div>
</div>
</div>
<c:import  url="/blocks/footerMenu.jsp"/>
</body>
</html>