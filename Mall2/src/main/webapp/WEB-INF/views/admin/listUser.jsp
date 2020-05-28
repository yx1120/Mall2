<%--
  Created by IntelliJ IDEA.
  User: Yang xu
  Date: 2019/12/5
  Time: 12:27
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="../include/admin/adminHeader.jsp" %>
<%@include file="../include/admin/adminNavigator.jsp" %>
<link href="${pageContext.request.contextPath}/css/back/admin_list.css" rel="stylesheet">

<title>用户管理</title>
<script>
    $(function () {
        $("ul.pagination li.disabled a").click(function () {
            return false;
        });
    });
</script>

<div class="workArea">
    <div class="myBread">
        <ol class="breadcrumb">
            <li><span>所有用户</span></li>
        </ol>
    </div>
    <%--用户表格--%>
    <div class="listDataTableDiv">
        <table class="table table-striped table-hover  table-condensed">

            <thead>
            <tr class="success row">
                <%--<th class="col-lg-6">ID</th>--%>
                <%--<th class="col-lg-12">用户名称</th>--%>
            </tr>
            </thead>

            <tbody>

            <c:forEach items="${list}" var="u" varStatus="vs">
                <tr class="row">
                    <td class="col-lg-1">${vs.count}</td>
                    <td class="col-lg-11">${u.uname}</td>
                </tr>
            </c:forEach>
            </tbody>

        </table>
    </div>

    <%--分页条--%>
    <div class="pageDiv">
        <%@include file="../include/admin/adminPage.jsp" %>
    </div>
</div>

<%@include file="../include/admin/adminFooter.jsp" %>