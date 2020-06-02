package indi.xu.web.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import indi.xu.common.MallConstant;
import indi.xu.domain.AdminUser;
import indi.xu.domain.Carousel;
import indi.xu.service.CarouselService;
import indi.xu.utils.PageUtil;
import indi.xu.utils.ResultInfo;
import indi.xu.web.vo.PageVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * @author a_apple
 * @create 2020-05-07 13:18
 */
@Controller
@RequestMapping("/admin")
public class CarouselController {

    @Resource
    private CarouselService carouselService;

    /**
     * 轮播图列表
     */
    @RequestMapping("carousel/list")
    public String list(Model model, Integer row, Integer currentPage) {
        PageVo pv = new PageVo(row, currentPage);
        PageUtil.checkAdminPv(pv);
        //分页插件
        PageHelper.startPage(pv.getCurrentPage(), pv.getRow());
        List<Carousel> list = carouselService.list();
        PageInfo pageInfo = new PageInfo<>(list);

        model.addAttribute("list", list);
        model.addAttribute("page", pageInfo);
        return "admin/listCarousel";
    }

    /**
     * 推送/取消推送 轮播图
     */
    @RequestMapping("carousel/push")
    @ResponseBody
    public ResultInfo pushCarousel(Integer carouselId) {
        ResultInfo info = new ResultInfo();
        Carousel carousel = null;
        if (carouselId == null || (carousel = carouselService.get(carouselId)) == null) {
            info.setFlag(false);
            return info;
        }

        //根据状态，判断是否进行推送 还是取消推送
        if (carousel.getIsPush() == 1) {
            //取消推送
            carousel.setIsPush((byte) 0);
            info.setInfo(""+ MallConstant.NOT_PUSH_CAROUSEL);
        } else {
            carousel.setIsPush((byte) 1);
            info.setInfo(""+MallConstant.PUSH_CAROUSEL);
        }

        carouselService.update(carousel);
        info.setFlag(true);
        return info;
    }

    /**
     * 添加轮播图
     */
    @RequestMapping("carousel/add")
    public String add(HttpServletRequest request, MultipartFile uploadImg, @SessionAttribute AdminUser adUser) throws Exception {

        //注意：页面提交文件的name属性值要和MultipartFile的变量名一样

        //获取上传位置
        String path = request.getSession().getServletContext().getRealPath("img/lunbo");
        //判断路径是否存在,mkdirs()会把父路径一起建立起来
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }

        //获取普通字段--插入数据库
        MultipartHttpServletRequest req = (MultipartHttpServletRequest) request;
        String carouselUrl = req.getParameter("carouselUrl");

        if (StringUtils.isBlank(carouselUrl)) {
            return "error/500";
        }

        Carousel carousel = new Carousel();
        carousel.setCarouselUrl(carouselUrl);
        carousel.setCreateTime(new Date());

        carousel.setCreateUser(adUser);
        carouselService.add(carousel);

        //获取文件名称
        String filename = carousel.getCarouselId() + ".jpg";

        //上传文件
        uploadImg.transferTo(new File(path, filename));

        return "redirect:list";
    }

    @RequestMapping("carousel/delete")
    public String delete(Integer carouselId) {
        if (carouselId == null || carouselService.get(carouselId) == null) {
            return "redirect:list";
        }
        carouselService.delete(carouselId);
        return "redirect:list";
    }

    @RequestMapping("carousel/update")
    @ResponseBody
    public ResultInfo updateUrl(Integer carouselId, String carouselUrl) {
        ResultInfo info = new ResultInfo(false);
        Carousel carousel;
        if (carouselId == null || StringUtils.isBlank(carouselUrl) || (carousel = carouselService.get(carouselId)) == null) {
            return info;
        }

        carousel.setCarouselUrl(carouselUrl);
        carouselService.update(carousel);
        info.setFlag(true);
        return info;
    }
}
