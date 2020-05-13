package indi.xu.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 产品表
 *
 * @author a_apple
 * @create 2019-11-18 20:58
 */
public class Product implements Serializable {

    private Integer pid;
    private String pname;
    private String subTitle;
    private float orignalPrice;
    private float promotePrice;
    private int stock;
    private Date createDate;
    private Category category;

    //非数据库字段，但需要
    private ProductImage firstProductImage;
    private List<ProductImage> productImages;
    private List<ProductImage> productSingleImages;
    private List<ProductImage> productDetailImages;
    private int reviewCount;
    private int saleCount;

    public Product() {
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public float getOrignalPrice() {
        return orignalPrice;
    }

    public void setOrignalPrice(float orignalPrice) {
        this.orignalPrice = orignalPrice;
    }

    public float getPromotePrice() {
        return promotePrice;
    }

    public void setPromotePrice(float promotePrice) {
        this.promotePrice = promotePrice;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public ProductImage getFirstProductImage() {
        return firstProductImage;
    }

    public void setFirstProductImage(ProductImage firstProductImage) {
        this.firstProductImage = firstProductImage;
    }

    public List<ProductImage> getProductImages() {
        return productImages;
    }

    public void setProductImages(List<ProductImage> productImages) {
        this.productImages = productImages;
    }

    public List<ProductImage> getProductSingleImages() {
        return productSingleImages;
    }

    public void setProductSingleImages(List<ProductImage> productSingleImages) {
        this.productSingleImages = productSingleImages;
    }

    public List<ProductImage> getProductDetailImages() {
        return productDetailImages;
    }

    public void setProductDetailImages(List<ProductImage> productDetailImages) {
        this.productDetailImages = productDetailImages;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }

    public int getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(int saleCount) {
        this.saleCount = saleCount;
    }

    @Override
    public String toString() {
        return "Product{" +
                "pid=" + pid +
                ", pname='" + pname + '\'' +
                ", subTitle='" + subTitle + '\'' +
                ", orignalPrice=" + orignalPrice +
                ", promotePrice=" + promotePrice +
                ", stock=" + stock +
                ", createDate=" + createDate +
                ", category=" + category +
                '}';
    }

}
