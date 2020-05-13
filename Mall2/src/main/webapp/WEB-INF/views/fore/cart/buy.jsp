<!DOCTYPE html>
<%--结算页面：
  --1.从单个商品点击“立即购买”后到此页面
    --分析：提交tid,后台获取tid的数组，计算总价
  --2.从购物车点击“结算”后到此页面来
    --分析：

  --3.返回list<OrderItem> list    ,int totalMoney
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
    <title>订单结算</title>
    <script src="${pageContext.request.contextPath}/js/jquery/jquery-3.2.1.min.js" type="text/javascript"></script>
    <link href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.min.css" rel="stylesheet">
    <script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js"></script>
    <link href="${pageContext.request.contextPath}/css/fore/iconfont.css" rel="stylesheet">
    <script>
        $(function () {
            $("button.submitOrderButton").click(function () {
                $("#detailForm").submit();
            });
        })
    </script>
</head>
<body>
<%@include file="../../include/fore/foreHeader.jsp" %>

<div class="foreWoreArea">


    <form action="${pageContext.request.contextPath}/mallOrder/createOrder" method="post" id="detailForm">
        <%--    ----------------buy_up-----------------------%>
        <%@include file="buy_up.jsp" %>
        <%--    ----------------buy_up-----------------------%>

        <%--    ----------------buy_down-----------------------%>
        <%@include file="buy_down.jsp" %>
        <%--    ----------------buy_down-----------------------%>
    </form>


</div>

</body>
</html>
