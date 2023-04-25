<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 3/17/2023
  Time: 12:36 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>resgiter 页面</title>
    <form action="/hku_cookie_war_exploded/register" method="post">
        <label>用户名: </label><input type="text" name="userName"/><br>
        <label>密&nbsp码&nbsp: </label><input type="password" name="userPwd"/><br>
        <span style="color:red">${errMsg}</span>
        <input type="submit" value="register "/>
    </form>

</head>
</html>
