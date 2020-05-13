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
    <title>评价商品</title>
    <script src="${pageContext.request.contextPath}/js/jquery/jquery-3.2.1.min.js" type="text/javascript"></script>
    <link href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.min.css" rel="stylesheet">
    <script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js"></script>
    <link href="${pageContext.request.contextPath}/css/fore/iconfont.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/fore/space.css" rel="stylesheet">

</head>
<body>
<%@include file="../include/fore/foreHeader.jsp" %>

<div class="workspace">
    <div id="left_nav">
        <div class="list-group">
            <a href="#" class="list-group-item disabled">
                个人中心
            </a>
            <a href="personalSpace" class="list-group-item">个人信息</a>
            <a href="myOrders" class="list-group-item">我的订单</a>
            <a href="myFavorites" class="list-group-item">我的收藏</a>
            <a href="myReviews" class="list-group-item">我的评价</a>
            <a href="myMoods" class="list-group-item">我的动态</a>
            <a href="tofixPassword" class="list-group-item">修改密码</a>

        </div>
    </div>

    <div id="right_content">

        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">商品评价</h3>
            </div>
            <div class="panel-body">
                <div>
                    <span class="label label-primary">商品名称：</span>
                    <span class="">${item.product.pname}</span>
                </div>
                <form method="post" action="${pageContext.request.contextPath}/auth/review">
                    <table class="">
                        <tbody>
                        <tr>
                            <td class="col-lg-2">评价商品</td>
                            <td class="">
                                <textarea name="content" class="form-control" rows="4"></textarea>
                            </td>
                        </tr>
                        <hr>
                        </tbody>
                    </table>
                    <div style="margin-left: 90px;">
                        <hr>
                        <input type="hidden" name="oid" value="${item.order.oid}">
                        <input type="hidden" name="pid" value="${item.product.pid}">
                        <input type="hidden" name="tid" value="${item.tid}">
                        <button type="submit" class="btn btn-info">提交评价</button>
                    </div>
                </form>
            </div>
        </div>


    </div>
</div>

</body>
</html>
