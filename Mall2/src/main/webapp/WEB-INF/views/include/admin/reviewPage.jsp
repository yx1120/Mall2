<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>

<%--isELIgnored=false,默认就是这个，可以不写--%>

<%--分页条--%>
<style>
    div.pageBarDiv{
        text-align: center;
        width: 90%;
        margin:30px auto;
        /*border:1px solid red;*/
    }
    div.page_num{
        font-size: 14px;
    }
</style>

<script>
    $(function () {
        $("ul.pagination li.disabled a").click(function () {
            return false;
        });
    });
</script>

<div class="pageBarDiv">

    <nav id="category_pageBar">
        <ul class="pagination">

            <%--前一页--%>
            <c:if test="${page.isFirstPage}">
                <li class="disabled">
            </c:if>
            <c:if test="${!page.isFirstPage}">
                <li>
            </c:if>
                <a href="?row=6&currentPage=${page.pageNum-1 }${params}" aria-label="Previous">
                    <span aria-hidden="true">上一页</span>
                </a>
            </li>

            <%--后一页--%>
            <c:if test="${page.isLastPage}">
                <li class="disabled">
            </c:if>
            <c:if test="${!page.isLastPage}">
                <li>
            </c:if>
                <a href="?row=6&currentPage=${page.pageNum+1}${params}" aria-label="Next">
                    <span aria-hidden="true">下一页</span>
                </a>
            </li>

        </ul>
    </nav>

    <div class="page_num">
        <i></i> 共
        <span id="totalPage">${page.pages}</span>页<span id="totalCount">${page.total}</span>条
    </div>


</div>

