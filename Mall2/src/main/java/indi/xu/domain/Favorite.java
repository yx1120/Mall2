package indi.xu.domain;

import indi.xu.utils.DateUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * 收藏表 -》用户+产品
 *
 * @author a_apple
 * @create 2019-11-18 20:59
 */
public class Favorite implements Serializable {

    private Product product;
    private User user;
    private Date fdate;

    public Favorite() {
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

    public Date getFdate() {
        return fdate;
    }

    public void setFdate(Date fdate) {
        this.fdate = fdate;
    }

    @Override
    public String toString() {
        return "Favorite{" +
                "product=" + product +
                ", user=" + user +
                ", fdate=" + fdate +
                '}';
    }
}
