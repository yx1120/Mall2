package indi.xu.web.controller.auth;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import indi.xu.log.anno.SystemControllerLog;
import indi.xu.common.MallResultTip;
import indi.xu.domain.*;
import indi.xu.service.*;
import indi.xu.utils.MD5Util;
import indi.xu.utils.PageUtil;
import indi.xu.utils.PatternUtil;
import indi.xu.utils.ResultInfo;
import indi.xu.web.vo.PageVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * 带权限：个人中心
 *
 * @author a_apple
 * @create 2020-04-17 15:04
 */
@RequestMapping("/space")
@Controller
public class PersonalController {

    @Resource
    private ProductService productService;
    @Resource
    private UserService userService;
    @Resource
    private ReviewService reviewService;
    @Resource
    private FavoriteService favoriteService;
    @Resource
    private OrderService orderService;
    @Resource
    private OrderItemService orderItemService;

    /**
     * 个人中心
     * 个人资料：用户名，注册日期
     * 我的收藏：
     * 我的订单：
     * 修改密码：
     * 我的动态：
     * <p>
     * 这里直接查询用户信息即可
     */
    @SystemControllerLog(description = "查看个人资料", actionType = "info")
    @RequestMapping("/personalSpace")
    public String personalSpace() {
        //session中已经有user
        return "fore/space/space-info";
    }

    /**
     * 我的订单
     */
    @SystemControllerLog(description = "查看我的订单", actionType = "info")
    @RequestMapping("/myOrders/{status}")
    public String myOrders(HttpSession session,
                           @PathVariable(required = false) String status,
                           Model model) {

        User user = (User) session.getAttribute("user");
        List<Order> orders = null;
        if (StringUtils.isBlank(status) || "all".equals(status)) {
            //查询某个用户所有订单
            orders = orderService.findByUid(user.getUid());
            status = "all";
        } else {
            //查询指定status
            orders = orderService.findByStatus(user.getUid(), status);
        }

        orderItemService.fillOrders(orders);

        model.addAttribute("orders", orders);
        model.addAttribute("status", status);
        return "fore/space/space-order";
    }

    /**
     * 确认收货
     */
    @RequestMapping("/confirmOrder")
    public String confirmOrder(Integer oid) {
        if (oid < 0 || orderService.get(oid) == null) {
            return "redirect:myOrders/all";
        }
        //确认收货后-->订单项（待评价
        Order order = orderService.get(oid);
        List<OrderItem> orderItems = orderItemService.findItemsForOrder(oid);
        for (OrderItem orderItem : orderItems) {
            orderItem.setStatus(OrderItemService.WAIT_REVIEW);
            //更新到数据库
            orderItemService.update(orderItem);
        }

        order.setStatus(OrderService.finish);
        order.setConfirmDate(new Date());

        orderService.update(order);

        return "redirect:myOrders/all";
    }

    /**
     * 发货
     */
    @RequestMapping("/deliveryOrder")
    public String deliveryOrder(Integer oid) {
        if (oid < 0 || orderService.get(oid) == null) {
            return "redirect:myOrders/all";
        }

        Order order = orderService.get(oid);
        //设置发货日期
        order.setDeliveryDate(new Date());
        order.setStatus(OrderService.waitConfirm);
        orderService.update(order);

        //重定向管理员订单管理页面
        return "redirect:myOrders/all";
    }

    /**
     * 我的收藏
     */
    @SystemControllerLog(description = "进入我的收藏", actionType = "info")
    @RequestMapping("/myFavorites")
    public String myFavoriteItems(@SessionAttribute User user, Model model) {

        List<Favorite> list = favoriteService.list(user.getUid());
        model.addAttribute("list", list);
        return "fore/space/space-collection";
    }

    /**
     * 我的评价，uid + oid!=-1
     */
    @SystemControllerLog(description = "查看我的评价", actionType = "info")
    @RequestMapping("/myReviews/{status}")
    public String myReviews(@SessionAttribute User user,
                            Integer currentPage, Integer row,
                            @PathVariable(required = false) String status,
                            Model model) {

        PageVo pv = new PageVo(row, currentPage);
        PageUtil.checkMallPv(pv);

        List<OrderItem> items = null;
        if (StringUtils.isBlank(status) || "all".equals(status)) {
            //查询某一个用户所有评价
            PageHelper.startPage(pv.getCurrentPage(), pv.getRow());
            items = orderItemService.findUserItemsByUid(user.getUid());
            status = "all";
        } else {
            //查询指定状态评价
            PageHelper.startPage(pv.getCurrentPage(), pv.getRow());
            items = orderItemService.findByStatus(user.getUid(), status);
        }
        PageInfo<OrderItem> pageInfo = new PageInfo<>(items);
        model.addAttribute("page", pageInfo);
        model.addAttribute("items", items);
        model.addAttribute("status", status);

        return "fore/space/space-review";
    }

