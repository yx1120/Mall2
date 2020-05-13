package indi.xu.domain;

import java.io.Serializable;

/**
 * 属性值表 -》产品+属性名
 *
 * @author a_apple
 * @create 2019-11-18 20:58
 */
public class PropertyValue implements Serializable {

    private Integer vid;
    private Product product;
    private Property property;
    private String value;

    public PropertyValue() {
    }

    public Integer getVid() {
        return vid;
    }

    public void setVid(Integer vid) {
        this.vid = vid;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "PropertyValue{" +
                "vid=" + vid +
                ", product=" + product +
                ", property=" + property +
                ", value='" + value + '\'' +
                '}';
    }
}
