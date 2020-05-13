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
    <title>我的订单-个人中心</title>
    <script src="${pageContext.request.contextPath}/js/jquery/jquery-3.2.1.min.js" type="text/javascript"></script>
    <link href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.min.css" rel="stylesheet">
    <script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js"></script>
    <link href="${pageContext.request.contextPath}/css/fore/iconfont.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/fore/space.css" rel="stylesheet">

    <script>
        $(function () {
            $("a[orderStatus]").click(function(){
                var orderStatus = $(this).attr("orderStatus");
                location.href = "${pageContext.request.contextPath}/space/myOrders/" + orderStatus + "";
            });
        });

        //删除订单
        function deleteOrder(data){
            if(confirm("确认删除？")){
                let oid = $(data).attr("oid");
                $.post("deleteOrder",{oid:oid},function (result) {
                    if("deleteSuccess"==result){
                        location.reload();
                    }else {
                        alert("删除失败")
                    }
                });
            }
        }

        //绑定详情按钮
        function getTable(ele) {
            let td = $(ele).parent().parent();
            //获取子节点table
            let tab = td.find("table").attr("id", "orderDetail");
            tab.prop("display","");
            $("#detailArea").html(tab.html());
        }
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
            <a href="all" class="list-group-item active">我的订单</a>
            <a href="../myFavorites" class="list-group-item">我的收藏</a>
            <a href="../myReviews/all" class="list-group-item">我的评价</a>
            <a href="../myMoods" class="list-group-item">我的动态</a>
            <a href="../toFixPassword" class="list-group-item">修改密码</a>

        </div>

    </div>

    <div id="right_content">
        <nav class="nav state_bar">
            <ul class="nav nav-tabs">
                <c:if test="${status eq 'all'}">
                    <li role="presentation" class="active"><a href="#" orderstatus="all" >所有订单</a></li>
                    <li role="presentation"><a href="#" orderstatus="waitPay" >待付款</a></li>
                    <li role="presentation"><a href="#" orderstatus="waitDelivery" >待发货</a></li>
                    <li role="presentation"><a href="#" orderstatus="waitConfirm" >待收货</a></li>
                </c:if>
                <c:if test="${status eq 'waitPay'}">
                    <li role="presentation"><a href="#" orderstatus="all">所有订单</a></li>
                    <li role="presentation"  class="active"><a href="#" orderstatus="waitPay" >待付款</a></li>
                    <li role="presentation"><a href="#" orderstatus="waitDelivery" >待发货</a></li>
                    <li role="presentation"><a href="#" orderstatus="waitConfirm" >待收货</a></li>
                </c:if>
                <c:if test="${status eq 'waitDelivery'}">
                    <li role="presentation"><a href="#" orderstatus="all">所有订单</a></li>
                    <li role="presentation"><a href="#" orderstatus="waitPay" >待付款</a></li>
                    <li role="presentation"  class="active"><a href="#" orderstatus="waitDelivery" >待发货</a></li>
                    <li role="presentation"><a href="#" orderstatus="waitConfirm" >待收货</a></li>
                </c:if>
                <c:if test="${status eq 'waitConfirm'}">
                    <li role="presentation"><a href="#" orderstatus="all">所有订单</a></li>
                    <li role="presentation"><a href="#" orderstatus="waitPay" >待付款</a></li>
                    <li role="presentation"><a href="#" orderstatus="waitDelivery" >待发货</a></li>
                    <li role="presentation"  class="active"><a href="#" orderstatus="waitConfirm" >待收货</a></li>
                </c:if>

            </ul>
        </nav>

        <table class="table table-striped  table-hover">
            <thead>
            <tr >
                <th class="col-lg-3">订单号</th>
                <th class="col-lg-2">金额</th>
                <th class="col-lg-2">商品数量</th>
                <th class="col-lg-2">操作</th>
                <th class="col-lg-1">详情</th>
                <th class="col-lg-2">创建时间</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach items="${orders}" var="order" varStatus="vs">
                <tr>
                        <%--订单号--%>
                    <td>${order.orderCode}

                    </td>
                        <%--金额--%>
                    <td>${order.total}</td>
                        <%--商品数量--%>
                    <td>${order.totalNumber}</td>

                    <td>

                        <c:if test="${order.status == 'waitConfirm'}">
                            <a href="${pageContext.request.contextPath}/space/confirmOrder?oid=${order.oid}">
                                <button class="btn btn-primary">确认收货</button>
                            </a>
                        </c:if>
                        <c:if test="${order.status == 'waitPay'}">
                            <a href="${pageContext.request.contextPath}/mallOrder/buy?oid=${order.oid}">
                                <button class="btn btn-danger">付款</button>
                            </a>
                        </c:if>
                        <c:if test="${order.status == 'waitDelivery'}">
                            <a href="${pageContext.request.contextPath}/space/deliveryOrder?oid=${order.oid}">
                                <button class="btn btn-warning">催货</button>
                            </a>
                        </c:if>
                        <c:if test="${order.status == 'finish'}">
                            <a href="#" disabled>
                                <button class="btn btn-success">完成</button>
                            </a>
                        </c:if>



                    </td>
                    <td>
                        <a href="#">
                            <button class="btn btn-info hideDetailBtn" onclick="getTable(this)">查看</button>
                        </a>

                        <table class="table table-hover" style="display: none" id="orderDetail">
                            <c:forEach items="${order.orderItems}" var="t">
                                <tr>
                                    <td><img
                                            src="${pageContext.request.contextPath}/img/productSingle/${t.product.firstProductImage.gid}.jpg"
                                            style="height: 40px;"></td>
                                    <td>${t.product.pname}</td>
                                    <td>${t.number}</td>
                                    <td>${t.product.orignalPrice}</td>
                                </tr>

                            </c:forEach>
                        </table>
                    </td>
                    <td>
                        <span>${order.createDate}</span>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <div id="detailArea" style="margin:30px;border:1px solid whitesmoke"></div>

    </div>

</div>

</body>
</html>
