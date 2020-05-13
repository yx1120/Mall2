<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>

<%--分页--%>
<%--isELIgnored=false,默认就是这个，可以不写--%>
<%--pageEncoding="utf-8",本jsp上的中文文字，使用UTF-8进行编码--%>

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
            <%--首页--%>
            <li><a href="?row=6&currentPage=1${page.params}">首页</a></li>

            <%--下一页--%>
            <c:if test="${page.currentPage == 1}">
                <li class="disabled">
            </c:if>
            <c:if test="${page.currentPage > 1}">
                <li>
            </c:if>

                <a href="?row=6&currentPage=${page.currentPage -1 }${page.params}" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>
            <%--中间的页码块--%>
            <c:forEach begin="1" end="${page.totalPage}" step="1" varStatus="vs">
                <c:if test="${page.currentPage == vs.count}">
                    <li class="active">
                </c:if>
                <c:if test="${page.currentPage != vs.count}">
                    <li>
                </c:if>

                    <a href="?row=6&currentPage=${vs.count}${page.params}">${vs.count}</a>
                </li>
            </c:forEach>
            <%--上一页--%>
            <c:if test="${page.currentPage == page.totalPage}">
                <li class="disabled">
            </c:if>
            <c:if test="${page.currentPage < page.totalPage}">
                <li>
            </c:if>

                <a href="?row=6&currentPage=${page.currentPage + 1}${page.params}" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
            <%--    尾页--%>
            <li><a href="?row=6&currentPage=${page.totalPage}${page.params}">尾页</a></li>
        </ul>
    </nav>

    <div class="page_num">
        <i></i> 共
        <span id="totalPage">${page.totalPage}</span>页<span id="totalCount">${page.totalCount}</span>条
    </div>
</div>

