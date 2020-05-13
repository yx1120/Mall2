<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<style>
    #logout {
        float: right;
        margin-right: 36px;
    }

    #logout:hover {
        color: #ff0036;
    }
</style>

<div >
    <nav class="navbar  navbar-fixed-top navbar-inverse">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/fore/home" style="margin-left: 30px;">
            <span class="glyphicon glyphicon-home" title="主页"></span>
        </a>

        <a class="navbar-brand" href="${pageContext.request.contextPath}/firstCategory/admin_list">分类管理</a>
		<a class="navbar-brand" href="${pageContext.request.contextPath}/user/admin_list">用户管理</a>
		<a class="navbar-brand" href="${pageContext.request.contextPath}/order/admin_list">订单管理</a>
        <a class="navbar-brand" href="${pageContext.request.contextPath}/carousel/admin_list">轮播图</a>

        <a class="navbar-brand" href="${pageContext.request.contextPath}/fore/toAdminLogout" id="logout">
            <span class="glyphicon glyphicon-off" title="注销"></span>
        </a>
	</nav>
</div>