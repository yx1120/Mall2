<%--
  Created by IntelliJ IDEA.
  User: Yang xu
  Date: 2019/12/12
  Time: 22:33
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<div class="lunbo" style="clear: both;position: relative; ">
    <!--    轮播图，bootstrap样式-->
    <div id="carousel-example-generic" class="carousel slide lunbo2" data-ride="carousel">
        <!-- Indicators -->
        <ol class="carousel-indicators" id="carousel_ol">
            <%--<li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
            <li data-target="#carousel-example-generic" data-slide-to="1"></li>
            --%>
        </ol>

        <!-- Wrapper for slides -->
        <div class="carousel-inner" role="listbox" id="carousel_div">
            <%--<div class="item active">
            <a href="http://localhost:8080/Mall/fore/product/844">
                <img src="${pageContext.request.contextPath}/img/lunbo/1.jpg" height="500px" alt="...">
            </a>
            </div>
            <div class="item">
                <a href="#">
                    <img src="${pageContext.request.contextPath}/img/lunbo/2.jpg" alt="...">
                </a>
            </div>
            --%>
        </div>

        <!-- Controls -->

        <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
            <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>
</div>
