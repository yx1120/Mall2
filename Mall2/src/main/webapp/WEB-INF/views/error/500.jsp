<%--
  Created by IntelliJ IDEA.
  User: Yang xu
  Date: 2020/2/28
  Time: 20:51
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0" />
    <title>500</title>

    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/404.css" />

    <!--[if IE 6]>
    <script src="${pageContext.request.contextPath}/js/png.js"></script>
    <script>DD_belatedPNG.fix('*')</script>
    <![endif]-->

</head>
<body>

<div id="wrap" style="margin-top:200px;">
    <div>
        <img src="${pageContext.request.contextPath}/img/500.png" alt="500" />
    </div>
    <div id="text">
        <strong style="padding-right: 10px">
            <a href="javascript:history.back()">返回上一页</a>
        </strong>
    </div>
</div>

<div class="animate below"></div>
<div class="animate above"></div>

</body>
</html>

