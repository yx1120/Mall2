package indi.xu.web.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import indi.xu.common.MallConstant;
import indi.xu.domain.Category;
import indi.xu.service.CategoryService;
import indi.xu.utils.PageUtil;
import indi.xu.utils.RedisUtil;
import indi.xu.utils.ResultInfo;
import indi.xu.web.vo.PageVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author a_apple
 * @create 2020-03-31 13:22
 */
@Controller
@RequestMapping("/firstCategory")
public class FirstCategoryController {

    @Resource
    private CategoryService categoryService;
    @Resource
    private RedisUtil redisUtil;

    /**
     * 刷新一级分类缓存
     */
    private void updateCache() {
        System.out.println("刷新一级分类缓存...");
        //1.更新缓存
        String key = "firstCategories";
        redisUtil.del("firstCategories");
        //list():找所有一级分类
        List<Category> list = categoryService.findByLevel(MallConstant.CATEGORY_LEVEL_ONE);

        for (Category fc : list) {
            redisUtil.zAdd(key, fc.getCname(), fc.getCid());
        }
        redisUtil.expire(key, 60 * 60);
    }


    @RequestMapping("/admin_list")
    public String list(Model model, Integer row, Integer currentPage) {

        PageVo pv = new PageVo(row, currentPage);
        PageUtil.checkAdminPv(pv);
        //分页插件使用,第几页，需要几条
        PageHelper.startPage(pv.getCurrentPage(), pv.getRow());
        List<Category> list = categoryService.findByLevel(MallConstant.CATEGORY_LEVEL_ONE);
        PageInfo pageInfo = new PageInfo<>(list);

        model.addAttribute("list", list);
        model.addAttribute("page", pageInfo);
        return "admin/listFirstCategory";
    }

    @RequestMapping("/admin_delete")
    public String delete(Integer cid) {
        if (cid < 0 || categoryService.get(cid) == null) {
            return "error/500";
        }
        //要先删除这个分类下面的所有二级。。
        categoryService.delete(cid);
        updateCache();
        return "redirect:admin_list";
    }

    //添加一级分类
    @RequestMapping("/admin_add")
    public String add(String cname) {
        if (StringUtils.isBlank(cname) || categoryService.findByName(cname) != null) {
            return "redirect:admin_list";
        }

        Category firstCategory = new Category();
        firstCategory.setCname(cname);
        firstCategory.setCategoryLevel(MallConstant.CATEGORY_LEVEL_ONE);
        Category parent = new Category();
        parent.setCid(0);
        firstCategory.setParentCategory(parent);

        categoryService.add(firstCategory);
        updateCache();

        return "redirect:admin_list";
    }

    //更新一级分类
    @RequestMapping("/admin_update")
    @ResponseBody
    public ResultInfo update(Category category) {
        ResultInfo info = new ResultInfo();
        if (category == null
                || categoryService.get(category.getCid()) == null
                || StringUtils.isBlank(category.getCname())) {
            info.setFlag(false);
            return info;
        }

        categoryService.update(category);
        updateCache();
        info.setFlag(true);
        return info;
    }
}
