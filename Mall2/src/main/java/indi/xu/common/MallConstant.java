package indi.xu.common;

/**
 * 常量配置：
 *      1.后台分页每页显示的条数
 *      2.前台分页每页显示的条数
 *      3.默认的当前页码
 *      4.验证码图片的长、宽
 *      5.上传图片保存的长、宽
 *
 * @author a_apple
 * @create 2020-04-17 8:50
 */
public class MallConstant {

    private MallConstant() {
    }

    // 一级分类
    public static final int CATEGORY_LEVEL_ONE = 1;
    // 二级分类
    public static final int CATEGORY_LEVEL_TWO = 2;

    // 后台分页显示条数
    public static final int ADMIN_PAGE_ROW = 8;
    // 前台分页显示条数
    public static final int MALL_PAGE_ROW = 6;

    // 默认的当前页码
    public static final int DEFAULT_CURRENT_PAGE = 1;

    // 手机验证码位数
    public static final int PHONE_CHECK_CODE_NUM = 6;
    public static final String TEMPLATE_CODE = "SMS_190282724";

    // 字符验证码位数
    public static final int CHECK_CODE_NUM = 4;

    // 验证码的长宽
    public static final int CHECK_CODE_WIDTH = 80;
    public static final int CHECK_CODE_HEIGHT = 30;

    // 上传商品小尺寸图片的长宽
    public static final int SINGLE_IMG_SMALL_WIDTH = 56;
    public static final int SINGLE_IMG_SMALL_HEIGHT = 56;

    // 上传商品大尺寸图片的长宽
    public static final int SINGLE_IMG_MIDDLE_WIDTH = 217;
    public static final int SINGLE_IMG_MIDDLE_HEIGHT = 190;

    // 推送轮播     1—推送      2-取消推送
    public static final int PUSH_CAROUSEL = 1;
    public static final int NOT_PUSH_CAROUSEL = 0;

}
