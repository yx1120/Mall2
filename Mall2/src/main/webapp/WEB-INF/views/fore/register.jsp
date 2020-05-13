<!DOCTYPE html>
<%--
  Created by IntelliJ IDEA.
  User: Yang xu
  Date: 2019/12/11
  Time: 22:25
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*" %>
<html>
<head>
    <title>注册</title>
    <script src="${pageContext.request.contextPath}/js/jquery/jquery-3.2.1.min.js" type="text/javascript"></script>
    <link href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.min.css" rel="stylesheet">
    <script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/public.js"></script>

    <link href="${pageContext.request.contextPath}/css/fore/iconfont.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/fore/login_register.css" rel="stylesheet">


    <script>
        $(function () {
            let img_check = $("#img_check");
            img_check.click(function () {
                this.src = "${pageContext.request.contextPath}/fore/checkCode?" + new Date().getTime();
            });

            //重复名检测
            //失去焦点，发送ajax
            $("#reg_phone").blur(function () {
                let telNum = $(this).val();

                //检测“全为空格”
                if(telNum.trim().length == 0){
                    $(this).css("border", "2px solid red");
                    $("#error_info").html("<font color = 'red'>手机号不能为空</font>");
                    return;
                }
                //手机号校验
                if (!isPhoneNumber(telNum)) {
                    $(this).css("border", "2px solid red");
                    $("#error_info").html("<font color = 'red'>手机号格式有误</font>");
                    return;
                }
                //在前台做长度验证
                if(telNum.length>0 ){
                    $.get("${pageContext.request.contextPath}/fore/checkRepeatName", {telNum: telNum}, function (info) {
                        if (info.flag) {
                            $("#error_info").html(info.info);
                            $("#reg_phone").css("border", "2px solid red");
                        } else {
                            $("#error_info").html(info.info);
                            $("#reg_phone").css("border", "2px solid green");
                        }
                    });
                }
            });

            //密码二次验证,与第一个框的密码验证
            $("#reg_pass2").blur(function () {
                let pass1 = $("#reg_pass");
                if(pass1.val().trim().length == 0 || $(this).val().trim().length == 0){
                    $("#passCheck").html("<font color = 'red'>密码不能为空</font>");
                }else if (pass1.val() !== $(this).val()) {
                    $("#passCheck").html("<font color = 'red'>密码不一致</font>");
                    // alert("2次密码不一样！");
                }else {
                    $("#passCheck").html("<font color = 'green'>OK</font>");
                }
            });

            //提交表单
            $("#reg_form").submit(function () {

                let phone = $("#reg_phone");
                let password = $("#reg_pass");
                let password2 = $("#reg_pass2");
                let checkCode = $("#checkCode");

                if (phone.val().length <= 0 || phone.val().trim().length == 0) {
                    phone.css("border", "2px solid red");
                    return false;
                }

                if (!isPhoneNumber(phone.val())) {
                    return false;
                }

                if (password.val().length <= 0) {
                    password.css("border", "2px solid red");
                    return false;
                }

                if (password2.val().length <= 0) {
                    password2.css("border", "2px solid red");
                    return false;
                }

                if (checkCode.val().length <= 0) {
                    checkCode.css("border", "2px solid red");
                    return false;
                }

                if (password.val() != password2.val()) {
                    alert("2次密码不一样！");
                    return false;
                }

                //上面都满足才提交   ajax提交表单为json格式
                var formObject = {};
                var formArray =$(this).serializeArray();
                $.each(formArray,function(i,item){
                    formObject[item.name] = item.value;
                });

                $.ajax({
                    url: '${pageContext.request.contextPath}/fore/userRegister',
                    type: 'POST',
                    contentType: 'application/json; charset=UTF-8',
                    async: false,
                    dataType: 'json',
                    data: JSON.stringify(formObject),
                    success: function (info) {
                        if (!info.flag) {
                            $("#check_error").html(info.info);
                        } else {
                            // alert("注册成功");
                            location.href = "${pageContext.request.contextPath}/fore/registerSuccess";
                        }
                    }
                });
                return false;
            });
        });


    </script>

</head>
<body>
<%@include file="../include/fore/foreHeader.jsp" %>

<div class="foreWoreArea">

    <div class="form_div">
        <div id="formId">
            <form action="#" method="post" id="reg_form">
                <table>
                    <tr>
                        <td><span class="login_txt">手机号</span></td>
                        <td colspan="2"><input type="text" class="form-control " placeholder="输入手机号" name="telNum"
                                               id="reg_phone"
                                               onkeyup="value=value.replace(/[^\d]/g,'')"
                                               onblur="value=value.replace(/[^\d]/g,'')"></td>
                        <td><span id="error_info" class=""></span></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td><span class="login_txt">密&nbsp&nbsp&nbsp&nbsp码</span></td>
                        <td colspan="2"><input type="password" class="form-control" placeholder="输入密码" name="password"
                                               id="reg_pass"></td>
                        <td></td>

                        <td></td>
                    </tr>
                    <tr>
                        <td><span class="login_txt">确&nbsp&nbsp&nbsp&nbsp认</span></td>
                        <td colspan="2"><input type="password" class="form-control" placeholder="再次输入密码"
                                               name="password2" id="reg_pass2"></td>
                        <td colspan="2"><span id="passCheck"></span></td>

                        <td></td>
                    </tr>
                    <tr>
                        <td><span class="login_txt">验证码</span></td>
                        <td>
                            <input type="text" class="form-control " name="checkCode" id="checkCode">
                        </td>
                        <td><a><img src="${pageContext.request.contextPath}/fore/checkCode" id="img_check"> </a></td>

                        <td><span id="check_error"></span></td>
                    </tr>
                    <tr>
                        <td colspan="4">
                            <button type="submit" class="btn btn-default login_btn2">注册</button>
                        </td>
                        <td></td>

                        <td></td>
                        <td></td>
                    </tr>
                </table>
            </form>

        </div>
    </div>
</div>

<%--------------------------------页尾---------------------------------%>
<%@include file="../include/fore/foreFooter.jsp" %>
<%--------------------------------页尾---------------------------------%>
</body>
</html>
