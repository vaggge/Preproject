<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>
<%--
  Created by IntelliJ IDEA.
  User: Valera
  Date: 27.03.2020
  Time: 18:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User page</title>
</head>
<body>
<table border="1" width="25%" cellpadding="5">
    <h1>User information</h1>
    <c:set var="user" scope="request" value="${user}"/>
    <tr><td><p><b>ID</b></p></td><td><p><b>Name</b></p></td><td><p><b>Password</b></p></td><td><p><b>Role</b></p></td>
        <tr><td>${user.id} </td><td>${user.name}</td><td>${user.password}</td><td>${user.role}</td></tr>
</table>
</body>
</html>
