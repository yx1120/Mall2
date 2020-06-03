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
    function toEdit(a) {
        var pyid = $(a).attr("pyid");
        $(a).html('');
        $(a).parent().append(($("div[pyid2="+pyid+"]").html()));
    }

    function updateName(btn) {
        var pyid = $(btn).attr("pyid");
        var tr = $(btn).parent().parent();
        //获取子节点table
        var pyname = tr.find("input").attr("pyid2", pyid).val();

        // alert(pyid+"..."+pyname+"...");

        $.post("${pageContext.request.contextPath}/admin/property/update",{pyid:pyid,pyname:pyname},function (info) {
            if(info.flag){
                $(btn).addClass("btn-success");
                $(btn).html("O K");

                location.reload();
            }
        });
    }
</script>


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
            <tr class="info">
                <%--<th>ID</th>--%>
                <th class="col-lg-4">属性名称</th>
                <th class="col-lg-4">编辑</th>
                <th class="col-lg-4">删除</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach items="${list}" var="p" varStatus="vs">
                <tr>
                        <%--<td>${vs.count}</td>--%>
                    <td>${p.pyname}</td>

                    <%-- 编辑属性--%>
                    <td>
                        <a href="#" onclick="toEdit(this)" pyid="${p.pyid}"><span class="glyphicon glyphicon-edit"></span></a>

                        <div hidden class="edit_div" pyid2=${p.pyid}>
                            <table >
                                <tr>
                                    <td>
                                        <label>
                                            <input type="text" placeholder="${p.pyname}" class="form-control" pyid2=${p.pyid}>
                                        </label>
                                    </td>
                                    <td>
                                        <button class="btn" onclick="updateName(this)" pyid=${p.pyid}>更新</button>
                                    </td>
                                </tr>
                            </table>
                        </div>
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
