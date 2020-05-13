package indi.xu.domain;

import java.io.Serializable;

/**
 * 产品图片表 -》产品
 *
 * @author a_apple
 * @create 2019-11-18 20:57
 */
public class ProductImage implements Serializable {

    private Integer gid;
    private Product product;
    private String type;

    public ProductImage() {
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ProductImage{" +
                "gid=" + gid +
                ", product=" + product +
                ", type='" + type + '\'' +
                '}';
    }
}
