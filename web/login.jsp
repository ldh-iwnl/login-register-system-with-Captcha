<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 3/17/2023
  Time: 12:45 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login 页面</title>
    <form action="/hku_cookie_war_exploded/login" method="post">
        <label>用户名: </label><input type="text" name="userName" value="${cookie.userName.value}"/><br>
        <label>密&nbsp码&nbsp: </label><input type="password" name="userPwd" value="${cookie.userPwd.value}"/><br>
        <label>记住密码: </label><input type="checkbox" name="rememberPassword"/><br>
        <span style="color:red">${errorMsg}</span>
        <input type="submit" value="login "/>
    </form>

</head>
</html>
