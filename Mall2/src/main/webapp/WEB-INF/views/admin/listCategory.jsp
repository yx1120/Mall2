<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*" %>

<%@include file="../include/admin/adminHeader.jsp" %>
<%@include file="../include/admin/adminNavigator.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link href="${pageContext.request.contextPath}/css/back/admin_list.css" rel="stylesheet">


<script>
    $(function () {

        $("#addForm").submit(function () {
            return checkEmpty("name", "分类名称");
        });

        $(".a-upload").on("change","input[type='file']",function(){
            var filePath=$(this).val();
            if(filePath.indexOf("jpg")!=-1 || filePath.indexOf("png")!=-1){
                $(".fileerrorTip").html("").hide();
                var arr=filePath.split('\\');
                var fileName=arr[arr.length-1];
                $(".showFileName").html(fileName);
            }else{
                $(".showFileName").html("");
                $(".fileerrorTip").html("您未上传文件，或者您上传文件类型有误！").show();
                return false
            }
        })
    });

    function toEdit(a) {
        var cid = $(a).attr("cid");
        $(a).html('');
        $(a).parent().append(($("div[cid2="+cid+"]").html()));
    }

    function updateName(btn) {
        var cid = $(btn).attr("cid");
        var tr = $(btn).parent().parent();
        //获取子节点table
        var cname = tr.find("input").attr("cid2", cid).val();

        // alert(cid+"..."+cname+"...");

        $.post("${pageContext.request.contextPath}/admin/category/update",{cid:cid,cname:cname},function (info) {
            if(info.flag){
                $(btn).addClass("btn-success");
                $(btn).html("O K");

                location.reload();
            }
        });
    }
</script>
<style>


</style>

<title>分类管理</title>


<div class="workArea">
    <div class="myBread">
        <ol class="breadcrumb">
            <li><a href="${pageContext.request.contextPath}/admin/firstCategory/list">所有分类</a></li>
            <li><a href="#">${parentCategory.cname}</a></li>

        </ol>

        <ol class="breadcrumb">
            <div >
                <form method="post" id="addForm" action="add">
                    <table class="addTable">
                        <tr>
                            <td class="cate_text">分类名称</td>
                            <td ><input id="name" name="cname" type="text" class="form-control" placeholder="输入二级分类名称"></td>

                            <td >
                                <input hidden name="parentId" value="${parentCategory.cid} ">
                                <button type="submit" class="btn btn-danger">新 增</button>
                            </td>
                        </tr>

                    </table>
                </form>
            </div>
            <div style="clear: both"></div>
        </ol>

    </div>
    <div class="listDataTableDiv">
        <table class="table table-striped  table-hover  ">

            <thead>
            <tr class=" row">
                <th class="col-lg-3">分类名称</th>

                <th class="col-lg-2">属性</th>
                <th class="col-lg-2">产品</th>
                <th class="col-lg-3">编辑</th>
                <th class="col-lg-2">删除</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach items="${list}" var="c" varStatus="vs">

                <tr class="row">
                    <td>
                        <a href="${pageContext.request.contextPath}/admin/product/list?cid=${c.cid}">
                                ${c.cname}
                        </a>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/admin/property/list?cid=${c.cid}">
                            <span class="glyphicon glyphicon-th-list"></span>
                        </a>
                    </td>
                        <%--产品管理--%>
                    <td>
                        <a href="${pageContext.request.contextPath}/admin/product/list?cid=${c.cid}">
                            <span class="glyphicon glyphicon-shopping-cart"></span>
                        </a>
                    </td>

                    <td>
                        <%--“编辑”图标--%>
                        <%--admin_edit?cid=${c.cid}--%>
                        <a href="#" onclick="toEdit(this)" cid="${c.cid}">
                            <span class="glyphicon glyphicon-edit"></span>
                        </a>

                        <div hidden class="edit_div" cid2="${c.cid}">
                            <table >
                                <tr>
                                    <td>
                                        <label>
                                            <input type="text" placeholder="${c.cname}" class="form-control" cid2="${c.cid}">
                                        </label>
                                    </td>
                                    <td>
                                        <button class="btn" onclick="updateName(this)" cid="${c.cid}">更新</button>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </td>

                    <td>
                        <a deleteLink="true" href="delete?cid=${c.cid}">
                                <%--“删除”图标--%>
                            <span class="glyphicon glyphicon-trash"></span>
                        </a>
                    </td>

                </tr>
            </c:forEach>
            </tbody>

        </table>
    </div>

    <div class="pageDiv">
    <%@include file="../include/admin/adminPage.jsp" %>
    </div>


</div>

<%@include file="../include/admin/adminFooter.jsp" %>