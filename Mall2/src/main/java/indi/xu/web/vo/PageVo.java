package indi.xu.web.vo;

/**
 * @author a_apple
 * @create 2020-04-17 9:48
 */
public class PageVo {

    private Integer row;
    private Integer currentPage;

    public PageVo(Integer row, Integer currentPage) {
        this.row = row;
        this.currentPage = currentPage;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }
}
