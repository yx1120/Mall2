package indi.xu.domain;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 订单表 -》用户
 *
 * @author a_apple
 * @create 2019-11-18 20:52
 */
public class Order implements Serializable {

    private Integer oid;
    //订单号
    private String orderCode;
    //收货地址
    private String address;
    //邮编
    private String post;
    //收货人信息
    private String receiver;
    //手机号码
    private String mobile;
    //用户备注信息
    private String userMessage;
    //订单创建日期
    private Date createDate;
    //订单支付日期
    private Date payDate;
    //发货日期
    private Date deliveryDate;
    //确认收货日期
    private Date confirmDate;
    //订单所属用户 1对多
    private User user;

    //所有订单项
    private List<OrderItem> orderItems;
    //订单总价
    private float total;
    //订单所有商品的数量
    private int totalNumber;
    //订单状态
    private String status;

    public Order() {
    }

    /*public String getStatusDesc(){
        String desc ="未知";
        switch(status){
           case OrderDaoImpl.waitPay:
               desc="待付款";
               break;
           case OrderDaoImpl.waitDelivery:
               desc="待发货";
               break;
           case OrderDaoImpl.waitConfirm:
               desc="待收货";
               break;
           case OrderDaoImpl.waitReview:
               desc="等评价";
               break;
           case OrderDaoImpl.finish:
               desc="完成";
               break;
           case OrderDaoImpl.delete:
               desc="刪除";
               break;
           default:
               desc="未知";
        }
        return desc;
    }*/

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Date getConfirmDate() {
        return confirmDate;
    }

    public void setConfirmDate(Date confirmDate) {
        this.confirmDate = confirmDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public int getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "oid=" + oid +
                ", orderCode='" + orderCode + '\'' +
                ", address='" + address + '\'' +
                ", post='" + post + '\'' +
                ", receiver='" + receiver + '\'' +
                ", mobile='" + mobile + '\'' +
                ", userMessage='" + userMessage + '\'' +
                ", createDate=" + createDate +
                ", payDate=" + payDate +
                ", deliveryDate=" + deliveryDate +
                ", confirmDate=" + confirmDate +
                ", user=" + user +
                ", orderItems=" + orderItems +
                ", total=" + total +
                ", totalNumber=" + totalNumber +
                ", status='" + status + '\'' +
                '}';
    }
}
