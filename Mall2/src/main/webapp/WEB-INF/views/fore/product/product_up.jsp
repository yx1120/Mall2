<%--
  Created by IntelliJ IDEA.
  User: Yang xu
  Date: 2019/12/15
  Time: 11:23
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<script>
    $(function () {
        //加入购物车。先检测user
        $("#putCar").click(function () {
            if (${empty user}) {
                $("#myModal").modal('show');
            } else {
                //加入购物车
                let ni = $("#num_input");
                let number = ni.val();
                let pid = ni.attr("pid");

                //用户id的话从后台获取（前台已判断登录
                $.post("${pageContext.request.contextPath}/shopCart/addCart", {
                    number: number,
                    pid: pid
                }, function (info) {
                    if (info.flag) {
                        //提示用户，添加成功
                        alert("添加成功");
                        //先用弹窗，等后面优化
                    }
                })
            }
        });

        // 点击购买
        $("#sellNow").click(function () {
            if (${empty user}) {
                $("#myModal").modal('show');
            } else {
                //立即购买
                //获取pid和number
                let ni = $("#num_input");
                let number = ni.val();
                let pid = ni.attr("pid");
                //跳转
                location.href = "${pageContext.request.contextPath}/mallOrder/buyRightNow?number=" + number + "&pid=" + pid + "";
            }
        });

        //点击收藏
        $("#addFavorite").click(function () {
            if (${empty user}) {
                $("#myModal").modal('show');
            } else {
                //获取pid
                let ni = $("#num_input");
                let pid = ni.attr("pid");
                //跳转
                let addLink = $("#addFavorite");
                $.post("${pageContext.request.contextPath}/shopCart/addAndDelFavorite", {
                    pid: pid,
                    "status": "add"
                }, function (info) {
                    if (info.flag) {
                        addLink.html("取消收藏");
                        location.reload();
                    }
                });
            }
        });

        //取消收藏
        $("#deleteFavorite").click(function () {
            if (${empty user}) {
                $("#myModal").modal('show');
            } else {
                //获取pid
                let ni = $("#num_input");
                let pid = ni.attr("pid");
                //跳转
                let addLink = $("#addFavorite");
                $.post("${pageContext.request.contextPath}/shopCart/addAndDelFavorite", {
                    pid: pid,
                    "status": "del"
                }, function (info) {
                    if (info.flag) {
                        location.reload();
                    }
                });
            }
        });

        //模态登录提交
        $("#login_form").submit(function () {

            var formObject = {};
            var formArray =$(this).serializeArray();
            $.each(formArray,function(i,item){
                formObject[item.name] = item.value;
            });
            //ajax提交
            $.ajax({
                url: '${pageContext.request.contextPath}/fore/userLogin',
                type: 'POST',
                contentType: 'application/json; charset=UTF-8',
                async: false,
                dataType: 'json',
                data: JSON.stringify(formObject),
                success: function (info) {
                    if (!info.flag) {
                        $("#check_error").html(info.info);
                    } else {
                        // alert("登录成功");
                        location.href = "${pageContext.request.contextPath}/fore/product/${product.pid}";
                    }
                }
            });
            return false;
        });

        //商品数量增减
        let num = $("#num_input");
        //商品数量减1
        $("#jian_num").click(function () {
            if (num.val() <= 1) {
                num.val(1);
            } else {
                num.val(num.val() - 1);
            }
        });

        //商品数量加1
        $("#jia_num").click(function () {
            let num2 = num.val();
            num.val(++num2);
        });
    });

</script>

<style>
    a.fix_num {
        display: inline-block;
        width: 24px;
        height: 24px;
        line-height: 24px;
        border: 1px solid silver;
        text-align: center;
        font-weight: bold;
    }

    .fix_num:hover{
        text-decoration: none;
        color: #C40000;
    }
    a.fix_num:visited{
        text-decoration: none;
    }

    #num_input {
        width: 28px;
        height: 24px;
    }
</style>

<%@include file="../login_float.jsp" %>

<div class="product_up">
    <!--左边图片-->
    <div class="pro_left_img">
        <div id="left_big_img">
            <img width="300px" height="300px"
                 src="${pageContext.request.contextPath}/img/productSingle/${product.firstProductImage.gid}.jpg"
                 class="bigImg" alt="1">
        </div>
        <div id="left_small_img">
            <c:forEach items="${product.productSingleImages}" var="sinImg" varStatus="vs">
                <c:if test="${vs.count<=5}">
                    <img src="${pageContext.request.contextPath}/img/productSingle_small/${sinImg.gid}.jpg"
                         class="smallImg"
                         selfBigImg="${pageContext.request.contextPath}/img/productSingle/${sinImg.gid}.jpg" alt="1">
                </c:if>
            </c:forEach>
        </div>
    </div>

    <div class="pro_right_detail">
        <div class="xswl" id="pro_title">
            <span class="awsl">${product.pname}</span>
        </div>

        <div class="xswl" id="pro_small_title">
            <span class="awsl">${product.subTitle}</span>
            <c:if test="${empty product.subTitle}">
                落日归山海，陪伴成告白
            </c:if>
        </div>

        <div class="xswl" id="pro_price">
            <div>价格：<span class="awsl "><s>${product.orignalPrice}</s></span></div>
            <div>促销价：<span class="awsl">${product.promotePrice}</span></div>
        </div>

        <div class="xswl" id="pro_sell_num">
            销量：<span class="awsl">${product.saleCount}</span>
            累计评价：<span class="awsl">${product.reviewCount}</span>
        </div>

        <div class="xswl" id="pro_sell_count">
            数量：
            <a id="jian_num" href="#" class="fix_num">-</a>
            <input type="text" name="num" value="1" id="num_input" pid="${product.pid}">
            <a id="jia_num" href="#" class="fix_num">+</a>
            <span>
                库存：<span class="awsl">${product.stock}</span>件
            </span>
        </div>

        <div class="xswl" id="pro_cno">
            <span>服务承诺 正品保证 极速退款 赠运费险 七天无理由退换</span>
        </div>

        <div class="xswl" id="pro_sell_btn">
            <%--使用ajax提交数量和pid即可--%>
            <a href="#" class="btn btn-danger carBtn" id="sellNow">立即购买</a>
            <a href="#" class="btn btn-success carBtn" id="putCar">加入购物车</a>
            <c:if test="${isFavorite == false}">
                <a href="#" class="btn btn-info carBtn" id="addFavorite" >点击收藏</a>
            </c:if>

            <c:if test="${isFavorite == true}">
                <a href="#" class="btn btn-info carBtn" id="deleteFavorite" style="background-color:lightgray;color: #ff0036">取消收藏</a>
            </c:if>
        </div>
    </div>
</div>