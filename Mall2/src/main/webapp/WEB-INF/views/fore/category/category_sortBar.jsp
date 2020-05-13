<%--
  Created by IntelliJ IDEA.
  User: Yang xu
  Date: 2019/12/15
  Time: 11:23
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>

<c:if test="${not empty category}">
    <div class="category_SortBar">
        <table id="left_bar">
            <tr>
                <td class="bgGray"><a href="?cid=${category.cid}&sort=all">综合<span
                        class="glyphicon glyphicon-arrow-down"></span></a></td>
                <td><a href="?cid=${category.cid}&sort=review">人气<span class="glyphicon glyphicon-arrow-up"></span></a></td>
                <td><a href="?cid=${category.cid}&sort=date">新品<span class="glyphicon glyphicon-arrow-down"></span></a></td>
                <td><a href="?cid=${category.cid}&sort=sallCount">销量<span class="glyphicon glyphicon-arrow-up"></span></a></td>
                <td><a href="?cid=${category.cid}&sort=price">价格<span class="glyphicon glyphicon-resize-vertical"></span></a>
                </td>
            </tr>
        </table>
    </div>
</c:if>

