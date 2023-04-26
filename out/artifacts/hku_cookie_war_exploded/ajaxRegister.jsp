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
  <title>AjaxResgiter 页面</title>
    <label>用户名: </label><input type="text" id= "userName" name="userName" onkeyup=hkuAxios(this) /><br>
    <label>密&nbsp码&nbsp: </label><input type="password" id="userPwd" name="userPwd"/><br/>
    <label>验证码: </label><input type="text" name="code" id="randomCode"/><img  src="VerifycodeServlet"><br>
    <a id="ecode" href="#">cannot see? change to a new one</a><br>
    <span id= "error" style="color:red">${errMsg}</span>
    <input type="submit" value="register " onclick= "ajaxRegister()" />

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
  <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
  <script>
    function hkuAxios(object){
      var userName = object.value ;
      axios({
        method: 'Get',
        url: 'http://localhost:8080${pageContext.request.contextPath}/exUserNameServlet',
        params: {
          userName: userName
        }
      }).then(function(res){
        document.getElementById("error").innerText = res.data;
      }).catch(function(err){
        console.log(err);
      })
    }
  </script>
  <script>
    function ajaxRegister(){
      var userName = document.getElementById("userName").value;
      var userPwd = document.getElementById("userPwd").value;
      var code = document.getElementById("randomCode").value;
      axios({
        method: 'Post',
        url: 'http://localhost:8080${pageContext.request.contextPath}/registerAjaxServlet',
        params: {
          userName: userName,
          userPwd: userPwd,
          code: code
        }
      }).then(function(res){
        var jsonData = res.data;
        if(jsonData.code!="200"){
            document.getElementById("error").innerText = jsonData.msg;
            return;
        }
        alert("register success");
        window.location.href= "http://localhost:8080${pageContext.request.contextPath}/login";
      }).catch(function(err){
        console.log(err);
      })

    }
  </script>
</head>
</html>
