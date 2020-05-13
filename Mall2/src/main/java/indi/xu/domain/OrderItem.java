package indi.xu.domain;

import java.io.Serializable;

/**
 * 订单项表
 *
 * @author a_apple
 * @create 2019-11-18 20:58
 */
public class OrderItem implements Serializable {

    private Integer tid;
    private Product product;
    private User user;
    private Order order;
    private int number;

    private String status; //订单状态：购物车，未评价，已评价

    public OrderItem() {
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "tid=" + tid +
                ", product=" + product +
                ", user=" + user +
                ", order=" + order +
                ", number=" + number +
                ", status='" + status + '\'' +
                '}';
    }
}
