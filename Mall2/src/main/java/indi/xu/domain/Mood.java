package indi.xu.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author a_apple
 * @create 2020-02-29 9:55
 */
public class Mood implements Serializable {

    private Integer mid;
    private String content;
    private Date createTime;
    private Date praiseNum;
    //删除，全是公开的
    private String status;
    private User user;

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getPraiseNum() {
        return praiseNum;
    }

    public void setPraiseNum(Date praiseNum) {
        this.praiseNum = praiseNum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Mood{" +
                "mid=" + mid +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                ", praiseNum=" + praiseNum +
                ", status='" + status + '\'' +
                ", user=" + user +
                '}';
    }
}
