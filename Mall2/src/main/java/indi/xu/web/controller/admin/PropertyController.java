package indi.xu.web.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import indi.xu.domain.Category;
import indi.xu.domain.Property;
import indi.xu.service.CategoryService;
import indi.xu.service.PropertyService;
import indi.xu.utils.PageUtil;
import indi.xu.web.vo.PageVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

/**
 * 属性管理
 *
 * @author a_apple
 * @create 2020-02-28 20:03
 */

@Controller
@RequestMapping("/admin")
public class PropertyController {

    @Resource
    private PropertyService propertyService;
    @Resource
    private CategoryService categoryService;

    @RequestMapping("property/list")
    public String list(Integer cid, Model model,
                       Integer row, Integer currentPage) {

        if (cid < 0 || categoryService.get(cid) == null) {
            return "error/500";
        }

        PageVo pv = new PageVo(row, currentPage);
        PageUtil.checkAdminPv(pv);
        //分页插件使用,第几页，需要几条
        PageHelper.startPage(pv.getCurrentPage(), pv.getRow());
        List<Property> list = propertyService.list(cid);

        //上级分类名称
        Category category = categoryService.get(cid);

        PageInfo pageInfo = new PageInfo<>(list);

        model.addAttribute("page", pageInfo);
        model.addAttribute("list", list);
        model.addAttribute("category", category);
        model.addAttribute("params", "&cid=" + cid);

        return "admin/listProperty";
    }

    @RequestMapping("property/edit")
    public String edit(Integer pyid, Model model) {

        if (pyid < 0 || propertyService.get(pyid) == null) {
            return "error/500";
        }

        Property property = propertyService.get(pyid);
        model.addAttribute("bean", property);

        return "admin/editProperty";
    }

    @RequestMapping("property/delete")
    public String delete(Integer pyid) {

        if (pyid < 0 || propertyService.get(pyid) == null) {
            return "error/500";
        }

        Integer cid = propertyService.get(pyid).getCategory().getCid();
        //先获取cid然后删除
        propertyService.delete(pyid);
        return "redirect:list?cid=" + cid;
    }

    @RequestMapping("property/add")
    public String add(@RequestParam(name = "cid") Integer cid,
                      @RequestParam(name = "pyname") String pyname) {

        if (cid < 0 || categoryService.get(cid) == null || StringUtils.isBlank(pyname)) {
            return "error/500";
        }

        Property property = new Property();
        property.setCategory(categoryService.get(cid));
        property.setPyname(pyname);

        propertyService.add(property);
        return "redirect:list?cid=" + cid;
    }

    @RequestMapping("property/update")
    public String update(String pyname, Integer pyid) {

        if (pyid < 0 || propertyService.get(pyid) == null || StringUtils.isBlank(pyname)) {
            return "error/500";
        }
        Property property = propertyService.get(pyid);
        property.setPyname(pyname);
        propertyService.update(property);

        return "redirect:list?cid=" + property.getCategory().getCid();
    }
}
