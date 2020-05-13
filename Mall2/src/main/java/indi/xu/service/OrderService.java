package indi.xu.service;


import indi.xu.domain.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author a_apple
 * @create 2019-12-03 16:25
 */
public interface OrderService {

    String waitpay = "waitPay";
    String waitDelivery = "waitDelivery";
    String waitConfirm = "waitConfirm";
    String waitReview = "waitReview";
    String finish = "finish";
    String delete = "delete";

    void add(Order order);

    void update(Order bean);

    Order get(int oid);

    void delete(int oid);

    List<Order> list();


    List<Order> findByStatus(int uid, String status);

    List<Order> findByUid(int uid);

}
