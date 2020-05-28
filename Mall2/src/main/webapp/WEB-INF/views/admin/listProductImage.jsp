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

<script>
    $(function () {
        $("#addForm2").submit(function () {
            return checkEmpty("single_img", "分类图片");
        });

        $("#addForm3").submit(function () {
            return checkEmpty("detail_img", "分类图片");
        })
    })
</script>

<title>图片管理</title>
<div class="workArea">
    <div class="myBread">
        <ol class="breadcrumb">
            <li><a href="${pageContext.request.contextPath}/admin/firstCategory/list">所有分类</a></li>
            <li>
                <a href="${pageContext.request.contextPath}/admin/product/list?cid=${product.category.cid}">${product.category.cname}</a>
            </li>
            <li class="active">${product.pname}</li>
        </ol>
    </div>


    <div class="product_table ">

        <%--一共2行，一行2列--%>
        <table class="table">
            <tr>
                <td>
                    <table class="table table-striped table-hover">
                        <thead>
                        <tr class="info">
                            <th>ID</th>
                            <th>单个图片缩略图</th>
                            <th>删除</th>
                        </tr>
                        </thead>

                        <tbody>
                        <c:forEach items="${singleList}" var="g" varStatus="vs">
                            <tr>
                                <td>${vs.count}</td>
                                <td>
                                    <img src="${pageContext.request.contextPath}/img/productSingle/${g.gid}.jpg" style="height: 40px">
                                </td>
                                <td>
                                    <a deleteLink="true"
                                       href="delete?gid=${g.gid}&pid=${product.pid}"><span
                                            class="glyphicon glyphicon-trash"></span></a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </td>
                <td>
                    <table class="table table-striped table-bordered table-hover">
                        <thead>
                        <tr class="info">
                            <th>ID</th>
                            <th>详情图片缩略图</th>
                            <th>删除</th>
                        </tr>
                        </thead>

                        <tbody>
                        <c:forEach items="${detailList}" var="g" varStatus="vs">
                            <tr>
                                <td>${vs.count}</td>
                                <td>
                                    <img src="${pageContext.request.contextPath}/img/productDetail/${g.gid}.jpg" style="height: 40px">
                                </td>
                                <td>
                                    <a deleteLink="true"
                                       href="delete?gid=${g.gid}&pid=${product.pid}"><span
                                            class="glyphicon glyphicon-trash"></span></a>
                                </td>
                            </tr>
                        </c:forEach>

                        </tbody>
                    </table>
                </td>
            </tr>

            <tr>
                <td>
                    <div class="panel panel-warning proImg_addDiv">
                        <div class="panel-heading">新增&nbsp<span class="img_title">单个</span>&nbsp图片</div>
                        <div class="panel-body">

                            <form method="post" id="addForm2" action="addProductImg"
                                  enctype="multipart/form-data">
                                <table class="addTable">

                                    <tr>
                                        <input type="file" name="uploadImg" id="single_img">
                                    </tr>
                                    <tr>
                                        <td class="cate_text">请选择本地图片&nbsp&nbsp尺寸400X400为佳</td>
                                    </tr>

                                    <tr class="submitTR">
                                        <td colspan="2" align="center">
                                            <%--    加一个隐藏域提交商品pid--%>
                                            <input name="pid" type="hidden" value="${product.pid}" class="form-control">
                                            <input name="type" type="hidden" value="type_single">
                                            <button type="submit" class="btn btn-success">提 交</button>
                                        </td>
                                    </tr>
                                </table>
                            </form>
                        </div>
                    </div>
                </td>

                <td>
                    <div class="panel panel-warning proImg_addDiv">
                        <div class="panel-heading">新增&nbsp<span class="img_title">详情</span>&nbsp图片</div>
                        <div class="panel-body">

                            <form method="post" id="addForm3" action="addProductImg"
                                  enctype="multipart/form-data">
                                <table class="addTable">
                                    <tr>
                                        <input type="file" name="uploadImg" id="detail_img">
                                    </tr>
                                    <tr>
                                        <td class="cate_text">请选择本地图片&nbsp&nbsp宽度790为佳</td>
                                    </tr>

                                    <tr class="submitTR">
                                        <td colspan="2" align="center">
                                            <%--    加一个隐藏域提交商品pid--%>
                                            <input name="pid" type="hidden" value="${product.pid}" class="form-control">
                                            <input name="type" type="hidden" value="type_detail">
                                            <button type="submit" class="btn btn-success">提 交</button>
                                        </td>
                                    </tr>
                                </table>
                            </form>
                        </div>
                    </div>
                </td>
            </tr>
        </table>


    </div>

    <!--   新增商品-->

</div>

<%@include file="../include/admin/adminFooter.jsp" %>
