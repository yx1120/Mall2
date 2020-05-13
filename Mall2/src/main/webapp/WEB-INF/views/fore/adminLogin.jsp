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
<html>
<head>
    <title>管理登录</title>
    <script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery/jquery-3.2.1.min.js" type="text/javascript"></script>
    <link href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/fore/iconfont.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/fore/login_register.css" rel="stylesheet">

</head>
<body>
<%@include file="../include/fore/foreHeader.jsp" %>

<div class="foreWoreArea">

    <div class="form_div">
        <div id="formId">
            <form action="${pageContext.request.contextPath}/fore/toAdminLogin" method="post" id="login_form">
                <table>
                    <tr>
                        <td><span class="login_txt">用户名</span></td>
                        <td colspan="2"><input type="text" class="form-control " placeholder="Username"
                                               id="log_name" name="name"></td>
                        <td><span id="error_info"></span></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td><span class="login_txt">密&nbsp&nbsp&nbsp&nbsp码</span></td>
                        <td colspan="2"><input type="password" class="form-control" id="log_pass"
                                               placeholder="Password" name="password"></td>
                        <td></td>

                        <td></td>
                    </tr>

                    <tr>
                        <td colspan="4">
                            <button type="submit" class="btn btn-default login_btn">登录
                            </button>
                        </td>
                        <td></td>

                        <td></td>
                        <td></td>
                    </tr>
                </table>
            </form>

        </div>
    </div>
</div>

</body>
</html>
