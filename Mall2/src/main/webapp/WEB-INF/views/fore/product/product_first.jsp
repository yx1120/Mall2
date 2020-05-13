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
    <link href="${pageContext.request.contextPath}/css/fore/fore_footer.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/fore/category_product.css" rel="stylesheet">

    <script>

    </script>
</head>
<body>
<%@include file="../../include/fore/foreHeader.jsp" %>

<div class="foreWoreArea">

    <!--------------------------搜索框------------------------------------------->
    <%@include file="../../include/fore/foreSearch.jsp" %>
    <!--------------------------搜索框------------------------------------------->
    <%@include file="../category/category_sortBar.jsp" %>

    <div class="category_products">

        <%@include file="product_first_level.jsp" %>

        <div style="clear: both"></div>

    </div>
</div>

<%@include file="../../include/fore/foreFooter.jsp" %>
</body>
</html>
