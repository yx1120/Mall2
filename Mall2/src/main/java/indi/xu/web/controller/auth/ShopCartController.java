package indi.xu.web.controller.auth;

import indi.xu.domain.*;
import indi.xu.service.FavoriteService;
import indi.xu.service.OrderItemService;
import indi.xu.service.ProductService;
import indi.xu.service.UserService;
import indi.xu.utils.ResultInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 带权限：
 * 购物车操作
 *
 * @author a_apple
 * @create 2020-04-17 8:46
 */
@RequestMapping("/shopCart")
@Controller
public class ShopCartController {

    @Resource
    private ProductService productService;
    @Resource
    private UserService userService;
    @Resource
    private FavoriteService favoriteService;
    @Resource
    private OrderItemService orderItemService;

    /**
     * 我的购物车
     */
    @RequestMapping("/myCart")
    public String showCart(@SessionAttribute User user, Model model) {
        List<OrderItem> cartItems = orderItemService.findItemsForShopCart(user.getUid());

        model.addAttribute("cartItems", cartItems);
        return "fore/cart/shopCart";
    }

    /**
     * 从购物车里删除
     */
    @RequestMapping("/deleteOrderItem")
    @ResponseBody
    public ResultInfo deleteOrderItem(Integer tid) {
        ResultInfo info = null;
        if (tid < 0 || orderItemService.get(tid) == null) {
            info = new ResultInfo(false);
            return info;
        }

        orderItemService.delete(tid);
        info = new ResultInfo(true);
        return info;
    }

    /**
     * 加入购物车
     */
    @RequestMapping("/addCart")
    @ResponseBody
    public ResultInfo addCart(Integer pid, Integer number, @SessionAttribute User user) {
        ResultInfo info = null;

        if (pid < 0 || productService.get(pid) == null || number == null || number < 0) {
            info = new ResultInfo(false);
            return info;
        }

        //操作orderitem
        OrderItem item = new OrderItem();
        item.setProduct(productService.get(pid));
        item.setNumber(number);
        item.setUser(userService.get(user.getUid()));
        item.setStatus(OrderItemService.CART);

        Order order = new Order();
        order.setOid(-1);
        item.setOrder(order);

        //先判断是否加入过
        OrderItem exist = orderItemService.isExist(user.getUid(), pid);

        if (exist != null) {
            item.setTid(exist.getTid());
            item.setNumber(number + exist.getNumber());
            orderItemService.update(item);
        } else {
            //加入购物车，订单号为-1
            orderItemService.add(item);
        }

        info = new ResultInfo(true);
        return info;
    }

    /**
     * 修改购物车里面的数量
     */
    @RequestMapping("/fixNumber")
    public String fixNumber(Integer tid, Integer number) {

        if (tid < 0 || orderItemService.get(tid) == null || number == null || number < 0) {
            return "fore/cart/shopCart";
        }

        OrderItem item = orderItemService.get(tid);

        Order order = new Order();
        order.setOid(-1);
        item.setOrder(order);
        item.setNumber(number);

        orderItemService.update(item);
        return "fore/cart/shopCart";
    }

    /**
     * 点击收藏、取消收藏
     */
    @RequestMapping("/addAndDelFavorite")
    @ResponseBody
    public ResultInfo addAndDelFavorite(Integer pid, String status, @SessionAttribute User user) {

        ResultInfo info = null;

        if (pid < 0 || productService.get(pid) == null) {
            info = new ResultInfo(false);
            return info;
        }

        String mystaus = "add";
        if (status.equals(mystaus)) {
            //添加收藏
            Favorite favorite = new Favorite();
            favorite.setProduct(productService.get(pid));
            favorite.setUser(userService.get(user.getUid()));
            favorite.setFdate(new Date());
            favoriteService.add(favorite);
        } else {
            //删除收藏 "del"
            favoriteService.delete(pid, user.getUid());
        }

        info = new ResultInfo(true);
        return info;
    }
}
