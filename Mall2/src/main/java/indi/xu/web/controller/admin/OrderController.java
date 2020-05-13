package indi.xu.web.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import indi.xu.domain.Order;
import indi.xu.service.OrderItemService;
import indi.xu.service.OrderService;
import indi.xu.utils.PageUtil;
import indi.xu.web.vo.PageVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 后台：订单控制器
 *
 * @author a_apple
 * @create 2020-02-28 20:03
 */

@Controller
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderService orderService;
    @Resource
    private OrderItemService orderItemService;


    @RequestMapping("/admin_list")
    public String list(Model model, Integer row, Integer currentPage) {
        PageVo pv = new PageVo(row, currentPage);
        PageUtil.checkAdminPv(pv);
        PageHelper.startPage(pv.getCurrentPage(), pv.getRow());

        List<Order> list = orderService.list();
        orderItemService.fillOrders(list);

        PageInfo<Order> pageInfo = new PageInfo<Order>(list);

        model.addAttribute("list", list);
        model.addAttribute("page", pageInfo);

        return "admin/listOrder";
    }

    /**
     * 发货
     */
    @RequestMapping("/admin_deliveryOrder")
    public String deliveryOrder(Integer oid) {

        if (oid < 0 || orderService.get(oid) == null) {
            return "error/500";
        }

        Order order = orderService.get(oid);
        //设置发货日期
        order.setDeliveryDate(new Date());
        order.setStatus(OrderService.waitConfirm);
        orderService.update(order);

        //重定向管理员订单管理页面
        return "redirect:admin_list";
    }
}
