//这是非异步加载使用的js

let bs = $(".block_span");
let cd = $(".cate_detail");

//鼠标移到左边分类，发ajax请求--->填充右边详细
bs.mouseover(function () {
    $(".cate_detail").css("display", "");
    let fid = $(this).attr("fid");
    // alert(fid);

    $.get("categories", {fid: fid}, function (list) {
        let a = "";
        for (let i = 0; i < list.length; i++) {
            a += '<a href="${pageContext.request.contextPath}/fore/products?cid=' + list[i].cid + '">' + list[i].cname + '</a>';
        }
        $(".cate_detail").html(a);
    });
});
bs.mouseout(function () {
    $(".cate_detail").css("display", "none");
});

//鼠标移出右边详细，隐藏详细分类
cd.mouseout(function () {
    $(this).css("display", "none");
});
cd.mouseover(function () {
    $(this).css("display", "");
});


//页面加载完后，发送ajax请求----》填充左边的一级分类
$.get("${pageContext.request.contextPath}/fore/home", function (list) {
    // alert(list);
    let sps = "";
    for (let i = 0; i < list.length; i++) {
        sps += '<span class="block_span" fid="' + list[i].id + '"><span class="glyphicon glyphicon-link"></span><a href="#">' + list[i].name + '</a></span>';
    }
    $(".cate_right").html(sps);
});

//委托事件
//鼠标移到左边分类，发ajax请求--->填充右边详细
$(".cate_right").delegate('.block_span', 'mouseover', function () {
    // alert("...___999");
    $(".cate_detail").css("display", "");
    let fid = $(this).attr("fid");

    $.get("categories", {fid: fid}, function (list) {
        let a = "";
        for (let i = 0; i < list.length; i++) {
            a += '<a href="${pageContext.request.contextPath}/fore/products?cid=' + list[i].cid + '">' + list[i].cname + '</a>';
        }
        $(".cate_detail").html(a);
    });
});

$(".foreWoreArea").delegate('.block_span', 'mouseout', function () {
    $(".cate_detail").css("display", "none");
});

//鼠标移出右边详细，隐藏详细分类
$(".index_category").delegate('.cate_detail', 'mouseover', function () {
    $(".cate_detail").css("display", "");
});
$("#hidden_div").delegate('.cate_detail', 'mouseout', function () {
    $(".cate_detail").css("display", "none");
});