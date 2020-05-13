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
    <title>支付成功</title>
    <script src="${pageContext.request.contextPath}/js/jquery/jquery-3.2.1.min.js" type="text/javascript"></script>
    <link href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.min.css" rel="stylesheet">
    <script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js"></script>
    <link href="${pageContext.request.contextPath}/css/fore/iconfont.css" rel="stylesheet">

    <style>
        div.paySuccess_div {
            width: 1200px;
            margin: 100px auto;
            border: 1px solid gainsboro;
        }

        div.pay_up {
            background-color: #ECFFDC;
            padding: 20px;
        }

        div.pay_meddle {
            padding: 20px;
        }

        div.pay_me2 {
            margin-left: 20px;
        }

        div.pay_line {
            border-bottom: 1px solid gainsboro;
            margin: 20px auto;
            width: 90%;
        }

        div.pay_down {
            padding-bottom: 20px;
            margin-left: 40px;
        }

        span.redColor {
            color: #C40000;
            font-weight: bold;
        }
    </style>
</head>
<body>
<%@include file="../../include/fore/foreHeader.jsp" %>

<div class="foreWoreArea">
    <div class="paySuccess_div">

        <div class="pay_up">
            <img src="${pageContext.request.contextPath}/img/site/paySuccess.png">
            <span>您已成功付款</span>
        </div>

        <div class="pay_meddle">
            <ul>
                <li>收货地址： ${order.address}&nbsp&nbsp${order.receiver}&nbsp&nbsp${order.mobile}</li>
                <li>实付款：<span class="redColor">￥${total}</span></li>
                <li>预计08月08日送达</li>
            </ul>
            <div class="pay_me2">
                您可以
                <a href="${pageContext.request.contextPath}/space/myOrders/all" class="payedCheckLink">查看已买到的宝贝</a>
            </div>
        </div>

        <div class="pay_line">
        </div>

        <div class="pay_down">
            <img src="${pageContext.request.contextPath}/img/site/warning.png">
            <b>安全提醒：</b>下单后，<span class="redColor">用QQ给您发送链接办理退款的都是骗子！</span>天猫不存在系统升级，订单异常等问题，谨防假冒客服电话诈骗！
        </div>

    </div>
</div>


</body>
</html>
