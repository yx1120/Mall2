package indi.xu.domain;

import java.io.Serializable;

/**
 * 属性表
 * @author a_apple
 * @create 2019-11-18 20:58
 */
public class Property implements Serializable {

    private Integer pyid;
    private Category category;
    private String pyname;

    public Property() {
    }

    public Integer getPyid() {
        return pyid;
    }

    public void setPyid(Integer pyid) {
        this.pyid = pyid;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getPyname() {
        return pyname;
    }

    public void setPyname(String pyname) {
        this.pyname = pyname;
    }

    @Override
    public String toString() {
        return "Property{" +
                "pyid=" + pyid +
                ", category=" + category +
                ", pyname='" + pyname + '\'' +
                '}';
    }
}
