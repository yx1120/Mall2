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


<style>
    div.buyPageDiv {
        margin: 20px auto;
        max-width: 1200px;
    }

    div.addressTip, div.productListTip {
        color: #333333;
        font-size: 16px;
        font-weight: bold;
        text-align: left;
        margin-bottom: 30px;
    }

    table.productListTable {
        width: 100%;
        border-collapse: separate;
        font-size: 12px;
    }

    table.productListTable th {
        color: #999999;
        font-family: 宋体;
        font-weight: normal;
        font-size: 12px;
        text-align: center;
        padding-bottom: 5px;
    }

    table.productListTable tr.rowborder td {
        background-color: #b2d1ff;
        border-right: 2px solid #fff;
        height: 3px;
    }

    th.productListTableFirstColumn {
        text-align: left !important;
    }

    img.tmallbuy {
        width: 15px;
    }

    a.marketLink {
        color: black;
        font-size: 12px;
        font-family: 宋体;
        font-weight: normal;
    }

    a.marketLink:hover {
        color: black;
        font-size: 12px;
        text-decoration: underline;
        font-family: 宋体;
        font-weight: normal;
    }

    span.wangwangGif {
        display: inline-block;
        width: 25px;
        height: 25px;
        /*background-image: url(../../img/site/wangwang.gif);*/
        background-repeat: no-repeat;
        background-color: transparent;
        background-attachment: scroll;
        background-position: -83px -0px;
        position: relative;
        top: 8px;
        left: 2px;
    }

    tbody.productListTableTbody td {
        text-align: center;
    }

    tbody.productListTableTbody td.orderItemFirstTD {
        text-align: left;
    }

    tr.orderItemTR td {
        border-bottom: 1px solid #E5E5E5;
    }

    tr.orderItemTR td {
        padding: 10px 0px;
    }

    img.orderItemImg {
        width: 50px;
        height: 50px;
        border: 1px solid #E9E9E9;
    }

    td.orderItemProductInfo {
        text-align: left !important;
    }

    td.orderItemProductInfo img {
        height: 16px;
    }

    a.orderItemProductLink {
        color: #666666;
        display: block;
    }

    a.orderItemProductLink:hover {
        color: #666666;
        text-decoration: underline;
    }

    span.orderItemProductPrice, span.orderItemProductNumber {
        color: #000000;
    }

    span.orderItemUnitSum {
        color: #CC0000;
        font-weight: bold;
    }

    td.orderItemFirstTD, td.orderItemLastTD {
        border-bottom: 0px solid black !important;
    }

    label.orderItemDeliveryLabel {
        color: #666666;
        font-family: 宋体;
        font-size: 12px;
        font-weight: normal;
    }

    select.orderItemDeliverySelect {
        width: 100px;
        height: 23px;
    }

    div.orderItemSumDiv {
        padding: 20px;
        border-top: 2px solid #B4D0FF;
        background-color: #F2F6FF;
        height: 50px;
    }

    div.orderItemSumDiv span {
        color: #999999;
    }

    span.leaveMessageText {
        display: inline-block;
        margin-right: 10px;
        float: left;
    }

    div.orderItemTotalSumDiv {
        margin: 40px;
        height: 40px;
    }

    div.submitOrderDiv {
        height: 40px;
        margin: 20px 0px;
    }

    div.orderItemTotalSumDiv span {
        color: #999999;
    }

    span.orderItemTotalSumSpan {
        color: #C40000 !important;
        font-size: 22px;
        font-weight: bold;
        border-bottom: 1px dotted #F2F6FF;
    }

    button.submitOrderButton {
        border: 1px solid #C40000;
        background-color: #C40000;
        text-align: center;
        line-height: 40px;
        font-size: 14px;
        font-weight: 700;
        color: white;
        float: right;
        display: inline-block;
        margin: 0px 10px;
        width: 180px;
        height: 40px;
    }

    body {
        font-size: 12px;
        font-family: Arial;
    }
