package indi.xu.domain;

import java.io.Serializable;
import java.util.Date;

/**轮播图
 * @author a_apple
 * @create 2020-04-25 21:09
 */
public class Carousel implements Serializable {

    private Integer carouselId;
    //跳转地址
    private String carouselUrl;
    //创建时间
    private Date createTime;
    //创建用户
    private AdminUser createUser;
    //是否删除
    private Byte isDeleted;
    //是否推送
    private Byte isPush;

    public Byte getIsPush() {
        return isPush;
    }

    public void setIsPush(Byte isPush) {
        this.isPush = isPush;
    }
    //路径：lunbo/carouselId.jpg

    public Integer getCarouselId() {
        return carouselId;
    }

    public void setCarouselId(Integer carouselId) {
        this.carouselId = carouselId;
    }

    public String getCarouselUrl() {
        return carouselUrl;
    }

    public void setCarouselUrl(String carouselUrl) {
        this.carouselUrl = carouselUrl;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public AdminUser getCreateUser() {
        return createUser;
    }

    public void setCreateUser(AdminUser createUser) {
        this.createUser = createUser;
    }

    public Byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "Carousel{" +
                "carouselId=" + carouselId +
                ", carouselUrl='" + carouselUrl + '\'' +
                ", createTime=" + createTime +
                ", createUser=" + createUser +
                ", isDeleted=" + isDeleted +
                ", isPush=" + isPush +
                '}';
    }
}
