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

<title>编辑产品</title>

<div class="workArea">
    <div class="panel panel-warning editDiv">
        <div class="panel-heading">新增商品</div>
        <div class="panel-body">

            <form method="post" id="addForm" action="update">
                <table class="editTable">
                    <tr>
                        <td class="cate_text">产品名称</td>
                        <td><input name="pname" type="text" class="form-control" value="${bean.pname}"></td>
                    </tr>
                    <tr>
                        <td class="cate_text">产品小标题</td>
                        <td><input name="subTitle" type="text" class="form-control" value="${bean.subTitle}"></td>
                    </tr>
                    <tr>
                        <td class="cate_text">原价格</td>
                        <td><input name="orignalPrice" type="text" class="form-control" value="${bean.orignalPrice}"></td>
                    </tr>
                    <tr>
                        <td class="cate_text">优惠价格</td>
                        <td><input name="promotePrice" type="text" class="form-control" value="${bean.promotePrice}"></td>
                    </tr>
                    <tr>
                        <td class="cate_text">库存</td>
                        <td><input name="stock" type="text" class="form-control" value="${bean.stock}"></td>
                    </tr>
                    <tr class="submitTR">
                        <td colspan="2" align="center">
                            <%--    加一个隐藏域提交cid--%>
                            <input name="cid" type="hidden" value="${bean.category.cid}" class="form-control">
                            <input name="pid" type="hidden" value="${bean.pid}" class="form-control">


                            <button type="submit" class="btn btn-success">提 交</button>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</div>

<%@include file="../include/admin/adminFooter.jsp" %>
