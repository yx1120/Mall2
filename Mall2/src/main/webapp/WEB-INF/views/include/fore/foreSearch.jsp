<%--
  Created by IntelliJ IDEA.
  User: Yang xu
  Date: 2019/12/12
  Time: 22:20
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>

<style>
    /**************************search2****************************/
    /**************************search2****************************/
    div.search_head2{
        width:1200px;
        margin:0 auto;
    }

    div.search_head2 #sea_div{
        background-color: #ff0036;
        width: 550px;
        margin: 25px auto;
        padding: 1px;
        height: 40px;
    }
    div.search_head2 #search_txt{
        width: 425px;
        border: 1px solid transparent;
        height: 36px;
        margin: 1px;
        /*去掉轮廓*/
        outline:none;
        padding-left: 15px;
    }
    #sea_btn{
        font-size: 18px;
        font-family: Arial;
        font-weight: bold;
        color: white;
        display: inline-block;
        padding-left: 35px;
        border-width: 0px;
        background-color: #ff0036;
    }
    #sea_btn:hover{
        text-decoration: none;
    }
</style>
<script>
    $(function () {
        $("#search_header").submit(function () {
            let txt = $("#search_txt").val();
            if (txt==""){
                return false;
            }
        });
    })
</script>


<div class="search_head2">
    <div id="sea_div">
        <form method="get" action="${pageContext.request.contextPath}/fore/searchProduct" id="search_header">
            <input type="text" id="search_txt" placeholder="搜索 商品" name="keyword" value="${param.keyword}">
            <input type="submit" id="sea_btn" value="搜索"/>
        </form>
    </div>
</div>