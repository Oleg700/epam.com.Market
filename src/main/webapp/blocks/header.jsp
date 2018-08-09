<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


    <link href="/css/style.css" rel="stylesheet" type="text/css">
<header>
    <c:set var="locale" value="${languageLocale}" scope="session"/>
    <c:set var="current" value="${language}" scope="session"/>
    <c:set var="currentPage" value="${currentPage}" scope="session"/>

    <c:choose>
        <c:when test="${current != null}">
              <fmt:setLocale value="${locale}"/>
            <fmt:setBundle basename="${current}" scope="session"/>
        </c:when>
        <c:otherwise>
            <fmt:setLocale value="en_US"/>
            <fmt:setBundle basename="language_en_EN" scope="session"/>
            <c:set var="languageId" value="1" scope="session"/>
        </c:otherwise>
    </c:choose>

    <div id="logo">
    Digital
    </div>

    <div id="lang_en">
    <form action="/controller" method="post">
        <input type="hidden" name="command" value="setLanguage">
        <input type="hidden" name="language" value="language_en_EN">
        <input type="hidden" name="languageLocale" value="en_US">
        <input type="hidden" name="languageId" value="1">
        <input type="image" name="submit" src="/images/language_en_EN.png" alt="Submit" />
    </form>
    </div>

    <div id="lang_ru">
    <form action="/controller" method="post">
        <input type="hidden" name="command" value="setLanguage">
        <input type="hidden" name="language" value="language_ru_RU">
        <input type="hidden" name="languageLocale" value="ru_RU">
        <input type="hidden" name="languageId" value="2">
        <input type="image" name="submit" src="/images/language_ru_RU.png" alt="Submit" />
    </form>
    </div>
    <div id="reg">
        <form method="POST" action="controller">
            <input type ="hidden" name="command" value="findPage" />
            <input type ="hidden" name="path" value="path.page.register" />
            <input type="submit"  value="<fmt:message key="header.registration"  />">
        </form>
    </div>

    <div id="login">
        <form method="POST" action="controller">
            <input type ="hidden" name="command" value="findPage" />
            <input type ="hidden" name="path" value="path.page.login" />
            <input type="submit"  value="<fmt:message key="header.login" />">
        </form>
    </div>

    <div id="logout">
        <form method="POST" action="controller">
            <input type ="hidden" name="command" value="logout" />
            <input type="submit"  value="<fmt:message key="header.logout" />">
        </form>
    </div>

    <div id="loginUser">${login}</div>


</header>

<menu>
    <div id="category">
        <form name="ShowProducts" method="POST" action="controller/notebooks">
            <input type ="hidden" name="command" value="selectProducts" />
            <input type ="hidden" name="category" value="1" />
            <input type="submit"  value="<fmt:message key="header.category.notebooks" />">
        </form>
    </div>
    <div id="category2">
        <form name="ShowProducts" method="POST" action="controller">
            <input type ="hidden" name="command" value="selectProducts" />
            <input type ="hidden" name="category" value="2">
            <input type="submit"  value="<fmt:message key="header.category.phones" />">
        </form>
    </div>



    <div id="category3">
        <form name="ShowProducts" method="POST" action="controller">
            <input type ="hidden" name="command" value="selectProducts" />
            <input type ="hidden" name="category" value="3" />
            <input type="submit"  value="<fmt:message key="header.category.cameras" />">
        </form>

    </div>
</menu>
