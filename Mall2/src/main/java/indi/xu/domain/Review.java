package indi.xu.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 评价表 -》用户+订单+产品
 *
 * @author a_apple
 * @create 2019-11-18 20:59
 */
public class Review implements Serializable {

    private Integer rid;
    private String content;
    private User user;
    private Product product;
    private Date createDate;
    private Order order;

    public Review() {
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "Review{" +
                "rid=" + rid +
                ", content='" + content + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}
