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

       $.post("${pageContext.request.contextPath}/admin/firstCategory/update",{cid:cid,cname:cname},function (info) {
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
            <div id="add_level1">
                <form method="post" id="addForm" action="add">
                    <table class="addTable">
                        <tr>
                            <td class="cate_text">分类名称</td>
                            <td><input name="cname" type="text" class="form-control" placeholder="输入一级分类名称"></td>
                            <td colspan="2" align="center">
                                <button type="submit" class="btn btn-danger">新 增</button>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
            <div style="clear: both;"></div>
        </ol>

    </div>
    <div class="product_table ">
        <table class="table  table-hover">
            <thead>
            <tr class="info">

            </tr>
            </thead>

            <tbody>
            <c:forEach items="${list}" var="f" varStatus="vs">

                <tr>
                        <%--<td>${vs.count}</td>--%>
                    <td class="col-lg-7">
                        <a class="fc_name"
                           href="${pageContext.request.contextPath}/admin/category/list?parentId=${f.cid}" cid2="${f.cid}">${f.cname}
                        </a>
                    </td>

                    <td class="col-lg-3">
                        <%--admin_edit?cid=${f.cid}--%>
                        <a href="#" onclick="toEdit(this)" cid="${f.cid}">
                            <span class="glyphicon glyphicon-edit">
                            </span>
                        </a>

                        <div hidden class="edit_div" cid2="${f.cid}">
                            <table >
                                <tr>
                                    <td>
                                        <label>
                                            <input type="text" placeholder="${f.cname}" class="form-control" cid2="${f.cid}">
                                        </label>
                                    </td>
                                    <td>
                                        <button class="btn" onclick="updateName(this)" cid="${f.cid}">更新</button>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </td>

                    <td class="col-lg-2">
                        <a deleteLink="true" href="delete?cid=${f.cid}">
                            <span class="glyphicon glyphicon-trash"></span>
                        </a>
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

<script>
    $(function () {
        $("#addForm").submit(function () {
            if ($("input[name='cname']").val().length == 0) {
                return false;
            }
        });
    })
</script>

<%@include file="../include/admin/adminFooter.jsp" %>


