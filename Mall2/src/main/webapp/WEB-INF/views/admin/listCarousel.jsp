<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*" %>

<%@include file="../include/admin/adminHeader.jsp" %>
<%@include file="../include/admin/adminNavigator.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link href="${pageContext.request.contextPath}/css/back/admin_list.css" rel="stylesheet">

<style>
    .workArea {
        width: 1400px;
        margin: 40px auto;
    }

    #edit_bar {
        margin-bottom: 20px;
    }

    #add_btn {
        width: 120px;
        /*float: right;*/
        font-size: 15px;
        font-weight: bold;
        font-family: "微软雅黑";
    }

    #carousel_list {
        background-color: #F8F8F8;
    }

    #carousel_list td {
        font-size: 15px;
        font-family: "微软雅黑";
    }

    .reason {
        height: 80px;
        line-height: 80px;
    }
    .addCol{
        color: #ff0036;
    }
    #carousel_list input[type='text'] {
        margin-top: 25px;
    }
</style>
<script>
    $(function () {

        $("#addForm").submit(function () {
            if (!checkEmpty("carouselUrl", "跳转地址"))
                return false;
            if (!checkEmpty("carouselImg", "轮播图片"))
                return false;
            return true;
        });

    });

    function pushCarousel(a) {
        var carouselId = $(a).attr("carouselId");

        $.post("${pageContext.request.contextPath}/admin/carousel/push",{carouselId:carouselId},function (info) {
            if(info.flag){
                if(info.info == "1"){
                    $(a).html('<span class="glyphicon glyphicon-ok addCol"></span>');
                }else {
                    $(a).html('<span class="glyphicon glyphicon-ban-circle"></span>');
                }
            }
        })
    }

    function updateCarouselUrl(btn) {
        var carouselId = $(btn).attr("carouselId");
        var url = $("input[carouselId="+carouselId+"]").val();

        $.post("${pageContext.request.contextPath}/admin/carousel/update",{carouselId:carouselId,carouselUrl:url},function (info) {
            if(info.flag){
                $(btn).addClass("btn-success");
                $(btn).html("O K");

                // 3s后恢复
                setTimeout(function () {
                    $(btn).removeClass("btn-success");
                    $(btn).html("更新");
                },2000);
            }
        });
    }

</script>

<title>轮播图管理</title>


<div class="workArea">
    <div id="edit_bar">
        <ol class="breadcrumb">
            <form method="post" action="add" enctype="multipart/form-data" id="addForm">
                <table class="addTable">
                    <tr>
                        <td class="col-lg-2">
                            <input accept="image/*" type="file" name="uploadImg" id="carouselImg"/>
                        </td>
                        <td class="col-lg-2">
                            <input name="carouselUrl" type="text" class="form-control" placeholder="跳转地址..." id="carouselUrl">
                        </td>
                        <td class=" col-lg-3">
                            <input type="submit" id="add_btn" class="btn btn-danger " value="新增">
                        </td>
                    </tr>
                </table>
            </form>
        </ol>
    </div>

    <div id="carousel_list">
        <table class="table table-hover">
            <thead>
            <tr>
                <td class="col-lg-3">轮播图片</td>
                <td class="col-lg-3">跳转地址</td>
                <td class="col-lg-1"></td>
                <td class="col-lg-2">上传时间</td>
                <td class="col-lg-1">上传用户</td>
                <td class="col-lg-1">推送</td>
                <td class="col-lg-1">删除</td>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${list}" var="carousel">
                <tr>
                    <td><img src="${pageContext.request.contextPath}/img/lunbo/${carousel.carouselId}.jpg" height="80px" class="img-rounded"></td>
                    <td>
                        <input type="text" value="${carousel.carouselUrl}" class="form-control" carouselId="${carousel.carouselId}">
                    </td>
                    <td>
                        <span  class="reason">
                            <button class="btn" carouselId="${carousel.carouselId}" onclick="updateCarouselUrl(this)">更新</button>
                        </span>
                    </td>
                    <td>
                        <span class="reason">
                            <fmt:formatDate value="${carousel.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                        </span>
                    </td>
                    <td>
                        <span class="reason">
                            ${carousel.createUser.name}
                        </span>
                    </td>
                    <td>
                        <span class="reason">
                            <c:if test="${carousel.isPush == 1}">
                                <a href="#" carouselId="${carousel.carouselId}" onclick="pushCarousel(this)" state="pushed">
                                    <span class="glyphicon glyphicon-ok addCol"></span>
                                </a>
                            </c:if>

                            <c:if test="${carousel.isPush == 0}">
                                <a href="#" carouselId="${carousel.carouselId}" onclick="pushCarousel(this)" state="noPush">
                                    <span class="glyphicon glyphicon-ban-circle"></span>
                                </a>
                            </c:if>
                        </span>
                    </td>
                    <td>
                        <span class="reason">
                             <a deleteLink="true" href="delete?carouselId=${carousel.carouselId}">
                                 <span class="glyphicon glyphicon-trash"></span>
                             </a>
                        </span>
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