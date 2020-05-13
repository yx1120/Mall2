package indi.xu.web.controller.mall;

import indi.xu.domain.*;
import indi.xu.service.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Comparator;
import java.util.List;

/**
 * @author a_apple
 * @create 2020-04-17 10:58
 */
@RequestMapping("/fore")
@Controller
public class MallProductController {

    @Resource
    private CategoryService categoryService;
    @Resource
    private ProductService productService;
    @Resource
    private ReviewService reviewService;
    @Resource
    private PropertyValueService propertyValueService;
    @Resource
    private FavoriteService favoriteService;

    /**
     * 直接点击一级分类进行查询
     */
    @RequestMapping("/firstLevelProducts/{parentId}")
    public String findProductsByFid(@PathVariable Integer parentId, Model model) {

        if (parentId < 0 || categoryService.get(parentId) == null) {
            return "error/500";
        }

        //1.取一级分类中5个二级分类
        List<Category> categories = categoryService.findByFidAndSize(parentId, 5);
        //2.填充每个二级分类5个商品
        productService.fillCategory(categories, 5);

        //3.判断，如果某个二级分类没有商品。就不要这个分类显示
        categories.removeIf(c -> c.getProducts().size() == 0);

        model.addAttribute("list", categories);
        model.addAttribute("parentId", parentId);

        return "fore/product/product_first";
    }

    /**
     * 查询某个分类的产品
     */
    @RequestMapping("/products")
    public String findProducts(@RequestParam(name = "cid") Integer cid,
                               @RequestParam(name = "sort", required = false) String sortStr,
                               Model model) {

        Category category = null;
        if (cid == null || cid <= 0 || (category = categoryService.get(cid)) == null) {
            //填充商品
            model.addAttribute("category", null);
            return "fore/category/category_products";
        }

        productService.fillCategory(category);
        List<Product> products = category.getProducts();
        //获取排序类型参数，对商品进行排序
        if (null == sortStr) {
            sortStr = "all";
        }
        switch (sortStr) {
            case "price":
                products.sort((x, y) -> (int) (x.getPromotePrice() - y.getPromotePrice()));
                break;
            case "review":
                products.sort((x, y) -> y.getReviewCount() - x.getReviewCount());
                break;
            case "date":
                products.sort(Comparator.comparing(Product::getCreateDate));
                break;
            case "saleCount":
                products.sort((x, y) -> y.getSaleCount() - x.getSaleCount());
                break;
            default: //"all"   如何输入其他字符也是这个
                products.sort((x, y) -> y.getSaleCount() - x.getSaleCount());
                break;
        }
        model.addAttribute("category", category);
        return "fore/category/category_products";
    }

    /**
     * 搜索商品
     */
    @RequestMapping("/searchProduct")
    public String searchProduct(String keyword, Model model) {

        if (StringUtils.isBlank(keyword)) {
            return "fore/product/product_search";
        }

        List<Product> products = productService.findByKeyword(keyword);
        model.addAttribute("products", products);
        return "fore/product/product_search";
    }

    /**
     * 某个商品详情
     */
    @RequestMapping("/product/{pid}")
    public String product(@PathVariable Integer pid, Model model, HttpSession session) {

        Product product = productService.get(pid);
        if (product == null) {
            return "error/404";
        }

        //用户未登录-->跳转登录
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "fore/login";
        }
        //判断是否收藏
        boolean isFavorite = false;
        Favorite favorite = favoriteService.get(pid, user.getUid());
        if (favorite != null) {
            isFavorite = true;
        }

        List<PropertyValue> pvList = propertyValueService.list(pid);
        List<Review> reviewList = reviewService.findByPid(pid);

        model.addAttribute("isFavorite", isFavorite);
        model.addAttribute("product", product);
        model.addAttribute("pvList", pvList);
        model.addAttribute("reviewList", reviewList);
        return "fore/product/product";
    }
}
