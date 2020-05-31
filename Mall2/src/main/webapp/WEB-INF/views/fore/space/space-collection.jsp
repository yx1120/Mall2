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
    <title>我的收藏-个人中心</title>
    <script src="${pageContext.request.contextPath}/js/jquery/jquery-3.2.1.min.js" type="text/javascript"></script>
    <link href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.min.css" rel="stylesheet">
    <script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js"></script>
    <link href="${pageContext.request.contextPath}/css/fore/iconfont.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/fore/space.css" rel="stylesheet">

    <script>

        $(function () {
            //取消收藏
            $(".deleteFavorite").click(function () {
                if (confirm("确认删除？")) {
                    //获取pid
                    let pid = $(this).attr("pid");
                    $.post("${pageContext.request.contextPath}/shopCart/addAndDelFavorite", {
                        pid: pid,
                        "status": "del"
                    }, function (info) {
                        if (info == "delSuccess") {
                            location.reload();
                        }
                    });
                }
            });
        })
    </script>
    <style>
        .delImg:hover{
            width:30px;
        }
    </style>
</head>
<body>
<%@include file="../../include/fore/foreHeader.jsp" %>

<div class="workspace">
    <div id="left_nav">
        <div class="list-group">
            <a href="#" class="list-group-item disabled">
                个人中心
            </a>
            <a href="personalSpace" class="list-group-item">个人信息</a>
            <a href="myOrders/all" class="list-group-item">我的订单</a>
            <a href="myFavorites" class="list-group-item active">我的收藏</a>
            <a href="myReviews/all" class="list-group-item">我的评价</a>
            <a href="toFixPassword" class="list-group-item">修改密码</a>
        </div>

    </div>

    <div id="right_content">
        <table class="table table-striped  table-hover">

            <thead>
            <tr class="info row">
                <th class="col-lg-1">图片</th>
                <th class="col-lg-5">商品名称</th>
                <th class="col-lg-4">收藏时间</th>
                <th class="col-lg-2">操作</th>
            </tr>
            </thead>

            <tbody>

            <c:forEach items="${list}" var="favorite" varStatus="vs">
                <tr class=" row">
                    <th class="col-lg-1"><img style="height: 40px" src="${pageContext.request.contextPath}/img/productSingle/${favorite.product.firstProductImage.gid}.jpg"></th>
                    <th class="col-lg-5"><a
                            href="${pageContext.request.contextPath}/fore/product/${favorite.product.pid}">${favorite.product.pname}</a>
                    </th>
                    <th class="col-lg-4"><fmt:formatDate value="${favorite.fdate}" pattern="yyyy-MM-dd"/></th>
                    <th class="col-lg-2">
                        <a href="#" class="deleteFavorite" pid="${favorite.product.pid}">
                            <img src="${pageContext.request.contextPath}/img/del.png" alt="取消收藏" title="取消收藏"
                                 class="delImg" width="28px">
                        </a>
                    </th>
                </tr>
            </c:forEach>

            </tbody>

        </table>
    </div>
</div>

</body>
</html>
