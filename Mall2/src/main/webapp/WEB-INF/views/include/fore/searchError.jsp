<%--
  Created by IntelliJ IDEA.
  User: Yang xu
  Date: 2019/12/22
  Time: 22:20
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>

<style>
    div.result_tip{
        width: 1200px;
        height: 60px;
        margin:10px auto;
        font-size: 22px;
        color: deeppink;
        text-align: center;
    }
</style>

<div class="result_tip">
    <c:if test="${empty products and empty category}">
        没有此类商品，请试试其他的
    </c:if>
</div>