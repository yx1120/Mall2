package indi.xu.web.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import indi.xu.domain.*;
import indi.xu.service.*;
import indi.xu.utils.PageUtil;
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
 * 后台：商品管理控制器
 *
 * @author a_apple
 * @create 2020-02-28 20:03
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    @Resource
    private ProductService productService;
    @Resource
    private CategoryService categoryService;
    @Resource
    private PropertyValueService propertyValueService;
    @Resource
    private ProductImageService productImageService;
    @Resource
    private PropertyService propertyService;

    @RequestMapping("/admin_list")
    public String list(Integer cid, Model model, Integer row, Integer currentPage) {

        if (cid < 0 || categoryService.get(cid) == null) {
            return "error/500";
        }

        PageVo pv = new PageVo(row, currentPage);
        PageUtil.checkAdminPv(pv);
        //分页插件使用,第几页，需要几条
        PageHelper.startPage(pv.getCurrentPage(), pv.getRow());

        List<Product> list = productService.list(cid);
        PageInfo pageInfo = new PageInfo<>(list);

        //上级分类名称
        Category category = categoryService.get(cid);

        model.addAttribute("page", pageInfo);
        model.addAttribute("params", "&cid=" + cid);
        model.addAttribute("list", list);
        model.addAttribute("category", category);
        return "admin/listProduct";
    }

    @RequestMapping("admin_delete")
    public String delete(Integer pid) {

        if (pid < 0 || productService.get(pid) == null) {
            return "error/500";
        }

        Integer cid = productService.get(pid).getCategory().getCid();

        //事务，因为有外键关联，所以删除一个商品先删除商品的所有图片
        List<ProductImage> singleList = productImageService.list(pid, ProductImageService.TYPE_SINGLE);
        List<ProductImage> detailsList = productImageService.list(pid, ProductImageService.TYPE_DETAIL);
        for (ProductImage image : detailsList) {
            productImageService.delete(image.getGid());
        }
        for (ProductImage image2 : singleList) {
            productImageService.delete(image2.getGid());
        }

        productService.delete(pid);
        return "redirect:admin_list?cid=" + cid;
    }

    @RequestMapping("admin_edit")
    public String edit(Integer pid, Model model) {

        if (pid < 0 || productService.get(pid) == null) {
            return "error/500";
        }
        Product product = productService.get(pid);
        model.addAttribute("bean", product);
        return "admin/editProduct";
    }

    @RequestMapping("admin_add")
    public String add(Product product, @RequestParam(name = "cid") Integer cid) {

        if (product == null
                || StringUtils.isBlank(product.getPname())
                || cid < 0
                || categoryService.get(cid) == null) {
            return "error/500";
        }

        Category category = categoryService.get(cid);
        product.setCategory(category);

        productService.add(product);
        return "redirect:admin_list?cid=" + cid;
    }

    @RequestMapping("admin_update")
    public String update(Product product, @RequestParam(name = "cid") Integer cid) {

        if (product == null
                || StringUtils.isBlank(product.getPname())
                || cid < 0
                || categoryService.get(cid) == null) {
            return "error/500";
        }

        Category category = categoryService.get(cid);
        product.setCategory(category);

        productService.update(product);
        return "redirect:admin_list?cid=" + cid;
    }

    @RequestMapping("admin_editPropertyValue")
    public String editPropertyValue(Integer pid, Model model) {

        if (pid < 0 || productService.get(pid) == null) {
            return "error/500";
        }
        List<PropertyValue> list = propertyValueService.list(pid);
        Product product = productService.get(pid);

        //对于新添加的商品
        if (list.isEmpty()) {
            //初始化商品的属性值
            List<Property> pros = propertyService.list(product.getCategory().getCid());
            for (Property pro : pros) {
                PropertyValue pv = new PropertyValue();
                pv.setProduct(product);
                pv.setProperty(pro);
                pv.setValue(null);

                propertyValueService.add(pv);
                list.add(pv);
            }
        }

        model.addAttribute("product", product);
        model.addAttribute("list", list);
        return "admin/editPropertyValue";
    }

    @RequestMapping("admin_updatePropertyValue")
    @ResponseBody
    public String updatePropertyValue(PropertyValue bean) {
        if (bean == null || StringUtils.isBlank(bean.getValue()) || bean.getVid() < 0) {
            return "error/500";
        }
        propertyValueService.update(bean);
        return "update_OK";
    }
}
