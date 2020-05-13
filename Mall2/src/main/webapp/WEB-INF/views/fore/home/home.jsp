<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix='fmt' %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <title>主页</title>
    <script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/jquery/jquery-3.2.1.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js" type="text/javascript"></script>

    <link href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/fore/iconfont.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/fore/tm_index.css" rel="stylesheet">
    <script>
        $(function () {

            $(".foreWoreArea").delegate('.block_span', 'mouseout', function () {
                $(".cate_detail").css("display", "none");
            });

            //鼠标移出右边详细，隐藏详细分类
            $(".index_category").delegate('.cate_detail', 'mouseover', function () {
                $(".cate_detail").css("display", "");
            });
            $("#hidden_div").delegate('.cate_detail', 'mouseout', function () {
                $(".cate_detail").css("display", "none");
            });

            //页面加载完后，发送ajax请求----》加载左边的一级分类
            $.getJSON("${pageContext.request.contextPath}/fore/fCategories", function (list) {
                let sps = "";
                for (let i = 0; i < list.length; i++) {
                    sps += '<span class="block_span" parentId="' + list[i].cid + '"><span class="glyphicon glyphicon-link"></span><a href="${pageContext.request.contextPath}/fore/firstLevelProducts/' + list[i].cid + '">' + list[i].cname + '</a></span>';
                }
                $(".cate_right").html(sps);
            });

            $(".cate_right").delegate('.block_span', 'mouseover', function () {
                $(".cate_detail").css("display", "");
                let parentId = $(this).attr("parentId");
                $.get("${pageContext.request.contextPath}/fore/categories", {parentId: parentId}, function (list) {
                    let a = "";
                    for (let i = 0; i < list.length; i++) {
                        a += '<a href="${pageContext.request.contextPath}/fore/products?cid=' + list[i].cid + '">' + list[i].cname + '</a>';
                    }
                    $(".cate_detail").html(a);
                });
            });

            //加载轮播图
            $.getJSON("${pageContext.request.contextPath}/fore/findCarousels",function (list) {
                let carousels = "";
                let lis = "";
                for (let i = 0; i < list.length ;i++) {
                    if(i==0){
                        lis += '<li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>';
                        carousels += ' <div class="item active">\n' +
                            '                <a href="'+list[i].carouselUrl+'">\n' +
                            '                    <img src="${pageContext.request.contextPath}/img/lunbo/'+list[i].carouselId+'.jpg" >\n' +
                            '                </a>\n' +
                            '            </div>';
                    }else {
                        lis += '<li data-target="#carousel-example-generic" data-slide-to="'+i+'"></li>';
                        carousels += ' <div class="item ">\n' +
                            '                <a href="'+list[i].carouselUrl+'">\n' +
                            '                    <img src="${pageContext.request.contextPath}/img/lunbo/'+list[i].carouselId+'.jpg" >\n' +
                            '                </a>\n' +
                            '            </div>';
                    }

                }
                $("#carousel_ol").html(lis);
                $("#carousel_div").html(carousels);
            })

        })
    </script>
</head>
<body>
<%@include file="../../include/fore/foreHeader.jsp" %>

<div class="foreWoreArea">
    <!--------------------------搜索框------------------------------------------->
    <%@include file="../../include/fore/foreSearch.jsp" %>
    <!--------------------------搜索框------------------------------------------->

    <%-------------------------最大容器------------------------------------------%>
    <div class="index_category">

        <div class="Menubar">
            <div class="categoryMenu">
                <!--bootstrap里面的字体图标-->
                <span class="glyphicon glyphicon-th-list"></span>
                <span>商品分类</span>
            </div>
            <div class="rightMenu">
            </div>
        </div>

        <%-- 用于设置背景色--%>

        <div id="hidden_div" style="position: relative;width:1200px;margin:0 auto;">
            <%------------------------------轮播图---------------------------------%>
            <%@include file="home_lunbo.jsp" %>
            <%------------------------------轮播图---------------------------------%>

            <%--------------------------------分类-------------------------------------%>
            <%@include file="home_leftMenu.jsp" %>
            <%--------------------------------分类-------------------------------------%>

            <%-----------------右边所有的详细内容 ，代表左边一个分类-----------------------%>
            <%@include file="home_rightDetail.jsp" %>
            <%-----------------右边所有的详细内容 ，代表左边一个分类-----------------------%>
        </div>
        <%----%>
    </div>
</div>

<%--------------------------------页尾---------------------------------%>
<%@include file="../../include/fore/foreFooter.jsp" %>
<%--------------------------------页尾---------------------------------%>

</body>
</html>
