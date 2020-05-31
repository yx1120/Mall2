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

            //更新用户信息
            $("#fixPassForm").submit(function () {
                $.post("${pageContext.request.contextPath}/space/checkPassword",
                    $(this).serialize(), function (info) {
                        if (info.flag) {
                            //替换right_content内容
                            let cf = $("#confirm_fix").removeAttr("display");
                            $("#right_content").html(cf.html());

                        } else {
                            $("#errorInfo").html(info.info);
                        }
                    }, 'json');
                return false;
            });
            //end.submit

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
            <a href="personalSpace" class="list-group-item">个人信息</a>
            <a href="myOrders/all" class="list-group-item">我的订单</a>
            <a href="myFavorites" class="list-group-item">我的收藏</a>
            <a href="myReviews/all" class="list-group-item">我的评价</a>
            <a href="toFixPassword" class="list-group-item active">修改密码</a>

        </div>
    </div>

    <div id="right_content">

        <form class="form-inline" action="#" id="fixPassForm" style="margin-left: 160px;margin-top: 120px;">
            <div class="form-group">
                <div class="input-group">
                    <div class="input-group-addon">原密码</div>
                    <input type="password" class="form-control" name="password" placeholder="password">
                </div>
            </div>
            <button type="submit" class="btn btn-primary">下一步</button>
            <div style="color: red; margin-left: 70px; margin-top:20px;"><span id="errorInfo"></span></div>
        </form>
    </div>

    <div id="confirm_fix" style="display: none">
        <form class="form-horizontal" action="${pageContext.request.contextPath}/space/setNewPassword" method="post"
              id="newPassForm" style="margin-left: 160px;margin-top: 120px;">
            <div class="form-group">
                <label for="pass_1" class="col-lg-2 control-label">新密码</label>
                <div class="col-lg-3">
                    <input type="password" class="form-control" id="pass_1" name="newPassword1">
                </div>
            </div>
            <div class="form-group">
                <label for="pass_2" class="col-lg-2 control-label">再次输入</label>
                <div class="col-lg-3">
                    <input type="password" class="form-control" id="pass_2" name="newPassword2">
                    <span id="errInfo"></span>

                </div>
            </div>

            <div class="form-group">
                <div class="col-lg-offset-2 col-lg-2 ">
                    <button type="submit" class="btn btn-default">确认修改</button>
                </div>
            </div>
        </form>
    </div>
</div>

</body>
</html>
