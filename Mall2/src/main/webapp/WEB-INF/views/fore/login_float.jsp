<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>

<%--模态登录--%>
<style>
    div.myDiglog {
        width: 400px;
        text-align: center;
    }

    #checkCode {
        width: 180px;
        margin-bottom: 15px;
    }

    #log_pass, #log_name {
        width: 180px;
        margin-bottom: 15px;
    }

    #img_check2 {
        margin-left: 10px;
        position: relative;
        bottom: 8px;
    }

    .login_btn {
        margin-right: 30px;
    }
</style>
<script>
    $("#img_check2").click(function () {
        this.src = "${pageContext.request.contextPath}/fore/checkCode?" + new Date().getTime();
    });

    $("#float_submit2").click(function () {
        $("#img_check2").src = "${pageContext.request.contextPath}/fore/checkCode?" + new Date().getTime();
    });
</script>


<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog myDiglog">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close" type="button"><span aria-hidden="true">×</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title">登录</h4>
            </div>
            <div class="modal-body">
                <form action="#" method="post" id="login_form">
                    <table>
                        <tr>
                            <td colspan="2"><input type="text" class="form-control " placeholder="输入手机号"
                                                   id="log_name" name="telNum"></td>
                            <td colspan="2"><span id="check_error"></span></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td colspan="2"><input type="password" class="form-control" id="log_pass"
                                                   placeholder="输入密码" name="password"></td>
                            <td></td>

                            <td></td>
                        </tr>
                        <tr>
                            <td><input type="text" class="form-control " id="checkCode" name="checkCode"
                                       placeholder="输入验证码"></td>
                            <td><a><img src="${pageContext.request.contextPath}/fore/checkCode" id="img_check2"> </a></td>
                            <td><span></span></td>
                        </tr>
                        <tr>
                            <td colspan="4">
                                <button type="submit" class="btn btn-default login_btn" id="float_submit">登录</button>
                                <button type="reset" class="btn btn-default login_btn" id="float_submit2">重置</button>
                                <button type="submit" class="btn btn-default login_btn"><a
                                        href="${pageContext.request.contextPath}/fore/register">注册</a>
                                </button>
                            </td>
                            <td></td>

                            <td></td>
                            <td></td>
                        </tr>
                    </table>
                </form>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>