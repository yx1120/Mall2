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
    <title>${category.cname}</title>
    <script src="${pageContext.request.contextPath}/js/jquery/jquery-3.2.1.min.js" type="text/javascript"></script>
    <link href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.min.css" rel="stylesheet">
    <script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js"></script>
    <link href="${pageContext.request.contextPath}/css/fore/iconfont.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/fore/category_product.css" rel="stylesheet">

</head>
<body>
<%@include file="../../include/fore/foreHeader.jsp" %>

<div class="foreWoreArea">

    <!--------------------------搜索框------------------------------------------->
    <%@include file="../../include/fore/foreSearch.jsp" %>
    <!--------------------------搜索框------------------------------------------->
    <%@include file="category_sortBar.jsp" %>

    <div class="category_products">
        <c:forEach items="${category.products}" var="p">
            <!-- 固定big_div宽度，流式填充product_div即可-->
            <div class="one_product">
                <div class="inner_border">
                    <div>
                        <a href="${pageContext.request.contextPath}/fore/product/${p.pid}">
                            <img src="${pageContext.request.contextPath}/img/productSingle_middle/${p.firstProductImage.gid}.jpg">
                        </a>
                    </div>
                    <span class="one_product_price opp">￥${p.promotePrice}</span>
                    <span class="opp one_product_title"><a
                            href="${pageContext.request.contextPath}/fore/product/${p.pid}"> ${p.pname}</a></span>
                    <span class="opp tmtm"> </span>
                    <div class="down_info">
                        <span>月成交<span class="info_01">${p.saleCount}笔</span></span>
                        <span>评价<span class="info_02">${p.reviewCount}</span></span>
                        <span class="info_img"><img
                                src="${pageContext.request.contextPath}/img/site/wangwang.png"></span>
                    </div>
                </div>
            </div>
        </c:forEach>

        <div style="clear: both"></div>
        <%--没有该分类商品--%>
        <%@include file="../../include/fore/searchError.jsp" %>
    </div>
</div>

<%@include file="../../include/fore/foreFooter.jsp" %>
</body>
</html>
