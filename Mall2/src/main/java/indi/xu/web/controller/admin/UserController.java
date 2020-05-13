package indi.xu.web.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import indi.xu.domain.User;
import indi.xu.service.UserService;
import indi.xu.utils.PageUtil;
import indi.xu.web.vo.PageVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * 后台：用户管理
 *
 * @author a_apple
 * @create 2020-02-28 20:03
 */

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping("/admin_list")
    public String list(Model model, Integer row, Integer currentPage) {

        PageVo pv = new PageVo(row, currentPage);
        PageUtil.checkAdminPv(pv);
        //分页插件使用,第几页，需要几条
        PageHelper.startPage(pv.getCurrentPage(), pv.getRow());

        List<User> users = userService.list();
        PageInfo<User> page = new PageInfo<User>(users);

        model.addAttribute("list", users);
        model.addAttribute("page", page);
        return "admin/listUser";
    }
}
