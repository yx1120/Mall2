<%--
  Created by IntelliJ IDEA.
  User: Yang xu
  Date: 2019/12/15
  Time: 11:23
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>


<div class="pro_middle_detail">

    <div id="detali_01">
        <a href="#" id="product_001">商品详情</a>
        <a href="#" id="product_002">累计评价 <span class="awsl">${product.reviewCount}</span> </a>
    </div>

    <div id="pro_contain_params">
        <div id="pro_params">
            <div id="cpsc">产品参数：</div>
            <c:forEach items="${pvList}" var="pv">
                <span class="oneParams">
                    <span id="pvName">${pv.property.pyname}：</span>${pv.value}
                </span>
            </c:forEach>
        </div>


        <div id="pro_review" style="display: none">
            <c:forEach items="${reviewList}" var="review">
                <div class="oneReview">
                    <span class="createDate">${review.user.uname}</span>&nbsp&nbsp:
                    <span class="awsl">${review.content}</span></div>
            </c:forEach>
        </div>
        <div style="clear:both"></div>

    </div>

</div>