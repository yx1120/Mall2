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
            <li><a href="${pageContext.request.contextPath}/admin/firstCategory/list">所有分类</a></li>
            <li class="active">${category.cname}</li>
            <li class="active">属性</li>
        </ol>

        <ol class="breadcrumb">
            <div >
                <form method="post" id="addPropertyForm" action="add">
                    <table class="addTable">
                        <tr>
                            <td class="cate_text">属性名称</td>
                            <td ><input id="name" name="pyname" type="text" class="form-control" placeholder="输入属性名称"></td>

                            <td >
                                <input hidden name="cid" value="${category.cid} ">
                                <button type="submit" class="btn btn-danger">新 增</button>
                            </td>
                        </tr>

                    </table>
                </form>
            </div>
            <div style="clear: both"></div>
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
                        <a href="edit?pyid=${p.pyid}"><span class="glyphicon glyphicon-edit"></span></a>
                    </td>
                    <td>
                        <a deleteLink="true" href="delete?pyid=${p.pyid}"><span
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

</div>

<%@include file="../include/admin/adminFooter.jsp" %>
