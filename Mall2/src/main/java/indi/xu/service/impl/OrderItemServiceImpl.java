package indi.xu.service.impl;

import indi.xu.dao.OrderItemDao;
import indi.xu.domain.Order;
import indi.xu.domain.OrderItem;
import indi.xu.domain.Product;
import indi.xu.service.OrderItemService;
import indi.xu.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author a_apple
 * @create 2019-12-03 16:25
 */

@Service("orderItemService")
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    private ProductService productService;
    @Autowired
    private OrderItemDao orderItemDao;

    @Override
    public void add(OrderItem bean) {
        orderItemDao.add(bean);
    }

    @Override
    public OrderItem get(int tid) {
        return orderItemDao.get(tid);
    }

    @Override
    public void delete(int tid) {
        orderItemDao.delete(tid);
    }

    @Override
    public void update(OrderItem bean) {
        orderItemDao.update(bean);
    }

    @Override
    public Integer getSaleCount(int pid) {
        return orderItemDao.getSaleCount(pid);
    }

    @Override
    public List<OrderItem> findItemsForOrder(int oid) {
        return orderItemDao.findItemsByOid(oid);
    }

    @Override
    public List<OrderItem> findItemsForShopCart(int uid) {
        List<OrderItem> items = orderItemDao.findItemsByUid(uid);
        if(items != null){
            items.forEach(cartItem->productService.setFirstProductImage(cartItem.getProduct()));
        }
        return items;
    }

    @Override
    public OrderItem isExist(int uid, int pid) {
        return orderItemDao.isExist(uid, pid);
    }

    /**
     * 填充Order的非数据库bean字段
     * orderItems ,total(总价格    totalNumber(总数量
     *
     * @param order
     */
    @Override
    public void fillOrder(Order order) {
        List<OrderItem> list = this.findItemsForOrder(order.getOid());
        int total = 0;
        int totalNumber = 0;
        for (OrderItem item : list) {
            productService.setFirstProductImage(item.getProduct());
            //设置总价格。和购买总数量
            total += item.getNumber() * item.getProduct().getPromotePrice();
            totalNumber += item.getNumber();
        }
        order.setOrderItems(list);
        order.setTotal(total);
        order.setTotalNumber(totalNumber);
    }

    @Override
    public void fillOrders(List<Order> os) {
        os.forEach(this::fillOrder);
    }

    @Override
    public List<OrderItem> findByUidAndOid(int uid) {
        return orderItemDao.findByUidAndOid(uid);
    }

    @Override
    public List<OrderItem> findByStatus(int uid, String status) {
        return orderItemDao.findByStatus(uid, status);
    }

    /**
     * 查询用户所有评价
     *
     */
    @Override
    public List<OrderItem> findUserItemsByUid(Integer uid) {
        return orderItemDao.findUserItemsByUid(uid);
    }
}
