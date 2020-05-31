package indi.xu.common;

/**
 * 响应的字符串信息
 *
 * @author a_apple
 * @create 2020-04-17 8:52
 */
public enum MallResultTip {

    /**
     * 错误提示信息
     */
    LOGIN_INFO_ERROR("<font color='red'>登录信息错误</font>"),

    LOGIN_USER_INFO_ERROR("<font color='red'>用户名或密码错误</font>"),

    CHECK_CODE_ERROR("<font color='red'>验证码错误或已过期</font>"),

    REGISTER_OK("<font color='green'>可以注册</font>"),

    REGISTER_NO("手机号已注册！"),

    PHONE_FORMAT_ERROR("<font color='red'>手机号格式错误</font>"),

    INIT_PASSWORD_ERROR("原密码错误！"),

    ;

    MallResultTip(String tipInfo) {
        this.tipInfo = tipInfo;
    }

    private String tipInfo;

    public String getTipInfo() {
        return tipInfo;
    }

    public void setTipInfo(String tipInfo) {
        this.tipInfo = tipInfo;
    }
}