</style>
<script>
    $(function () {
        $("img.leaveMessageImg").click(function () {
            $(this).hide();
            $("span.leaveMessageTextareaSpan").show();
            $("div.orderItemSumDiv").css("height", "100px");
        });
    });
</script>

<div class="foreWoreArea">
    <div class="buyPageDiv">
        <div class="productList">
            <div class="productListTip">确认订单信息</div>
            <table class="productListTable">
                <thead>
                <tr>
                    <th class="productListTableFirstColumn" colspan="2">
                        <img src="${pageContext.request.contextPath}/img/site/tmallbuy.png" class="tmallbuy">
                        <a href="#" class="marketLink">店铺：天猫店铺</a>
                        <a href="#" class="wangwanglink"> <span class="wangwangGif"></span> </a>
                    </th>
                    <th>单价</th>
                    <th>数量</th>
                    <th>小计</th>
                    <th>配送方式</th>
                </tr>
                <tr class="rowborder">
                    <td colspan="2"></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                </thead>
                <tbody class="productListTableTbody">
                <c:forEach items="${orderItemList}" var="item" varStatus="vs">
                    <tr class="orderItemTR">
                        <td class="orderItemFirstTD"><img width="20px"
                                                          src="${pageContext.request.contextPath}/img/productSingle_middle/${item.product.firstProductImage.gid}.jpg"
                                                          class="orderItemImg">
                        </td>

                        <td class="orderItemProductInfo">
                            <a class="orderItemProductLink" href="#">
                                    ${item.product.pname}
                            </a>
                            <img title="支持信用卡支付" src="${pageContext.request.contextPath}/img/site/creditcard.png">
                            <img title="消费者保障服务,承诺7天退货" src="${pageContext.request.contextPath}/img/site/7day.png">
                            <img title="消费者保障服务,承诺如实描述" src="${pageContext.request.contextPath}/img/site/promise.png">
                        </td>

                        <td>
                            <span class="orderItemProductPrice">￥${item.product.promotePrice}</span>
                        </td>

                        <td>
                            <span class="orderItemProductNumber">${item.number}</span>
                        </td>

                        <td>
                            <span class="orderItemUnitSum">￥
                            <fmt:formatNumber value="${item.number * item.product.promotePrice}" pattern="##.##"
                                              minFractionDigits="2"/>

                            </span>
                        </td>

                        <td class="orderItemLastTD" rowspan="5">
                            <label class="orderItemDeliveryLabel">
                                <input type="radio" checked="checked" value="">
                                普通配送
                            </label>
                            <select class="orderItemDeliverySelect">
                                <option>快递 免邮费</option>
                            </select>
                        </td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>


            <div class="orderItemSumDiv">
                <div class="pull-left">
                    <span class="leaveMessageText">给卖家留言:</span>
                    <span>
                    <img src="${pageContext.request.contextPath}/img/site/leaveMessage.png" class="leaveMessageImg">
                </span>
                    <span class="leaveMessageTextareaSpan" style="display: none;">
                    <textarea class="leaveMessageTextarea" name="userMessage"></textarea>
                    <div>
                        <span>还可以输入200个字符</span>
                    </div>
                </span>
                </div>
                <span class="pull-right">店铺合计(含运费): ￥
                    <fmt:formatNumber value="${totalPrice}" pattern="##.##" minFractionDigits="2"/>
                </span>
            </div>
        </div>


        <div class="orderItemTotalSumDiv">
            <div class="pull-right">
                <span>实付款：</span>
                <span class="orderItemTotalSumSpan">￥
                    <fmt:formatNumber value="${totalPrice}" pattern="##.##" minFractionDigits="2"/>
                </span>
            </div>
        </div>

        <div class="submitOrderDiv">
            <%--点击提交上面的详细信息表单--%>
            <button class="submitOrderButton" type="button">提交订单</button>

        </div>
    </div>
</div>


