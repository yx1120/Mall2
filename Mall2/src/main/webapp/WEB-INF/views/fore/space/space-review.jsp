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
    <title>我的评价-个人中心</title>
    <script src="${pageContext.request.contextPath}/js/jquery/jquery-3.2.1.min.js" type="text/javascript"></script>
    <link href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.min.css" rel="stylesheet">
    <script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js"></script>
    <link href="${pageContext.request.contextPath}/css/fore/iconfont.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/fore/space.css" rel="stylesheet">


    <style>
        .review_pname{
            font-weight: bold;
            color: darkslategray;
        }
        .review_pname:hover{
            text-decoration: none;
            color: #ff0036;
        }
    </style>
    <script>
        $(function () {
            $("a[itemStatus]").click(function(){
                var itemStatus = $(this).attr("itemStatus");
                location.href = "${pageContext.request.contextPath}/space/myReviews/" + itemStatus + "";
            });
        });
    </script>
</head>
<body>
<%@include file="../../include/fore/foreHeader.jsp" %>

<div class="workspace">
    <div id="left_nav">
        <div class="list-group">
            <a href="#" class="list-group-item disabled">
                个人中心
            </a>
            <a href="../personalSpace" class="list-group-item">个人信息</a>
            <a href="../myOrders/all" class="list-group-item">我的订单</a>
            <a href="../myFavorites" class="list-group-item">我的收藏</a>
            <a href="myReviews/all" class="list-group-item active">我的评价</a>
            <a href="../myMoods" class="list-group-item">我的动态</a>
            <a href="../toFixPassword" class="list-group-item">修改密码</a>

        </div>
    </div>

    <div id="right_content">
        <nav class="nav state_bar">
            <ul class="nav nav-tabs">
                <c:if test="${status eq 'all'}">
                    <li role="presentation" class="active"><a href="#" itemStatus="all">所有评价</a></li>
                    <li role="presentation"><a href="#" itemStatus="waitReview">待评价</a></li>
                    <li role="presentation"><a href="#" itemStatus="reviewed">已评价</a></li>
                </c:if>
                <c:if test="${status eq 'waitReview'}">
                    <li role="presentation"><a href="#" itemStatus="all">所有评价</a></li>
                    <li role="presentation" class="active"><a href="#" itemStatus="waitReview">待评价</a></li>
                    <li role="presentation"><a href="#" itemStatus="reviewed">已评价</a></li>
                </c:if>

                <c:if test="${status eq 'reviewed'}">
                    <li role="presentation"><a href="#" itemStatus="all">所有评价</a></li>
                    <li role="presentation"><a href="#" itemStatus="waitReview">待评价</a></li>
                    <li role="presentation" class="active"><a href="#" itemStatus="reviewed">已评价</a></li>
                </c:if>
            </ul>
        </nav>

        <table class="table table-striped  table-hover">
            <thead>
            <tr>
                <th class="col-lg-3">订单号</th>
                <th class="col-lg-4">商品名称</th>
                <th class="col-lg-2">数量</th>
                <th class="col-lg-3">操作</th>
            <%--    往orderItem加一个状态--%>
            </tr>
            </thead>

            <tbody>
            <c:forEach items="${items}" var="item" varStatus="vs">
                <tr>
                        <%--订单号--%>
                    <td>${item.order.orderCode}</td>
                        <%--商品名称--%>
                    <td><a
                            href="${pageContext.request.contextPath}/fore/product/${item.product.pid}" class="review_pname">${item.product.pname}</a></td>
                        <%--商品数量--%>
                    <td>${item.number}</td>

                    <td>
                        <c:if test="${item.status eq 'waitReview'}">
                            <a href="${pageContext.request.contextPath}/space/toReviewPage?tid=${item.tid}">
                                <button class="btn btn-danger">评价</button>
                            </a>
                        </c:if>
                        <c:if test="${item.status eq 'reviewed'}">
                            <a href="${pageContext.request.contextPath}/fore/product/${item.product.pid}">
                                <button class="btn btn-success">查看</button>
                            </a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

    </div>
    <!--分页条-->
    <div class="pageDiv">
        <%@include file="../../include/admin/reviewPage.jsp" %>
    </div>
</div>
</body>
</html>
