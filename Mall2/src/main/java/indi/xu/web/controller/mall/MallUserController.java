package indi.xu.web.controller.mall;

import indi.xu.log.anno.SystemControllerLog;
import indi.xu.common.MallConstant;
import indi.xu.common.MallResultTip;
import indi.xu.domain.AdminUser;
import indi.xu.utils.ResultInfo;
import indi.xu.domain.User;
import indi.xu.service.*;
import indi.xu.utils.MD5Util;
import indi.xu.utils.NumberUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.util.HtmlUtils;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.Random;

/**
 * 前台用户相关操作
 *
 * @author a_apple
 * @create 2020-04-17 8:48
 */
@RequestMapping("/fore")
@SessionAttributes(value = {"user"})
@Controller
public class MallUserController {

    @Resource
    private UserService userService;
    @Resource
    private AdminUserService adminUserService;

    @SystemControllerLog(description = "注册成功", actionType = "info")
    @RequestMapping("/registerSuccess")
    public String registerSuccess() {
        return "fore/reg_success";
    }

    /**
     * 验证码
     */
    @RequestMapping("checkCode")
    public void checkCode(HttpSession session, HttpServletResponse response) throws IOException {
        //服务器通知浏览器不要缓存
        response.setHeader("expires", "0");
        response.setHeader("pragma", "no-cache");
        response.setHeader("cache-control", "no-cache");

        //在内存中创建一个长80，宽30的图片，默认黑色背景
        //参数三：颜色
        int width = MallConstant.CHECK_CODE_WIDTH;
        int height = MallConstant.CHECK_CODE_HEIGHT;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        //获取画笔
        Graphics g = image.getGraphics();
        //设置画笔颜色为灰色
        g.setColor(Color.GRAY);
        //填充图片
        g.fillRect(0, 0, width, height);

        //产生4个随机验证码，12Ey
        String codeServer = getCheckCode();
        //将验证码放入HttpSession中
        session.setAttribute("codeServer", codeServer);

        //设置画笔颜色为黄色
        g.setColor(Color.YELLOW);
        //设置字体的小大
        g.setFont(new Font("黑体", Font.BOLD, 24));
        //向图片上写入验证码
        g.drawString(codeServer, 15, 25);
        //再画2条干扰线
        Random rd = new Random();
        g.setColor(new Color(185, 228, 219));
        for (int i = 0; i < 2; i++) {
            //线尽量集中在里面
            int x1 = rd.nextInt(60) + 10;
            int x2 = rd.nextInt(60) + 10;
            int y1 = rd.nextInt(15) + 5;
            int y2 = rd.nextInt(15) + 5;
            g.drawLine(x1, y1, x2, y2);
        }

        //将内存中的图片输出到浏览器
        //参数一：图片对象
        //参数二：图片的格式，如PNG,JPG,GIF
        //参数三：图片输出到哪里去
        ImageIO.write(image, "PNG", response.getOutputStream());
    }

