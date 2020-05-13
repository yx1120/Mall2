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


<title>属性管理</title>
<div class="workArea">
    <div class=" myBread">
        <ol class="breadcrumb">
            <li><a href="${pageContext.request.contextPath}/firstCategory/admin_list">所有分类</a></li>
            <li class="active">${category.cname}</li>
            <li class="active">属性</li>
        </ol>
    </div>

    <div class="product_table ">
        <table class="table table-striped  table-hover">
            <thead>
            <tr class="success">
                <%--<th>ID</th>--%>
                <th>属性名称</th>
                <th>编辑</th>
                <th>删除</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach items="${list}" var="p" varStatus="vs">
                <tr>
                        <%--<td>${vs.count}</td>--%>
                    <td>${p.pyname}</td>
                    <td>
                        <a href="admin_edit?pyid=${p.pyid}"><span class="glyphicon glyphicon-edit"></span></a>
                    </td>
                    <td>
                        <a deleteLink="true" href="admin_delete?pyid=${p.pyid}"><span
                                class="glyphicon glyphicon-trash"></span></a>
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

    <!--   新增商品-->
    <div class="panel panel-warning property_addDiv">
        <div class="panel-heading">新增属性</div>
        <div class="panel-body">

            <form method="post" id="addForm" action="admin_add">
                <table class="addTable">
                    <tr>
                        <td class="cate_text">属性名称</td>
                        <td><input name="pyname" type="text" class="form-control"></td>
                    </tr>
                    <tr class="submitTR">
                        <td colspan="2" align="center">
                            <%--    加一个隐藏域提交cid--%>
                            <input name="cid" type="hidden" value="${category.cid}" class="form-control">
                            <button type="submit" class="btn btn-success">提 交</button>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</div>

<%@include file="../include/admin/adminFooter.jsp" %>
