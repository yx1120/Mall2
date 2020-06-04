package indi.xu.utils;

import indi.xu.common.MallConstant;
import indi.xu.web.controller.auth.MallOrderController;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 数字校验
 */
public class NumberUtil {

    private NumberUtil() {
    }


    /**
     * 判断是否为11位电话号码
     *
     * @param phone
     * @return
     */
    public static boolean isPhone(String phone) {
        Pattern pattern = Pattern.compile("^((13[0-9])|(14[5,7])|(15[^4,\\D])|(17[0-8])|(18[0-9]))\\d{8}$");
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }

    /**
     * 生成指定长度的随机数
     *
     * @param length
     * @return
     */
    public static int genRandomNum(int length) {
        int num = 1;
        double random = Math.random();
        if (random < 0.1) {
            random = random + 0.1;
        }
        for (int i = 0; i < length; i++) {
            num = num * 10;
        }
        return (int) ((random * num));
    }

    /**
     * 生成订单流水号
     *
     * @return
     */
    public static String genOrderNo() {
        StringBuffer buffer = new StringBuffer(String.valueOf(System.currentTimeMillis()));
        int num = genRandomNum(4);
        buffer.append(num);
        return buffer.toString();
    }

    /**
     * 生成指定位数手机验证码
     */
    public static String createPhoneCheckCode(){
        StringBuilder sb = new StringBuilder();
        Random rd = new Random();
        for (int i = 0; i < MallConstant.PHONE_CHECK_CODE_NUM; i++) {
            sb.append(rd.nextInt(10));
        }
        return sb.toString();
    }

    /**
     * 产生4位随机字符串
     */
    public static String createCheckCode() {
        String base = "123456789ABCDEFGHJKLUVWXYZabcdefghijk";
        int size = base.length();
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= MallConstant.CHECK_CODE_NUM; i++) {
            int index = r.nextInt(size);
            char c = base.charAt(index);
            sb.append(c);
        }
        return sb.toString();
    }
}
