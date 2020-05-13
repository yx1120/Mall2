package indi.xu.web.controller.admin;

import indi.xu.common.MallConstant;
import indi.xu.domain.Category;
import indi.xu.domain.Product;
import indi.xu.domain.ProductImage;
import indi.xu.service.ProductImageService;
import indi.xu.service.ProductService;
import indi.xu.utils.ImageUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 商品图片管理
 *
 * @author a_apple
 * @create 2020-03-02 19:06
 */
@Controller
@RequestMapping("/productImage")
public class ProductImageController {

    @Resource
    private ProductImageService productImageService;
    @Resource
    private ProductService productService;

    @RequestMapping("/admin_list")
    public String list(Integer pid, Model model) {
        if (pid < 0 || productService.get(pid) == null) {
            return "error/500";
        }
        Product product = productService.get(pid);
        List<ProductImage> singleImgs = productImageService.list(pid, ProductImageService.TYPE_SINGLE);
        List<ProductImage> detailImgs = productImageService.list(pid, ProductImageService.TYPE_DETAIL);

        model.addAttribute("singleList", singleImgs);
        model.addAttribute("detailList", detailImgs);
        model.addAttribute("product", product);

        return "admin/listProductImage";
    }

    @RequestMapping("/admin_delete")
    public String delete(@RequestParam(name = "gid") Integer gid,
                         @RequestParam(name = "pid") Integer pid) {

        if (gid < 0 || productImageService.get(gid) == null
                || pid < 0 || productService.get(pid) == null) {
            return "error/500";
        }
        productImageService.delete(gid);
        return "redirect:admin_list?pid=" + pid;
    }

    //上传图片
    @RequestMapping("/admin_addProductImg")
    public String addSingleImg(HttpServletRequest request,
                               MultipartFile uploadImg,
                               @RequestParam(name = "pid") Integer pid,
                               @RequestParam(name = "type") String type) throws IOException {

        if (pid < 0 || productService.get(pid) == null || StringUtils.isBlank(type)) {
            return "error/500";
        }

        String path = null;
        String pathSmall = null;
        String pathMiddle = null;

        //判断上传图片类型  single
        if (type.equals(ProductImageService.TYPE_SINGLE)) {
            //获取上传位置
            path = request.getSession().getServletContext().getRealPath("img/productSingle");
            pathSmall = request.getSession().getServletContext().getRealPath("img/productSingle_small");
            pathMiddle = request.getSession().getServletContext().getRealPath("img/productSingle_middle");
        } else {
            path = request.getSession().getServletContext().getRealPath("img/productDetail");
        }

        //判断文件夹是否存在
        File file = new File(path);
        if (!file.exists()) {
            //如果父目录不存在，会把父目录也创建
            file.mkdirs();
        }

        //先插入数据库，获取gid
        ProductImage image = new ProductImage();
        image.setProduct(productService.get(pid));
        image.setType(type);
        productImageService.add(image);

        //创建文件，如果是single类型，还要弄2份大小的图片，复制到_small和_middle文件里面
        String fileName = image.getGid() + ".jpg";
        File singleImg = new File(path, fileName);

        uploadImg.transferTo(singleImg);
        BufferedImage img = ImageUtil.change2jpg(singleImg);
        ImageIO.write(img, "jpg", singleImg);
        //需要先上传文件，才能读取，不然报E:Can't read input file!
        if (type.equals(ProductImageService.TYPE_SINGLE)) {
            File singleImg_small = new File(pathSmall, fileName);
            File singleImg_middle = new File(pathMiddle, fileName);

            ImageUtil.resizeImage(singleImg, MallConstant.SINGLE_IMG_SMALL_WIDTH, MallConstant.SINGLE_IMG_SMALL_HEIGHT, singleImg_small);
            ImageUtil.resizeImage(singleImg, MallConstant.SINGLE_IMG_MIDDLE_WIDTH, MallConstant.SINGLE_IMG_MIDDLE_HEIGHT, singleImg_middle);
        }

        return "redirect:admin_list?pid=" + pid;
    }


}
