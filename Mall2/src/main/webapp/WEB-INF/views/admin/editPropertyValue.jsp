<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="../include/admin/adminHeader.jsp" %>
<%@include file="../include/admin/adminNavigator.jsp" %>
<link href="${pageContext.request.contextPath}/css/back/admin_list.css" rel="stylesheet">

<title>编辑属性值</title>

<script>
    $(function () {

        $("#editForm").submit(function () {
            if (!checkEmpty("name", "属性名称"))
                return false;

            return true;
        });

        $(".pvValue").focus(function () {
            $(this).css("border", "2px solid yellow");
        });

        $(".pvValue").blur(function () {
            //获取vid,发送ajax请求，修改属性
            //如果修改成功，返回Boolean fixOk = true;.    前端判断，if(fixOk) 改变边框为绿色
            var vid = $(this).attr("vid");
            var value = $(this).val();
            $.post("updatePropertyValue", {vid: vid, value: value}, function (data) {
                if (data == "update_OK") {
                    $(this).css("border", "2px solid green");
                }
            }, "json");
        });
    });

</script>

<div class="workArea">

    <%--路径导航--BootStarp--%>
    <div class="myBread">
        <ol class="breadcrumb">
            <li><a href="${pageContext.request.contextPath}/admin/firstCategory/list">所有分类</a></li>
            <li>
                <a href="${pageContext.request.contextPath}/admin/product/list?cid=${product.category.cid}">${product.category.cname}</a>
            </li>
            <li class="active">${product.pname}</li>
        </ol>
    </div>


    <div class="panel panel-warning pro_editDiv">
        <%--BootStrap 带标题的面板--%>
        <div class="panel-heading">编辑属性</div>
        <%--	BootStrap-面板体。--%>
        <div class="panel-body">

            <table class="editTable">
                <c:forEach items="${list}" var="vs">
                    <tr>
                        <td class="cate_text">${vs.property.pyname}</td>
                        <td><input vid="${vs.vid}" name="pvValue" value="${vs.value}" type="text"
                                   class="form-control pvValue"></td>
                    </tr>
                </c:forEach>
            </table>

        </div>

    </div>
</div>