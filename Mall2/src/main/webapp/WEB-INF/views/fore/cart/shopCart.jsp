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
    <title>myShopCart</title>
    <script src="${pageContext.request.contextPath}/js/jquery/jquery-3.2.1.min.js" type="text/javascript"></script>
    <link href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.min.css" rel="stylesheet">
    <script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js"></script>
    <link href="${pageContext.request.contextPath}/css/fore/iconfont.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/fore/shopCart.css" rel="stylesheet">

    <script>
        $(function () {

            //全选按钮
            //点击：先把自己改变，然后改变订单的"勾选图片"
            $(".choseAll").click(function () {

                let smBtn1 = $("#sm_btn1");
                let smBtn2 = $("#sm_btn2");
                let all = $(this).attr("isAll");
                let choneOneBtn = $(".choneOne");
                //自定义属性,src是固有属性
                if (all == "notAll") {
                    $(this).prop("src", "${pageContext.request.contextPath}/img/site/cartSelected.png");

                    choneOneBtn.prop("src", "${pageContext.request.contextPath}/img/site/cartSelected.png");
                    choneOneBtn.attr("isChose", "yes");
                    $(this).attr("isAll", "All");


                    smBtn1.css("background", "#C40000");
                    smBtn2.css("background", "#C40000");
                    smBtn1.removeAttr("disabled");
                    smBtn2.removeAttr("disabled");

                } else {
                    //取消全选
                    $(this).prop("src", "${pageContext.request.contextPath}/img/site/cartNotSelected.png");

                    choneOneBtn.prop("src", "${pageContext.request.contextPath}/img/site/cartNotSelected.png");
                    choneOneBtn.attr("isChose", "no");
                    $(this).attr("isAll", "notAll");

                    //设置金额
                    $/**/(".allPrice").html(0);

                    smBtn1.css("background", "#AAAAAA");
                    smBtn1.attr("disabled", "disabled");
                    smBtn2.css("background", "#AAAAAA");
                    smBtn2.attr("disabled", "disabled");
                }
            });

            //选中购物车某些项生成订单
            $("#sm_btn2").click(function () {
                var params = "";
                $(".choneOne").each(function () {
                    if ($(this).attr("isChose") == "yes") {
                        let tid = $(this).attr("tid");
                        params += "&tid=" + tid;
                    }
                });
                params = params.substring(1);
                location.href = "${pageContext.request.contextPath}/mallOrder/buy?" + params;

            });
        });

        //订单的单选按钮：
        function choseOneItem(imgBtn) {
            let isChose = $(imgBtn).attr("isChose");
            let choseAllBtn = $(".choseAll");
            let smBtn1 = $("#sm_btn1");
            let smBtn2 = $("#sm_btn2");
            // alert(isChose);
            if (isChose == "no") {
                //勾选商品
                //判断全选状态：

                $(imgBtn).prop("src", "${pageContext.request.contextPath}/img/site/cartSelected.png");
                $(imgBtn).attr("isChose", "yes");

                //设置结算的金额
                let allPrice = nowPrice();
                // alert(allPrice);
                // $(".allPrice").html(allPrice);

                //设置完成后再判断
                isAllFlag = isSelectedAll();
                if (isAllFlag) {
                    let choseAllObj = $(".choseAll");
                    choseAllObj.each(function () {
                        $(this).prop("src", "${pageContext.request.contextPath}/img/site/cartSelected.png");
                        $(this).attr("isAll", "All");
                    });
                }

                //1.某一个被选中，结算按钮变红(获取已经选择的商品，计算总额）。并且变为可点击状态
                smBtn1.css("background", "#C40000");
                smBtn2.css("background", "#C40000");
                smBtn1.removeAttr("disabled");
                smBtn2.removeAttr("disabled");
            } else {
                //取消某一个选择。全选状态破坏
                choseAllBtn.prop("src", "${pageContext.request.contextPath}/img/site/cartNotSelected.png");
                choseAllBtn.attr("isAll", "notAll");

                //设置结算的金额
                let allPrice = nowPrice();

                $(imgBtn).prop("src", "${pageContext.request.contextPath}/img/site/cartNotSelected.png");
                $(imgBtn).attr("isChose", "no");

                //3.全部被取消选中，（结算按钮，设置为不可点击状态，颜色变成灰色）
                if (isNotSelectedAll()) {
                    smBtn1.css("background", "#AAAAAA");
                    smBtn1.attr("disabled", "disabled");
                    smBtn2.css("background", "#AAAAAA");
                    smBtn2.attr("disabled", "disabled");
                }
            }
        }

        //增加，减少购买商品的数量
        //减少，不能为0.除非自己删除
        function cut(data) {
            //先获取父td,然后获取td的孩子input
            var td = $(data).parent();
            //获取子节点table
            var input = td.find("input").attr("class", "saleNum");
            let tid = input.attr("tid");

            let number = input.val();
            if (number <= 1) {
                input.val(number);
            } else {
                input.val(--number);
            }

            //减少数量ajax
            $.post("${pageContext.request.contextPath}/shopCart/fixNumber", {tid: tid, number: number});
        }

        //增加，可以一直增加。增加数量的同时要进行价格改变
        function add(data) {
            //先获取父td,然后获取td的孩子input
            var td = $(data).parent();
            //获取子节点table
            var input = td.find("input").attr("class", "saleNum");
            let tid = input.attr("tid");

            let number = input.val();
            input.val(++number);

            //增加数量ajax
            $.post("${pageContext.request.contextPath}/shopCart/fixNumber", {tid: tid, number: number});
        }


        //监听函数：
        //1.某一个被选中，结算按钮变红(获取已经选择的商品，计算总额）。并且变为可点击状态
        //2.取消一个选中，（1，是否全部被取消（function1）,减少总额，
        //3.全部被取消选中，（结算按钮，设置为不可点击状态，颜色变成灰色）
        //4.需要抽取的函数：
        //1.结算按钮的css变化
        //2.全选后。全选按钮的变化（被勾选）
        //3.

        //全选监听 boolean返回值
        function isSelectedAll() {
            //获取所有子项
            let ones = $(".choneOne");
            let flag = true;
            //判断是否isChose的值
            ones.each(function () {
                if ($(this).attr("isChose") == "no") {
                    flag = false;
                }
            });
            return flag;
        }

        //全不选监听 boolean返回值
        function isNotSelectedAll() {
            //获取所有子项
            let ones = $(".choneOne");
            let flag = true;

            //判断是否isChose的值
            ones.each(function () {
                if ($(this).attr("isChose") == "yes") {
                    flag = false;
                }
            });
            return flag;
        }

        //结算价格更新函数
        //每次添加或者删除都要更新
        function nowPrice() {
            //获取已经选中的  //位于td
            let ones = $(".choneOne");
            let allPrice = 0;
            //判断是否isChose的值
            ones.each(function () {
                if ($(this).attr("isChose") == "yes") {
                    //获取tr
                    let mytr = $(this).parent().parent();
                    let price = mytr.find("span").attr("class", "singleRealPrice").text();
                    allPrice += price;
                }
            });

            return allPrice;
        }

        //删除 ajax
        function deleteItem(data) {
            if (confirm("确认删除？")) {
                let tid = $(data).attr("tid");
                $.post("${pageContext.request.contextPath}/shopCart/deleteOrderItem", {tid: tid}, function (info) {
                    if (info.flag) {
                        location.reload();
                    }else{
                        alert("删除失败")
                    }
                });
            }
        }
    </script>
