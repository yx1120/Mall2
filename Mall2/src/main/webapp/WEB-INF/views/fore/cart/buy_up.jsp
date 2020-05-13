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
    div.look_shopCar {
        width: 1200px;
        margin: 60px auto;
    }

    div.look_add {
        font-size: 20px;
        font-weight: bold;
        margin-bottom: 20px;
    }

    div.look_detailInfo {
        font-size: 12px;
        font-family: Arial;
    }

    span.look_star {
        color: #ff0036;
    }

    div.look_detailInfo input, textarea {
        margin-left: 50px;
        width: 360px;
    }

    div.look_detailInfo tr {
        height: 50px;
    }
</style>

<div class="foreWoreArea">
    <div class="look_shopCar">
        <div class="look_img">
            <img src="${pageContext.request.contextPath}/img/site/simpleLogo.png" class="pull-left">
            <img src="${pageContext.request.contextPath}/img/site/buyflow.png" class="pull-right">
            <div style="clear: both"></div>
        </div>
        <div class="look_add">
            输入收货地址
        </div>
        <div class="look_detailInfo">

                <table>
                    <tr>
                        <td>详细地址<span class="look_star">*</span></td>
                        <td><textarea placeholder="建议您如实填写详细收货地址，例如街道名称，门牌号码，楼层和房间号等信息" name="address"></textarea></td>
                    </tr>
                    <tr>
                        <td>邮政编码</td>
                        <td><input type="text" placeholder="请输入邮递区号" name="post"></td>
                    </tr>
                    <tr>
                        <td>收货人姓名<span class="look_star">*</span></td>
                        <td><input type="text" placeholder="长度不超过25个字符" name="receiver"></td>
                    </tr>
                    <tr>
                        <td>手机号码<span class="look_star">*</span></td>
                        <td><input type="text" placeholder="请输入11位手机号码" name="mobile"></td>
                    </tr>
                </table>

        </div>
    </div>
</div>