    /**
     * 产生4位随机字符串
     */
    private String getCheckCode() {
        String base = "123456789ABCDEFGHJKLUVWXYZabcdefghijk";
        int size = base.length();
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 4; i++) {
            int index = r.nextInt(size);
            char c = base.charAt(index);
            sb.append(c);
        }
        return sb.toString();
    }

    /**
     * 用户登录
     * 需要注意的地方：如何发送表单json数据，如何接受josn数据？使用@RequestBody注解，使用map接受。
     * 因为这里还提交了验证码，使用@Param或@RequestParam注解获取不到
     * 先把放到map，然后取出来后，封装对象
     */
    @RequestMapping("/userLogin")
    @ResponseBody
    public ResultInfo userLogin(@RequestBody Map<String, Object> map,
                                @SessionAttribute String codeServer,
                                Model model, HttpSession session) {
        ResultInfo info = null;

        String password = (String) map.get("password");
        String telNum = (String) map.get("telNum");
        String checkCode = (String) map.get("checkCode");

        if (StringUtils.isBlank(password) || StringUtils.isBlank(telNum) || StringUtils.isBlank(checkCode)) {
            info = new ResultInfo(false,MallResultTip.LOGIN_INFO_ERROR.getTipInfo());
            return info;
        }

        //删除验证码
        session.removeAttribute("codeServer");
        User user = new User();
        user.setPassword(MD5Util.MD5Encode(password, "UTF-8"));
        user.setTelNum(telNum);

        //验证码错误
        if (!checkCode.equalsIgnoreCase(codeServer)) {
            info = new ResultInfo(false, MallResultTip.CHECK_CODE_ERROR.getTipInfo());
            return info;
        }

        //验证码正确
        if ((user = userService.findUser(user)) == null) {
            info = new ResultInfo(false, MallResultTip.LOGIN_USER_INFO_ERROR.getTipInfo());
        } else {
            //登录成功
            info = new ResultInfo(true);
            model.addAttribute("user", user);
        }
        return info;
    }

    /**
     * 用户注销
     */
    @RequestMapping("/logout")
    public String logout(SessionStatus status) {
        status.setComplete();
        return "redirect:home";
    }

    @RequestMapping("/checkRepeatName")
    @ResponseBody
    public ResultInfo checkRepeatName(String telNum) {
        ResultInfo info = null;
        if (StringUtils.isNotBlank(telNum) && userService.findRepeatTelNumber(telNum) == null) {
            info = new ResultInfo(false, MallResultTip.REGISTER_OK.getTipInfo());
            return info;
        }
        //存在重复名
        info = new ResultInfo(true, MallResultTip.REGISTER_NO.getTipInfo());
        return info;
    }

    @RequestMapping("/userRegister")
    @ResponseBody
    public ResultInfo userRegister(@RequestBody Map<String, Object> map,
                                   @SessionAttribute String codeServer,
                                   HttpSession session) {
        ResultInfo info = null;

        String phone = (String) map.get("telNum");
        String password = (String) map.get("password");
        String checkCode = (String) map.get("checkCode");

        if (StringUtils.isBlank(phone) || !NumberUtil.isPhone(phone) ||
                StringUtils.isBlank(password) || StringUtils.isBlank(checkCode)) {
            //验证码错误
            info = new ResultInfo(false, MallResultTip.PHONE_FORMAT_ERROR.getTipInfo());
            return info;
        }

        session.removeAttribute("codeServer");

        if (StringUtils.isNotBlank(checkCode) && codeServer.equalsIgnoreCase(checkCode)) {
            //check_success
            phone = HtmlUtils.htmlEscape(phone);
            User regUser = new User();
            regUser.setTelNum(phone);

            password = MD5Util.MD5Encode(password, "UTF-8");
            regUser.setPassword(password);
            regUser.setCreateTime(new Date());
            //用户刚注册，默认昵称为手机号
            regUser.setUname(phone);

            userService.add(regUser);
            info = new ResultInfo(true);
        } else {
            //验证码错误
            info = new ResultInfo(false, MallResultTip.CHECK_CODE_ERROR.getTipInfo());
        }
        return info;
    }

    @RequestMapping("/toAdminLogin")
    public String toAdminLogin(AdminUser adminUser, Model model, HttpSession session) {

        AdminUser adUser = adminUserService.findAdminUser(adminUser);
        if (adUser == null) {
            model.addAttribute("errorMsg", MallResultTip.LOGIN_INFO_ERROR.getTipInfo());
            return "fore/adminLogin";
        }

        //添加到session
        session.setAttribute("adUser", adUser);
        return "redirect:../firstCategory/admin_list";

    }

    @RequestMapping("/toAdminLogout")
    public String toAdminLogout(HttpSession session) {
        session.removeAttribute("adUser");
        return "redirect:home";
    }
}
