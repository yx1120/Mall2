package indi.xu.web.controller.auth;

import indi.xu.domain.Order;
import indi.xu.domain.OrderItem;
import indi.xu.domain.User;
import indi.xu.service.*;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 带权限：前台订单流程
 *
 * @author a_apple
 * @create 2020-04-17 15:03
 */
@RequestMapping("/mallOrder")
@Controller
public class MallOrderController {

    @Resource
    private ProductService productService;
    @Resource
    private UserService userService;
    @Resource
    private OrderService orderService;
    @Resource
    private OrderItemService orderItemService;

    /**
     * 立即购买
     */
    @RequestMapping("/buyRightNow")
    public String buyRightNow(Integer pid, Integer number, @SessionAttribute User user) {

        if (pid == null || productService.get(pid) == null || number == null) {
            return "error/500";
        }

        //进入结算页面,此时还没有生成订单，oid=-1
        OrderItem item = new OrderItem();
        item.setNumber(number);
        item.setUser(userService.get(user.getUid()));
        item.setProduct(productService.get(pid));
        item.setStatus(OrderItemService.CART);

        Order order = new Order();
        order.setOid(-1);
        item.setOrder(order);
        //插入数据库,设置订单项id
        orderItemService.add(item);
        //获取订单项id
        int tid = item.getTid();

        //进入结算页面
        return "redirect:buy?tid=" + tid;
    }

    /**
     * 从购物车里面购买--->进入结算页面
     *
     * @param tid 接收一组订单项的tid
     */
    @RequestMapping("/buy")
    public String buy(@RequestParam(name = "tid") Integer[] tid, HttpSession session) {

        if (tid == null || tid.length == 0) {
            return "error/500";
        }

        //计算总价格
        float totalPrice = 0;
        List<OrderItem> list = new ArrayList<>();
        for (Integer tidTemp : tid) {
            OrderItem item = orderItemService.get(tidTemp);
            productService.setFirstProductImage(item.getProduct());

            list.add(item);
            totalPrice += item.getProduct().getPromotePrice() * item.getNumber();
        }
        //把list放到session中
        session.setAttribute("orderItemList", list);
        session.setAttribute("totalPrice", totalPrice);

        //进入结算页面
        return "fore/cart/buy";
    }

    /**
     * 结算页面：填写邮寄信息
     * 提交订单，进入支付页面
     *
     * @param user          用户
     * @param order         使用Order接收订单信息
     * @param orderItemList 订单项
     */
    @RequestMapping("/createOrder")
    public String createOrder(@SessionAttribute User user,
                              Order order,
                              Model model,
                              @SessionAttribute List<OrderItem> orderItemList,
                              @SessionAttribute Float totalPrice, HttpSession session) {

        String orderCode = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) +
                RandomUtils.nextInt(10000);

        order.setOrderCode(orderCode);
        order.setUser(user);
        order.setCreateDate(new Date());
        order.setStatus(OrderService.waitpay);
        order.setTotal(totalPrice);
        orderService.add(order);

        for (OrderItem item : orderItemList) {
            item.setOrder(order);
            //更新到数据库
            orderItemService.update(item);
        }

        model.addAttribute("order", order);
        session.removeAttribute("orderItemList");

        return "fore/cart/ali_pay";
    }

    /**
     * 点击结算后，进入支付
     */
    @RequestMapping("/payOrder")
    public String payOrder(Integer oid, Model model,
                           @SessionAttribute(required = false) Float totalPrice) {
        if (oid < 0 || orderService.get(oid) == null) {
            return "error/500";
        }
        float total = 0;
        if (totalPrice == null) {
            //此时订单的状态是未付款，需要查询出该订单的订单项，然后计算总价
            List<OrderItem> items = orderItemService.findItemsForOrder(oid);
            for (OrderItem item : items) {
                total += item.getProduct().getPromotePrice() * item.getNumber();
            }
        } else {
            total = totalPrice.floatValue();
        }

        //支付后-->订单项都是未评价状态
        Order order = orderService.get(oid);

        //更新支付日期
        order.setPayDate(new Date());
        order.setStatus(OrderService.waitDelivery);
        orderService.update(order);

        model.addAttribute("order", order);
        model.addAttribute("total", total);

        return "fore/cart/paySuccess";
    }
}
