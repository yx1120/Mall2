package indi.xu.service;


import indi.xu.domain.Order;
import indi.xu.domain.OrderItem;

import java.util.List;

/**
 * @author a_apple
 * @create 2019-12-03 16:25
 */
public interface OrderItemService {

    String WAIT_REVIEW = "waitReview";
    String REVIEWED = "reviewed";
    String CART = "cart";

    void add(OrderItem bean);

    OrderItem get(int tid);

    void delete(int tid);

    void update(OrderItem bean);


    Integer getSaleCount(int pid);

    List<OrderItem> findItemsForOrder(int oid);

    List<OrderItem> findItemsForShopCart(int uid);

    OrderItem isExist(int uid, int pid);

    /**
     * 放到service层
     * 为订单设置订单项集合
     */
    void fillOrder(Order order);

    void fillOrders(List<Order> os);

    List<OrderItem> findByUidAndOid(int uid);

    List<OrderItem> findByStatus(int uid, String status);

    List<OrderItem> findUserItemsByUid(Integer uid);
}
