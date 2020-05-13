package indi.xu.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户表
 *
 * @author a_apple
 * @create 2019-11-18 20:49
 */
public class User implements Serializable {
    private Integer uid;
    private String uname;
    private String realName;
    private String sex;
    private Date birthday;
    //邮箱
    private String email;
    //手机号
    private String telNum;
    //注册时间
    private Date createTime;
    private String password;

    public User() {
    }

    public User(int uid, String uname, String password) {
        this.uid = uid;
        this.uname = uname;
        this.password = password;
    }

    public User(String uname, String password) {
        this.uname = uname;
        this.password = password;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelNum() {
        return telNum;
    }

    public void setTelNum(String telNum) {
        this.telNum = telNum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", uname='" + uname + '\'' +
                ", realName='" + realName + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday=" + birthday +
                ", email='" + email + '\'' +
                ", telNum='" + telNum + '\'' +
                ", createTime='" + createTime + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
