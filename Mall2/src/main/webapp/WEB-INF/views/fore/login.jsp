<!DOCTYPE html>
<%--
  Created by IntelliJ IDEA.
  User: Yang xu
  Date: 2019/12/11
  Time: 22:25
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix='fmt' %>
<html>
<head>
    <title>登录</title>
    <script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery/jquery-3.2.1.min.js" type="text/javascript"></script>
    <link href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/fore/iconfont.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/fore/login_register.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/public.js"></script>


    <script>
        $(function () {

            // 发送验证码
            $("#askPhoneCheckCode").click(function () {
                // .1获取手机号
                let phone = $("#log_number2").val();
                if(phone.length!=0 && isPhoneNumber(phone)){
                    $.ajax({
                        url: '${pageContext.request.contextPath}/fore/send/'+phone,
                        type: 'GET',
                        success: function (info) {
                            if (!info.flag) {
                                alert("验证码发送失败，请联系管理员！");
                            }else {
                                //发送成功
                                let td = $("#askPhoneCheckCode").parent();
                                td.html("已发送");
                                setTimeout(function () {
                                    td.html("<a href=\"#\" id=\"askPhoneCheckCode\">点我发送</a>");
                                },60000);
                            }
                        }
                    });
                }});

            //切换短信登录
            $("#smsLogin").click(function () {
                $("#login_form").css("display","none");
                $("#login_sms").css("display","");

                $(this).html("");
            });

            // 短信登录
            $("#login_sms").submit(function () {
                $.ajax({
                    url: '${pageContext.request.contextPath}/fore/userLoginBySms',
                    type: 'POST',
                    data: $(this).serialize(),
                    success: function (info) {
                        if (!info.flag) {
                            alert(info.info);
                            location.href = "register";
                        } else {
                            location.href = "home";
                        }
                    }
                });
            });

            //判断是否输入正确的手机号
            $("input[name=telNum]").blur(function () {
                let telNum = $(this).val();
                if (!isPhoneNumber(telNum)) {
                    $("#error_info").html("手机号格式有误");
                } else {
                    $("#error_info").html("");
                }
            });

            let img_check = $("#img_check");
            img_check.click(function () {
                this.src = "${pageContext.request.contextPath}/fore/checkCode?" + new Date().getTime();
            });

            //提交表单
            $("#login_form").submit(function () {

                let telNumber = $("#log_number");
                let password = $("#log_pass");
                let checkCode = $("#checkCode");

                //加一个手机号的正则表达式校验

                if (telNumber.val().length <= 0) {
                    telNumber.css("border", "2px solid red");
                    return false;
                }

                if (!isPhoneNumber(telNumber.val())) {
                    return false;
                }

                if (password.val().length <= 0) {
                    password.css("border", "2px solid red");
                    return false;
                }

                if (checkCode.val().length <= 0) {
                    checkCode.css("border", "2px solid red");
                    return false;
                }

                //上面都满足才提交   ajax提交表单为json格式
                var formObject = {};
                var formArray =$(this).serializeArray();
                $.each(formArray,function(i,item){
                    formObject[item.name] = item.value;
                });

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
                            location.href = "${pageContext.request.contextPath}/fore/home";
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
            <form action="#" method="post" id="login_form">
                <table>

                    <tr>
                        <td><span class="login_txt">手机号</span></td>
                        <td colspan="2"><input type="text" class="form-control " placeholder="输入手机号"
                                               id="log_number" name="telNum"
                                               onkeyup="value=value.replace(/[^\d]/g,'')"
                                               onblur="value=value.replace(/[^\d]/g,'')"
                        ></td>
                        <td><span id="error_info"></span></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td><span class="login_txt">密&nbsp&nbsp&nbsp&nbsp码</span></td>
                        <td colspan="2"><input type="password" class="form-control" id="log_pass"
                                               placeholder="输入密码" name="password"></td>
                        <td></td>

                        <td></td>
                    </tr>
                    <tr>
                        <td><span class="login_txt">验证码</span></td>
                        <td><input type="text" class="form-control " id="checkCode" name="checkCode"></td>
                        <td><a><img src="${pageContext.request.contextPath}/fore/checkCode" id="img_check"> </a></td>
                        <td><span id="check_error"></span></td>
                    </tr>
                    <tr>
                        <td colspan="4">
                            <button type="submit" class="btn btn-default login_btn">登录</button>
                        </td>
                        <td>

                        </td>

                        <td></td>
                    </tr>
                </table>
            </form>
            <%--短信登录--%>
            <form action="#" method="post" id="login_sms" style="display: none">
                <table>

                    <tr>
                        <td><span class="login_txt">手机号</span></td>
                        <td colspan="2"><input type="text" class="form-control " placeholder="输入手机号"
                                               id="log_number2" name="phone"
                                               onkeyup="value=value.replace(/[^\d]/g,'')"
                                               onblur="value=value.replace(/[^\d]/g,'')"
                        ></td>
                        <td><span id="error_info2"></span></td>
                        <td></td>
                    </tr>

                    <tr>
                        <td><span class="login_txt">验证码</span></td>
                        <td><input type="text" class="form-control " id="checkCode2" name="phoneCheckCode"></td>
                        <td><a href="#" id="askPhoneCheckCode">点我发送</a></td>
                        <td><span id="check_error2"></span></td>
                    </tr>
                    <tr>
                        <td colspan="4">
                            <button type="submit" class="btn btn-default login_btn">登录</button>
                        </td>
                        <td>

                        </td>

                        <td></td>
                    </tr>
                </table>
            </form>

            <div id="reg_div">
                <a href="#" id="smsLogin">短信登录</a>&nbsp
                <a href="${pageContext.request.contextPath}/fore/register">注册账号</a>
            </div>
        </div>
    </div>

    <%--短信登录--%>
    <div style="display: none">

    </div>
</div>

<%--------------------------------页尾---------------------------------%>
<%@include file="../include/fore/foreFooter.jsp" %>
<%--------------------------------页尾---------------------------------%>
</body>
</html>
