package indi.xu.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

/**
 * 分类表
 *
 * @author a_apple
 * @create 2019-11-18 20:57
 */
@JsonIgnoreProperties(value = {"handler"})
public class Category implements Serializable {

    private Integer cid;
    private String cname;
    private Category parentCategory;
    private Integer categoryLevel;
    private Byte isDeleted;
    private List<Product> products;

    public Category() {
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public Category getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }

    public Integer getCategoryLevel() {
        return categoryLevel;
    }

    public void setCategoryLevel(Integer categoryLevel) {
        this.categoryLevel = categoryLevel;
    }

    public Byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Category{" +
                "cid=" + cid +
                ", cname='" + cname + '\'' +
                ", parentCategory=" + parentCategory +
                ", categoryLevel=" + categoryLevel +
                ", isDeleted=" + isDeleted +
                ", products=" + products +
                '}';
    }
}
