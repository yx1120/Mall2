<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="../include/admin/adminHeader.jsp" %>
<%@include file="../include/admin/adminNavigator.jsp" %>
<link href="${pageContext.request.contextPath}/css/back/admin_list.css" rel="stylesheet">

<title>编辑属性</title>

<script>
    $(function () {

        $("#editForm").submit(function () {
            if (!checkEmpty("name", "属性名称"))
                return false;

            return true;
        });
    });
</script>

<div class="workArea">

    <%--路径导航--BootStarp--%>
    <div class="myBread">
        <ol class="breadcrumb">
            <li><a href="admin_list?cid=${bean.category.cid}">属性列表</a></li>
            <li class="active">编辑属性</li>
        </ol>
    </div>


    <div class="panel panel-warning editDiv">
        <%--BootStrap 带标题的面板--%>
        <div class="panel-heading">编辑属性</div>
        <%--	BootStrap-面板体。--%>
        <div class="panel-body">
            <form method="post" id="editForm" action="admin_update">
                <table class="editTable">
                    <tr>
                        <td class="cate_text">属性名称</td>
                        <td><input id="name" name="pyname" value="${bean.pyname}" type="text" class="form-control"></td>
                    </tr>
                    <tr class="submitTR">
                        <td colspan="2" align="center">
                            <%--一个隐藏域，用于提交分类id--%>
                            <input type="hidden" name="pyid" value="${bean.pyid}">
                            <button type="submit" class="btn btn-success">提 交</button>
                        </td>
                    </tr>
                </table>
            </form>
        </div>

    </div>
</div>