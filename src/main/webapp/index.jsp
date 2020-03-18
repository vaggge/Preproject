<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  Models.User: Valera
  Date: 08.03.2020
  Time: 20:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Users</title>
  </head>
  <table border="1" width="25%" cellpadding="5">
    <h1>Users list</h1>
    <c:set var = "isSave" scope = "request" value = "${isSave}"/>
    <c:choose>
      <c:when test = "${isSave == true}">
        <p>
          <big><font color="red">Editing was successful</font></big>
        </p>
      </c:when>
      <c:when test = "${isSave == false}">
        <p>
          <big><font color="red">The name is busy</font></big>
        </p>
      </c:when>
    </c:choose>
    <c:if test="${isVisible == true}" scope="request" var="isVisible"><big><font color="red">User was delete</font></big></c:if>
<tr><td><p><b>ID</b></p></td><td><p><b>Name</b></p></td><td><p><b>Password</b></p></td><td><p><b>Action</b></p></td></tr>
    <c:set var="users" scope="request" value="${users}"/>
    <c:forEach items="${users}" var="user">
      <tr><td>
          ${user.id} </td><td>${user.name}</td><td>${user.password}</td>
        <td>
          <form action="/delete" method="post">
            <button name="name" value="${user.name}" type="submit" onclick='this.form.submit()'>Delete</button>
      </form>
        <form action="/edit" method="get">
          <button name = "name" value = "${user.name}" type="submit" onclick='this.form.submit()'>Edit</button>
        </form>
        </td></tr>
    </c:forEach>
  </table>

  <c:if test="${isEditVisible == true}" scope="request" var="isVisible">
      <form action="/edit" method="post">
        Set new name
        <input type="text" name="newName">
        Set new password
        <input type="text" name="newPassword">
        <input type="submit" name="submit" value="Save">
      </form>
    </c:if>

  <h1>Add new user</h1>
  <p>
  <big><font color="red"><c:out value="${add}"> </c:out></font></big>
  </p>
  <form action="/add" method="post">
    <p>
    <input type="text" name="name" placeholder="Name">
    </p>
    <p>
    <input type="password" name="password" placeholder="Password">
    </p>
    <input type = "submit" name = "submit" value = "Add">
  </form>
  </body>
</html>
