package indi.xu.web.controller.mall;

import indi.xu.domain.AdminUser;
import indi.xu.domain.Carousel;
import indi.xu.domain.Category;
import indi.xu.log.SystemLogAspect;
import indi.xu.service.CarouselService;
import indi.xu.service.CategoryService;
import indi.xu.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 商城首页
 *
 * @author a_apple
 * @create 2020-04-17 8:41
 */
@RequestMapping("/fore")
@Controller
public class IndexController {

    @Resource
    private CategoryService categoryService;
    @Resource
    private CarouselService carouselService;

    @Resource
    private RedisUtil redisUtil;

    private static final Logger logger = LoggerFactory.getLogger(SystemLogAspect.class);

    /**
     * 跳转到首页
     */
    @RequestMapping("/home")
    public String toHome() {
        return "fore/home/home";
    }

    @RequestMapping("/login")
    public void login() {
    }

    @RequestMapping("/register")
    public void register() {
    }

    @RequestMapping("/adminLogin")
    public String adminLogin(@SessionAttribute(required = false) AdminUser adUser) {
        if(adUser!=null){
            return "redirect:../firstCategory/admin_list";
        }
        return "fore/adminLogin";
    }

    /**
     * 一级分类：
     *    首页左边的分类条
     */
    @RequestMapping("/fCategories")
    @ResponseBody
    public List<Category> home() {
        //查询分类
        List<Category> list = new ArrayList<>();
        Category fc;
        String key = "firstCategories";

        if (redisUtil.zSize(key) == 0) {
            logger.info("缓存---没有---一级分类数据...");
            //从数据库拿，再存入缓存
            list = categoryService.findByLevel(1);
            if (list != null) {
                for (Category firstCategory : list) {
                    redisUtil.zAdd(key, firstCategory.getCname(), firstCategory.getCid());
                }
            }
            return list;
        }

        logger.info("缓存---有---一级分类数据...");
        Set<Object> names = redisUtil.zRange(key, 0, -1);
        for (Object name : names) {
            fc = new Category();
            fc.setCname((String) name);
            Integer id = redisUtil.zScore(key, name);
            fc.setCid(id);

            list.add(fc);
        }
        return list;
    }

    /**
     * 鼠标移动到一级分类上，发起请求查询二级分类
     * 二级分类：
     * 这个要放到redis缓存中，因为不经常变化。
     * 如何保持缓存与数据库与页面的一致性？
     * 对数据库大分类的增删操作，需要先更新数据库，然后更新缓存
     *
     * @param parentId 一级分类id
     * @return 二级分类集合
     */
    @RequestMapping("/categories")
    @ResponseBody
    public List<Category> findCategories(Integer parentId) {

        String key = "categories" + parentId;

        List<Category> list = new ArrayList<>();
        Category category;
        //从缓存取
        if (redisUtil.zSize(key) == 0) {
            logger.debug("缓存---没有---二级分类数据...");
            //从数据库拿,存入有序set缓存
            list = categoryService.findByParentId(parentId);
            if (list != null) {
                for (Category c : list) {
                    redisUtil.zAdd(key, c.getCname(), c.getCid());
                }
                //设置缓存过期时间
                redisUtil.expire(key, 60 * 60);
            }
            return list;
        }

        logger.debug("缓存---有---二级分类数据...");
        Set<Object> cNames = redisUtil.zRange(key, 0, -1);
        for (Object cname : cNames) {
            category = new Category();
            category.setCname((String) cname);
            category.setCid(redisUtil.zScore(key, cname));

            list.add(category);
        }

        return list;
    }

    /**
     * 查找已推送的轮播图
     */
    @RequestMapping("/findCarousels")
    @ResponseBody
    public List<Carousel> findCarousels() {
        return carouselService.findPushed();
    }
}
