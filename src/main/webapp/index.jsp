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
    <title>Login Page</title>
  </head>
 <body>
 <br><br><br><p  align="center" style="font-size: 35px"><strong>Login Page</strong></p>
 <c:set var="isLogin" scope="session" value="${isLogin}" />
 <c:choose>
     <c:when test = "${isLogin == null}">
         <div id="action_form" style="left: 50%;margin-left: -150px;position: absolute;width: 1000px;margin-top: 1%;">
             <c:if test="${isVisible == true}">
                 <p> <big><font color="red">Something is wrong</font></big></p>
             </c:if>
             <form action="/login" method="post">
                 <input type="text" name="name" placeholder="Name" >
                 <input type="password" name="password" placeholder="Password">
                 <input type="submit" value="Login">
             </form>
         </div>
     </c:when>
     <c:when test = "${isLogin == true}">
     <br><br><br><p  align="center" style="font-size: 35px"><strong>Hello, <c:out value="${user.name}"/>! </p></strong>
 <form action="/logout" method="post">
     <p align="center"><button name = "name" value = "${user.name}" type="submit" onclick='this.form.submit()'>Logout</button>
     </p>
         </form>
     </c:when>
 </c:choose>
 </body>
</html>
