<%--
  Created by IntelliJ IDEA.
  User: Yang xu
  Date: 2019/11/18
  Time: 19:35
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<html>

<head>
  <title>后台管理</title>
</head>

<style>
  #adminIndex{
    font-size: 40px;
    color: skyblue;
    text-decoration: none
  }
  #adminIndex:hover{
    color: #2aabd2;
  }
</style>

<script>
  $(function () {
    $.ajax({
      url: '${pageContext.request.contextPath}//firstCategory/admin_list',
      async: true,
    });
  })
</script>

<body style="text-align: center;margin-top:300px;">
<a href="${pageContext.request.contextPath}/firstCategory/admin_list" id="adminIndex">登录成功，欢迎您!!!</a>
</body>
</html>

