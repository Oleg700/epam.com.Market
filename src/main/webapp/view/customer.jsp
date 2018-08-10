<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AdminMenu</title>
</head>

<c:import  url="/block/header.jsp"/>
<div id="wrap">
    <div id="adminblocks">
        <form  method="POST" action="controller">
            <input type ="hidden" name="command" value="selectCustomers" />
            <input type="submit" value="<fmt:message key="admin.ShowCustomers" />">
        </form><br/><hr/>

        <form method="POST" action="controller/addProduct">
            <input type ="hidden" name="command" value="findPage" />
            <input type ="hidden" name="path" value="path.page.addProduct" />
            <input type="submit" value="<fmt:message key="admin.addProudct" />">
        </form><br/><hr/>

        <form method="POST" action="controller">
            <input type ="hidden" name="command" value="selectOrders" />
            <input type="submit" value="<fmt:message key="admin.showOrders" />">
        </form><br/><hr/>
    </div>

    <div id="showUsers">
        <table border="1">
            <tr>
                <td> <fmt:message key="customer.name" /></td>
                <td> <fmt:message key="customer.lastname" /></td>
                <td> <fmt:message key="customer.login" /></td>
                <td> <fmt:message key="customer.email" /></td>
                <td> <fmt:message key="customer.access" /></td>
                <td> <fmt:message key="customer.block" /></td>

            <tr>
        </table>
        <c:forEach items="${listOfCustomers}" var="customer">
            <table border="1">
                <tr>
                    <td> ${customer.name}</td>
                    <td> ${customer.surname}</td>
                    <td> ${customer.login}</td>
                    <td> ${customer.email}</td>
                    <td> ${customer.access}</td>
                    <td>  <form name="ShowProducts" method="POST" action="controller">
                        <input type ="hidden" name="command" value="blockUser" />
                        <input type ="hidden" name="loginCustomer" value="${customer.login}" />
                        <input type="submit" value="<fmt:message key="admin.block" />">
                    </form>  </td>
                </tr>
            </table>
        </c:forEach>
    </div>
</div>
<c:import  url="/block/footer.jsp"/>
</body>
</html>