    @SystemControllerLog(description = "跳转评价页面", actionType = "info")
    @RequestMapping("/toReviewPage")
    public String toReviewPage(Integer tid, Model model) {
        if (tid < 0 || orderItemService.get(tid) == null) {
            return "redirect:myReviews/all";
        }
        OrderItem item = orderItemService.get(tid);
        model.addAttribute("item", item);
        return "fore/reviewPage";
    }

    /**
     * 接收：pid , oid ,content  ==>Review
     */
    @SystemControllerLog(description = "评价商品", actionType = "info")
    @RequestMapping("/review")
    public String review(@RequestParam("pid") Integer pid,
                         @RequestParam("oid") Integer oid,
                         @RequestParam("tid") Integer tid,
                         @RequestParam("content") String content,
                         @SessionAttribute User user) {
        if (pid < 0 || oid < 0 || tid < 0 ||
                productService.get(pid) == null ||
                orderItemService.get(tid) == null ||
                orderService.get(oid) == null) {
            return "redirect:myReviews/all";
        }

        Review review = new Review();
        review.setProduct(productService.get(pid));
        review.setOrder(orderService.get(oid));
        review.setContent(content);

        review.setUser(user);
        review.setCreateDate(new Date());

        reviewService.add(review);
        //更新orderitem的状态
        OrderItem item = orderItemService.get(tid);
        item.setStatus(OrderItemService.REVIEWED);
        orderItemService.update(item);

        return "redirect:myReviews/all";
    }

    /**
     * 用户更新个人信息
     */
    @SystemControllerLog(description = "更新个人信息", actionType = "info")
    @RequestMapping("/updateUser")
    @ResponseBody
    public ResultInfo updateUser(User newUser, Date birthday, @SessionAttribute User user) {

        ResultInfo info = null;

        String email = newUser.getEmail();
        if (!PatternUtil.isEmail(email) || StringUtils.isBlank(newUser.getUname())
        || StringUtils.isBlank(newUser.getSex()) ) {
            info = new ResultInfo(false);
            return info;
        }

        user.setUname(newUser.getUname());
        user.setRealName(newUser.getRealName());
        user.setSex(newUser.getSex());
        user.setBirthday(birthday);
        user.setEmail(email);

        userService.update(user);
        info = new ResultInfo(true);
        return info;
    }


    @RequestMapping("/toFixPassword")
    public String toFixPassword() {
        return "fore/space/space-fixpass";
    }

    /**
     * 检查原密码是否正确
     */
    @RequestMapping("/checkPassword")
    @ResponseBody
    public ResultInfo checkPassword(String password, @SessionAttribute User user) {

        ResultInfo info = null;
        password = MD5Util.MD5Encode(password, "UTF-8");
        if (StringUtils.isNotBlank(password) && password.equals(user.getPassword())) {
            info = new ResultInfo(true);
        } else {
            info = new ResultInfo(false, MallResultTip.INIT_PASSWORD_ERROR.getTipInfo());
        }
        return info;
    }

    /**
     * 更新用户密码
     */
    @SystemControllerLog(description = "更新用户密码", actionType = "info")
    @RequestMapping("/setNewPassword")
    public String setNewPassword(@RequestParam(value = "newPassword1") String newPassword1,
                                 @RequestParam(value = "newPassword2") String newPassword2,
                                 @SessionAttribute User user, HttpSession session,
                                 Model model) {
        if (StringUtils.isBlank(newPassword1) || StringUtils.isBlank(newPassword2) || !newPassword1.equals(newPassword2)) {
            model.addAttribute("errorInfo", "2次密码输入不一致");
            return "redirect:personalSpace";
        }

        user.setPassword(MD5Util.MD5Encode(newPassword1, "UTF-8"));
        userService.update(user);
        //清除session中的uesr,重新登录
        session.removeAttribute("user");

        return "fore/fixpass_success";
    }
}
