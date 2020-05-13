package indi.xu.service.impl;

import indi.xu.dao.OrderDao;
import indi.xu.domain.Order;
import indi.xu.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author a_apple
 * @create 2019-12-03 16:25
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public void add(Order order) {
        orderDao.add(order);
    }

    @Override
    public void update(Order bean) {
        orderDao.update(bean);
    }

    @Override
    public Order get(int oid) {
        return orderDao.get(oid);
    }

    @Override
    public void delete(int oid) {
        orderDao.delete(oid);
    }

    @Override
    public List<Order> list() {
        return orderDao.list();
    }

    @Override
    public List<Order> findByStatus(int uid, String status) {
        return orderDao.findByStatus(uid, status);
    }

    @Override
    public List<Order> findByUid(int uid) {
        return orderDao.findByUid(uid);
    }
}
