<!DOCTYPE html>
<%--
  Created by IntelliJ IDEA.
  User: Yang xu
  Date: 2019/12/11
  Time: 22:25
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix='fmt' %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <title>${product.pname}</title>
    <script src="${pageContext.request.contextPath}/js/jquery/jquery-3.2.1.min.js" type="text/javascript"></script>
    <link href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.min.css" rel="stylesheet">
    <script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js"></script>
    <link href="${pageContext.request.contextPath}/css/fore/iconfont.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/fore/product.css" rel="stylesheet">

    <script>
        $(function () {
            $(".smallImg").mouseover(function () {
                //获取小图的selfBigImg属性
                bigImgPath = $(this).attr("selfBigImg");
                //设置大图路径
                $(".bigImg").prop("src", bigImgPath);
            });

            //切换  商品详情，评价
            $("#product_001").click(function () {
                $("#pro_params").css("display", "");
                $("#pro_review").css("display", "none");
            });
            $("#product_002").click(function () {
                $("#pro_params").css("display", "none");
                $("#pro_review").css("display", "");
            });

            //从搜索商品后退---禁止返回
            if(document.referrer == "http://localhost:8080/Mall/fore/searchProduct"){

                pushHistory();
                window.addEventListener("popstate", function(e) {
                    location.href="${pageContext.request.contextPath}/fore/home";
                }, false);
                function pushHistory() {
                    var state = {
                        title: "title",
                        url: "#"
                    };
                    window.history.pushState(state, "title", "#");
                }

            }


        });
    </script>
</head>

<body>
<%@include file="../../include/fore/foreHeader.jsp" %>

<div class="foreWoreArea">

    <div class="foreContainer">
        <%-------------------详情上面的部分-------------------------%>
        <%@include file="product_up.jsp" %>
        <%-------------------详情上面的部分-------------------------%>

        <!--    商品详情---------------评价-----参数-------------->
        <%@include file="product_params.jsp" %>
        <!--    商品详情---------------评价-----参数-------------->

        <!--    商品详情---------------大图片-----大图片-------------->
        <div id="pro_imgs">
            <c:forEach items="${product.productDetailImages}" var="gg">
                <img src="${pageContext.request.contextPath}/img/productDetail/${gg.gid}.jpg" width="650">
            </c:forEach>
        </div>
        <!--    商品详情---------------大图片-----大图片-------------->
    </div>


</div>

<%--------------------------------页尾---------------------------------%>
<%@include file="../../include/fore/foreFooter.jsp" %>
<%--------------------------------页尾---------------------------------%>
</body>
</html>
