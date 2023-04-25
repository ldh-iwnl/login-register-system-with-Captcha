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
        <label>密&nbsp码&nbsp: </label><input type="password" name="userPwd"/><br/>
        <label>验证码: </label><input type="text" name="code"/><img id="exchangecode" src="VerifycodeServlet"><br>
        <a id="ecode" href="#">cannot see? change to a new one</a><br>
        <span style="color:red">${errMsg}</span>
        <input type="submit" value="register "/>
    </form>

    <script type="text/javascript">
        window.onload = function () {
            //获取img标签的对象
            img = document.getElementById("exchangecode");
            img.onclick = function () {
                //加时间戳,避免浏览器缓存
                var date = new Date().getTime()
                img.src = "VerifycodeServlet?" + date;
            }
            //获取a标签的对象
            ec = document.getElementById("ecode");
            ec.onclick = function () {
                //加时间戳
                var date = new Date().getTime()
                img.src = "VerifycodeServlet?" + date;
            }
        }

    </script>
</head>
</html>
