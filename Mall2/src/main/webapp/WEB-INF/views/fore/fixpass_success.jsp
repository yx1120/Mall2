<!DOCTYPE html>
<%--
  Created by IntelliJ IDEA.
  User: Yang xu
  Date: 2019/12/11
  Time: 22:25
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix='fmt' %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <title>注册成功</title>
    <script src="${pageContext.request.contextPath}/js/jquery/jquery-3.2.1.min.js" type="text/javascript"></script>
    <link href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.min.css" rel="stylesheet">
    <script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js"></script>
    <link href="${pageContext.request.contextPath}/css/fore/iconfont.css" rel="stylesheet">


    <style>
        div.success_div {
            width: 1000px;
            margin: 50px auto;
            text-align: center;
            line-height: 100px;
            height: 100px;
            background-color: #F3FDF6;
            font-size: 20px;

        }

        #succ_span {
            color: #15C2B4;
        }
        a:hover{
            text-decoration: none;
            color: deeppink;
        }
    </style>
</head>
<body>
<%@include file="../include/fore/foreHeader.jsp" %>

<div class="foreWoreArea">
    <div class="success_div">
        <span>
            <span class="glyphicon glyphicon-ok-sign" id="succ_span"></span>
            <a href="${pageContext.request.contextPath}/fore/login">修改成功，点击登录！</a>
        </span>
    </div>
</div>

</body>
</html>
