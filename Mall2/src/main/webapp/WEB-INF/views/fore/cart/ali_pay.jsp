<!DOCTYPE html>
<%--提交订单后的结账页面（一个支付宝二维码图片）
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
    <title>支付</title>
    <script src="${pageContext.request.contextPath}/js/jquery/jquery-3.2.1.min.js" type="text/javascript"></script>
    <link href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.min.css" rel="stylesheet">
    <script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js"></script>
    <link href="${pageContext.request.contextPath}/css/fore/iconfont.css" rel="stylesheet">

    <style>
        div.payPage {
            font-size: 12px;
            font-family: Arial;
            text-align: center;
            padding-bottom: 40px;
            max-width: 1200px;
            margin: 10px auto;
        }

        div.payLogo {
            padding: 40px;
        }

        span.payText {
            color: #4D4D4D;
        }

        span.payMoney {
            display: block;
            color: #FF6600;
            font-weight: bold;
            font-size: 20px;
            margin: 10px;
        }

        button.confirmPay {
            background-color: #00AAEE;
            border: 1px solid #00AAEE;
            text-align: center;
            line-height: 31px;
            font-size: 14px;
            font-weight: 700;
            color: white;
            width: 107px;
            margin-top: 20px;
        }
    </style>
</head>
<body>
<%@include file="../../include/fore/foreHeader.jsp" %>

<div class="foreWoreArea">
    <div class="payPage">
        <div class="payLogo">
            <img src="${pageContext.request.contextPath}/img/site/simpleLogo.png" class="pull-left">
            <div style="clear:both"></div>
        </div>
        <div>
            <span class="payText">扫一扫付款（元）</span>
            <span class="payMoney">${order.total}</span>
        </div>
        <div>
            <img src="${pageContext.request.contextPath}/img/site/canvas.png">
        </div>
        <div>
            <a href="${pageContext.request.contextPath}/mallOrder/payOrder?oid=${order.oid}">
                <button class="confirmPay">确认支付</button>
            </a>
        </div>
    </div>
</div>

</body>
</html>
