package indi.xu.utils;

import indi.xu.common.MallConstant;
import indi.xu.web.vo.PageVo;

/**
 * @author a_apple
 * @create 2020-04-17 9:26
 */
public class PageUtil {

    private PageUtil() {
    }

    public static void checkAdminPv(PageVo pv) {
        Integer row = pv.getRow();
        Integer currentPage = pv.getCurrentPage();

        if (row == null || row <= 0) {
            pv.setRow(MallConstant.ADMIN_PAGE_ROW);
        }
        if (currentPage == null || currentPage <= 0) {
            pv.setCurrentPage(MallConstant.DEFAULT_CURRENT_PAGE);
        }
    }

    public static void checkMallPv(PageVo pv) {
        Integer row = pv.getRow();
        Integer currentPage = pv.getCurrentPage();

        if (row == null || row <= 0) {
            pv.setRow(MallConstant.MALL_PAGE_ROW);
        }
        if (currentPage == null || currentPage <= 0) {
            pv.setCurrentPage(MallConstant.DEFAULT_CURRENT_PAGE);
        }
    }
}