</head>
<body>
<%@include file="../../include/fore/foreHeader.jsp" %>

<div class="foreWoreArea">
    <div class="settlement_div">
        <div class="sm_up pull-right">
            <span>已选商品 (不含运费) </span>
            <span class="sm_price">￥<span class="allPrice"></span></span>
            <!--如果都不选的话，加一个disabled属性。有其中一个选了的话，删除这个属性-->
            <button id="sm_btn1" class="btnToGray" disabled>结算</button>
        </div>
        <div class="sm_me" style="clear: both">
            <!--    订单内容-->
            <table class="table">
                <thead>
                <tr>
                    <th><img src="${pageContext.request.contextPath}/img/site/cartNotSelected.png" class="choseAll"
                             isAll="notAll" alt="1">
                        <span>全选</span></th>
                    <th>商品信息</th>
                    <th>单价</th>
                    <th>数量</th>
                    <th>金额</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>

                <c:forEach items="${cartItems}" var="item" varStatus="vs">
                    <tr>
                        <td>
                            <img src="${pageContext.request.contextPath}/img/site/cartNotSelected.png" class="choneOne"
                                 isChose="no" onclick="choseOneItem(this)" tid="${item.tid}" alt="1">
                            <a href="product?pid=${item.product.pid}">
                                <img src="${pageContext.request.contextPath}/img/productSingle_middle/${item.product.firstProductImage.gid}.jpg"
                                     height="90px" width="90px" alt="1">
                            </a>
                        </td>
                        <td>
                            <div class="product_infos">
                                <a href="product?pid=${item.product.pid}"
                                   class="sm_product_nameTitle">${item.product.pname}</a>
                            </div>
                        </td>
                        <td>
                            <span class="two_price"><del>￥<fmt:formatNumber value="${item.product.orignalPrice}"
                                                                            pattern="##.##"
                                                                            minFractionDigits="2"/></del></span>
                            <span class="sm_realPrice two_price">
                                ￥<span class="singleRealPrice"><fmt:formatNumber value="${item.product.promotePrice}"
                                                                                 pattern="##.##"
                                                                                 minFractionDigits="2"/></span>
                            </span>
                        </td>
                        <td>
                            <a href="#" onclick="cut(this)" class="handle_a cutNum_a">-</a>
                                <%--商品数量调整输入框--%>
                            <span>
                                <input type="text" name="productNum" class="saleNum"
                                       value="${item.number}" tid="${item.tid}">
                            </span>
                            <a href="#" onclick="add(this)" class="handle_a addNum_a">+</a>
                        </td>
                        <td>
                            <span class="sm_realPrice" id="oneItemPrice">￥
                                <fmt:formatNumber value="${item.number * item.product.promotePrice}" pattern="##.##"
                                                  minFractionDigits="2"/>
                            </span>
                        </td>
                        <td><a href="#" onclick="deleteItem(this)" tid="${item.tid}">删除</a></td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>

        </div>

        <%--提交--%>
        <div class="sm_down">
            <div id="sm_img">
                <img src="${pageContext.request.contextPath}/img/site/cartNotSelected.png" class="choseAll"
                     isAll="notAll" alt="1">
                <span>全选</span>
            </div>
            <div class="pull-right">
                <span>已选商品 <span class="sm_price">0</span> 件 合计 (不含运费): </span>
                <span class="sm_price sm_price2">￥<span class="allPrice"></span></span>
                <!--需要获取上面的tid和每个商品的数量，提交到-->
                <button id="sm_btn2" class="btnToGray" disabled>结算</button>
            </div>
        </div>
    </div>
</div>

<%--使用一个独立的页面--%>
<%--<%@include file="../../include/fore/foreFooter.jsp" %>--%>
<%--使用一个独立的页面--%>
</body>
</html>
