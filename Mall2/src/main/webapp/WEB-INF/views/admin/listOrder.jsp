<%--
  Created by IntelliJ IDEA.
  User: Yang xu
  Date: 2019/12/5
  Time: 12:27
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix='fmt' %>

<%@include file="../include/admin/adminHeader.jsp" %>
<%@include file="../include/admin/adminNavigator.jsp" %>

<link href="${pageContext.request.contextPath}/css/back/admin_list.css" rel="stylesheet">

<title>订单管理</title>

<script>
    $(function () {
        $(".hideDetailBtn").click(function () {
            $("#detail_id_div").css("display", "none");
            $("#detailArea").html("");
        });

        //发货
        $(".deliveryBtn").click(function () {
            let oid = $(this).attr("oid");
            location.href = "${pageContext.request.contextPath}/order/admin_deliveryOrder?oid="+oid+"";
        });
    });


    //绑定详情按钮
    function getTable(oid) {
        let detailTable = $("div[oid="+oid+"]");
        let detailContent = $("#detail_id_div");
        detailContent.css("display","");
        detailContent.html(detailTable.html());
    }
</script>

<div class="workArea">
    <div class="myBread">
        <ol class="breadcrumb">
            <li><span>所有订单</span></li>
        </ol>
    </div>

    <div class="product_table ">
        <table class="table table-striped table-hover">
            <thead>
            <tr class="">
                <th>ID</th>
                <th>状态</th>
                <th>金额</th>
                <th>商品数量</th>
                <th>卖家名称</th>
                <th>创建时间</th>
                <th>支付时间</th>
                <th>发货时间</th>
                <th>确认收货时间</th>
                <th>操作</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach items="${list}" var="o" varStatus="vs">
                <tr>
                    <td>${vs.count}</td>
                        <%--状态--%>
                    <td>${o.orderCode}</td>
                        <%--金额--%>
                    <td>${o.total}</td>
                        <%--商品数量--%>
                    <td>${o.totalNumber}</td>
                        <%--用户名--%>
                    <td>${o.user.uname}</td>
                        <%--创建日期--%>
                    <td><fmt:formatDate value="${o.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                        <%--支付时间--%>
                    <td><fmt:formatDate value="${o.payDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                        <%--发货时间--%>
                    <td><fmt:formatDate value="${o.deliveryDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                        <%--确认收货时间--%>
                    <td><fmt:formatDate value="${o.confirmDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>

                    <%--操作【发货/详情】--%>
                    <td>
                        <%--分析：点击按钮，获的父元素。通过父元素获得最后一个子元素table--%>
                        <button class="btn btn-primary btn-sm" id="orderDetailBtn" onclick="getTable(${o.oid})" >
                            详情
                        </button>
                        <c:if test="${o.status == 'waitDelivery'}">
                            <a class="btn btn-primary btn-sm deliveryBtn" oid="${o.oid}">发货</a>
                        </c:if>

                        <%--塞在这里算了   订单详情   遍历所有订单项
                           这里id只能用一次,所以只有第一个有用
                        --%>
                        <div oid="${o.oid}" hidden>
                                <%--Helloworld--%>
                            <div class="panel-heading">订单详情</div>
                            <table class="table" >
                                <c:forEach items="${o.orderItems}" var="t">
                                    <tr>
                                        <td><img src="${pageContext.request.contextPath}/img/productSingle/${t.product.firstProductImage.gid}.jpg"
                                                 style="height: 40px;"></td>
                                        <td>${t.product.pname}</td>
                                        <td>${t.number}</td>
                                        <td>${t.product.orignalPrice}</td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <!--分页条-->
    <div class="pageDiv">
        <%@include file="../include/admin/adminPage.jsp" %>
    </div>

    <div class="detail_div panel panel-default" style="display: none" id="detail_id_div">

    </div>

</div>

<%@include file="../include/admin/adminFooter.jsp" %>
