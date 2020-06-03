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


<title>产品管理</title>
<div class="workArea">
    <div class="myBread">
        <ol class="breadcrumb">
            <li><a href="${pageContext.request.contextPath}/admin/firstCategory/list">所有分类</a></li>
            <li>
                <a href="${pageContext.request.contextPath}/admin/category/list?parentId=${category.parentCategory.cid}">${category.parentCategory.cname}</a>
            </li>
            <li class="active">${category.cname}</li>
        </ol>
    </div>

    <div class="product_table ">
        <table class="table table-striped table-hover">
            <thead>
            <tr class="info">
                <%--<th>ID</th>--%>
                <th  class="col-lg-1">图片</th>
                <th  class="col-lg-3">产品名称</th>
                <th  class="col-lg-1">小标题</th>
                <th  class="col-lg-1">原价</th>
                <th  class="col-lg-1">优惠价</th>

                <th  class="col-lg-1">库存</th>
                <th  class="col-lg-1">图片设置</th>
                <th  class="col-lg-1">属性设置</th>
                <th  class="col-lg-1">编辑</th>
                <th  class="col-lg-1">删除</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach items="${list}" var="p" varStatus="vs">
                <tr>
                        <%--<td>${vs.count}</td>--%>
                    <td><img style="height: 40px" src="${pageContext.request.contextPath}/img/productSingle/${p.firstProductImage.gid}.jpg"></td>
                    <td>${p.pname}</td>
                    <td>${p.subTitle}</td>
                    <td>${p.orignalPrice}</td>
                    <td>${p.promotePrice}</td>
                    <td>${p.stock}</td>

                    <!--图片管理-->
                    <td><a href="${pageContext.request.contextPath}/admin/productImage/list?pid=${p.pid}"><span
                            class="glyphicon glyphicon-picture"></span></a></td>
                        <%--设置属性--%>
                    <td><a href="editPropertyValue?pid=${p.pid}"><span
                            class="glyphicon glyphicon-list"></span></a></td>

                    <td><a href="edit?pid=${p.pid}"><span class="glyphicon glyphicon-edit"></span></a>
                    </td>
                    <td><a deleteLink="true" href="delete?pid=${p.pid}"><span
                            class="glyphicon glyphicon-trash"></span></a></td>
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
    <div class="panel panel-info product_addDiv">
        <div class="panel-heading">新增商品</div>
        <div class="panel-body">

            <form method="post" id="addForm" action="add">
                <table class="addTable">
                    <tr>
                        <td class="cate_text">产品名称</td>
                        <td><input name="pname" type="text" class="form-control"></td>
                    </tr>
                    <tr>
                        <td class="cate_text">小标题</td>
                        <td><input name="subTitle" type="text" class="form-control"></td>
                    </tr>
                    <tr>
                        <td class="cate_text">原价</td>
                        <td><input name="orignalPrice" type="text" class="form-control"></td>
                    </tr>
                    <tr>
                        <td class="cate_text">优惠价</td>
                        <td><input name="promotePrice" type="text" class="form-control"></td>
                    </tr>
                    <tr>
                        <td class="cate_text">库存</td>
                        <td><input name="stock" type="text" class="form-control"></td>
                    </tr>
                    <tr class="submitTR">
                        <td colspan="2" align="center">
                            <%--    加一个隐藏域提交cid--%>
                            <input name="cid" type="hidden" value="${category.cid}" class="form-control">
                            <button type="submit" class="btn btn-danger">添加</button>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</div>

<%@include file="../include/admin/adminFooter.jsp" %>
