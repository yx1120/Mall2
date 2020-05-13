<%--
  Created by IntelliJ IDEA.
  User: Yang xu
  Date: 2019/12/12
  Time: 22:37
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>

<div class="product_container">
    <div class="products_out_div">

        <%--先拿出分类--%>
        <c:forEach items="${list}" var="cg">
            <%--   每个分类显示5个--%>
            <div class="row_products">
                <div class="cate_name">
                    <span class="tiao"></span>
                        <%--分类名称 更多，可以点击名称进入即可 --%>
                    <span class="cate_name2">
                        <a href="${pageContext.request.contextPath}/fore/products?cid=${cg.cid}">
                                ${cg.cname}
                        </a>
                    </span>
                </div>

                    <%--再对分类的产品遍历5个出来--%>
                <c:forEach items="${cg.products}" var="p" varStatus="vs">
                    <c:if test="${vs.count <=5}">
                        <div class="product">
                            <a href="${pageContext.request.contextPath}/fore/product/${p.pid}">
                                <img src="${pageContext.request.contextPath}/img/productSingle_middle/${p.firstProductImage.gid}.jpg">
                            </a>
                            <a href="${pageContext.request.contextPath}/fore/product/${p.pid}">
                                <span class="pro_name">[热销]${p.pname}</span>
                            </a>
                            <span class="pro_price">${p.orignalPrice}</span>
                        </div>
                    </c:if>
                </c:forEach>

                <div style="clear: both"></div>
            </div>
        </c:forEach>

        <%--            这个图标，应该是继续加载二级分类商品--%>
        <div class="row_end">
            <img src="${pageContext.request.contextPath}/img/site/end.png">
        </div>
    </div>
</div>
