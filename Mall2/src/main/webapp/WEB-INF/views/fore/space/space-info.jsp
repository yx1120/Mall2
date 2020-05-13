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
    <title>个人资料-个人中心</title>
    <script src="${pageContext.request.contextPath}/js/jquery/jquery-3.2.1.min.js" type="text/javascript"></script>
    <link href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.min.css" rel="stylesheet">
    <script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js"></script>
    <link href="${pageContext.request.contextPath}/css/fore/iconfont.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/fore/space.css" rel="stylesheet">

    <script>

        $(function () {

            //更新用户信息
            $("#updateUserInfo").submit(function () {
                //上面都满足才提交   ajax提交表单为json格式
                $.ajax({
                    url: '${pageContext.request.contextPath}/space/updateUser',
                    type: 'POST',
                    data: $(this).serialize(),
                    success: function (info) {
                        if (info.flag) {
                            $("#update_info").html("<font color='#20b2aa'>更新成功</font>");
                        }
                    }
                });
                return false;
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
            <a href="personalSpace" class="list-group-item active" >个人信息</a>
            <a href="myOrders/all" class="list-group-item">我的订单</a>
            <a href="myFavorites" class="list-group-item" >我的收藏</a>
            <a href="myReviews/all" class="list-group-item">我的评价</a>
            <a href="myMoods" class="list-group-item" >我的动态</a>
            <a href="toFixPassword" class="list-group-item" id="space-fixpass">修改密码</a>

        </div>

    </div>

    <div id="right_content">
        <form action="#" id="updateUserInfo">
            <ul class="list-group">
                <li class="list-group-item">昵称：<input value="${user.uname}" name="uname" type="text"></li>
                <li class="list-group-item">姓名：<input value="${user.realName}" name="realName"  type="text"></li>
                <li class="list-group-item">性别：
                    <c:if test="${user.sex eq '男'}">
                        <label><input type="radio" name="sex" value="男" checked>男</label>&nbsp&nbsp
                        <label><input type="radio" name="sex" value="女">女</label>
                    </c:if>
                    <c:if test="${user.sex eq '女'}">
                        <label><input type="radio" name="sex" value="男" >男</label>&nbsp&nbsp
                        <label><input type="radio" name="sex" value="女" checked>女</label>
                    </c:if>
                    <c:if test="${empty user.sex}">
                        <label><input type="radio" name="sex" value="男">男</label>&nbsp&nbsp
                        <label><input type="radio" name="sex" value="女">女</label>
                    </c:if>

                </li>
                <li class="list-group-item">生日：<input value="<fmt:formatDate value="${user.birthday}" pattern="yyyy-MM-dd"/>" name="birthday" type="date"></li>
                <li class="list-group-item">QQ 邮箱：<input value="${user.email}" name="email" type="text"></li>
                <li class="list-group-item">电话号码：${user.telNum}</li>
                <li class="list-group-item">注册时间：<fmt:formatDate value="${user.createTime}" pattern="yyyy-MM-dd"/></li>
            </ul>
            <input type="submit" value="更新" class="btn btn-info">
            <span id="update_info"></span>
        </form>
    </div>
</div>

</body>
</html>
