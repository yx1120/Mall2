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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 后台：商品分类控制器
 *
 * @author a_apple
 * @create 2020-02-28 20:03
 */
@Controller
@RequestMapping("/category")
public class CategoryController {

    @Resource
    private CategoryService categoryService;
    @Resource
    private RedisUtil redisUtil;

    /**
     * 刷新二级分类缓存
     */
    private void updateCache(int parentId) {
        System.out.println("刷新二级分类缓存..." + parentId);
        String key = "categories" + parentId;
        redisUtil.del(key);

        List<Category> categories = categoryService.findByParentId(parentId);
        for (Category category : categories) {
            redisUtil.zAdd(key, category.getCname(), category.getCid());
        }
        redisUtil.expire(key, 60 * 60);
    }

    /**
     * 根据一级分类查找二级分类
     */
    @RequestMapping("/admin_list")
    public String list(Model model, Integer row, Integer currentPage, @RequestParam("parentId") Integer parentId) {


        PageVo pv = new PageVo(row, currentPage);
        PageUtil.checkAdminPv(pv);
        //分页插件使用,第几页，需要几条
        PageHelper.startPage(pv.getCurrentPage(), pv.getRow());
        List<Category> categorys = categoryService.findByParentId(parentId);
        PageInfo pageInfo = new PageInfo<>(categorys);

        //把上级分类名称存入
        Category parentCategory = categoryService.get(parentId);

        model.addAttribute("parentCategory", parentCategory);
        model.addAttribute("list", categorys);
        model.addAttribute("page", pageInfo);
        model.addAttribute("params", "&parentId=" + parentId);

        return "admin/listCategory";
    }

    @RequestMapping("/admin_delete")
    public String delete(Integer cid) {

        if (cid < 0 || categoryService.get(cid) == null) {
            return "error/500";
        }

        //分页需要
        Integer parentId = categoryService.get(cid).getParentCategory().getCid();
        //先要删除分类的所有属性,产品
        categoryService.delete(cid);
        //刷新缓存
        updateCache(parentId);
        return "redirect:admin_list?parentId=" + parentId;
    }

    @RequestMapping("/admin_edit")
    public String edit(Model model, Integer cid) {
        if (cid < 0 || categoryService.get(cid) == null) {
            return "error/500";
        }

        Category category = categoryService.get(cid);
        model.addAttribute("bean", category);
        return "admin/editCategory";
    }

    /**
     * 添加二级分类，需要刷新redis缓存
     */
    @RequestMapping("/admin_add")
    public String add(@RequestParam("cname") String cname,@RequestParam("parentId") Integer parentId)  {
        if(categoryService.get(parentId) == null
                ||StringUtils.isBlank(cname) ||categoryService.findByName(cname)!=null){
            return "error/500";
        }

        Category bean = new Category();
        bean.setCname(cname);
        //设置一级分类
        Category parentCategory = categoryService.get(parentId);
        bean.setParentCategory(parentCategory);
        bean.setCategoryLevel(MallConstant.CATEGORY_LEVEL_TWO);

        categoryService.add(bean);
        updateCache(bean.getParentCategory().getCid());

        return "redirect:admin_list?parentId="+parentId;
    }

    //更新二级分类
    @RequestMapping("/admin_update")
    @ResponseBody
    public ResultInfo update(@RequestParam("cid") Integer cid,@RequestParam("cname") String cname) {
        ResultInfo info = new ResultInfo();
        Category category = null;
        if ((category =  categoryService.get(cid)) == null || StringUtils.isBlank(cname)) {
            info.setFlag(false);
            return info;
        }

        category.setCname(cname);
        categoryService.update(category);
        updateCache(category.getParentCategory().getCid());
        info.setFlag(true);
        return info;
    }
}
