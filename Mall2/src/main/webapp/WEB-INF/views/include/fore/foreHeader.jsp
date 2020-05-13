<%--
  Created by IntelliJ IDEA.
  User: Yang xu
  Date: 2019/12/11
  Time: 21:58
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>

<style>

    #right_nav_div {
        float: right;
    }

    .home_color {
        color: #ff002d;
    }

    .headNav {
        font-size: 12px;
        font-family: Arial, serif;
        background-color: #f2f2f2;
        height: 30px;
        line-height: 30px;
    }

    div.left_nav_div a {
        margin-right: 16px;
        color: #a1a1a1;
    }

    #welcome {
        margin-right: 16px;
        color: #a1a1a1;
    }

    div.left_nav_div a:hover {
        color: #ff002d;
        text-decoration: none;
    }

    #img_code {
        width: 130px;
        height: 130px;
    }

    /*手机版二维码，使用绝对定位*/
    .code_div {
        width: 150px;
        height: 150px;
        padding: 10px;
        border: solid #a1a1a1 1px;
        position: absolute;
        right: 400px;
    }

    div.left_nav_div {
        width: 1200px;
        margin: 0 auto;
    }
</style>

<div class="headNav">
    <div class="left_nav_div">
        <nav>
            <a href="${pageContext.request.contextPath}/fore/home">
                <!--bootstrap里面的字体图标-->
                <span class="glyphicon glyphicon-home home_color"></span>
                商城首页
            </a>

            <span>
                <c:if test="${!empty user}">
                    <a href="#">${user.uname}</a>
                </c:if>

                <c:if test="${empty user}">
                    <a href="${pageContext.request.contextPath}/fore/login">请登录</a>
                </c:if>

                <c:if test="${empty user}">
                    <a href="${pageContext.request.contextPath}/fore/register">注册</a>
                </c:if>
            </span>

            <span id="right_nav_div">

                    <a href="${pageContext.request.contextPath}/space/personalSpace" is="personalCenter">
                        <span class="glyphicon glyphicon-user"></span>
                        个人中心</a>

                    <a href="${pageContext.request.contextPath}/shopCart/myCart">
                        <span class="iconfont icon-shouji3"></span>
                        购物车</a>

                    <c:if test="${!empty user}">
                        <a href="${pageContext.request.contextPath}/fore/logout" id="logout">
                        <span class="glyphicon glyphicon-off"></span>
                        退出</a>
                    </c:if>

                </span>
        </nav>
    </div>

    <div class="code_div" style="display: none">
        <!--二维码-->
        <img src="${pageContext.request.contextPath}/img/site/canvas.png" id="img_code" alt="手机二维码">
    </div>
</div>
