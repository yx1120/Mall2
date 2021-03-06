package indi.xu.service.impl;

import indi.xu.dao.*;
import indi.xu.domain.Category;
import indi.xu.domain.Product;
import indi.xu.domain.ProductImage;
import indi.xu.service.ProductImageService;
import indi.xu.service.ProductService;
import indi.xu.service.PropertyValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author a_apple
 * @create 2019-12-03 16:25
 */
@Service("productService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;
    @Autowired
    private ProductImageDao productImageDao;
    @Autowired
    private OrderItemDao orderItemDao;
    @Autowired
    private ReviewDao reviewDao;
    @Autowired
    private ProductImageService productImageService;
    @Autowired
    private PropertyValueDao propertyValueDao;
    @Autowired
    private PropertyValueService propertyValueService;


    @Override
    public void add(Product bean) {
        productDao.add(bean);
        propertyValueService.init(bean);
    }

    @Override
    public void deleteByPid(int pid) {
        // 级联，因为有外键关联，所以删除一个商品先删除商品的所有图片
        // 还有商品的属性值
        List<ProductImage> singleList = productImageDao.list(pid, ProductImageService.TYPE_SINGLE);
        List<ProductImage> detailsList = productImageDao.list(pid, ProductImageService.TYPE_DETAIL);
        if(singleList!=null && detailsList!=null){
            singleList.forEach(s->productImageDao.delete(s.getGid()));
            detailsList.forEach(s->productImageDao.delete(s.getGid()));
        }
        // 删除属性值
        propertyValueDao.deleteByPid(pid);

        productDao.delete(pid);
    }

    @Override
    public void update(Product bean) {
        productDao.update(bean);
    }

    @Override
    public Product get(int pid) {

        Product product = productDao.get(pid);
        if(product == null){
            return null;
        }

        this.setFirstProductImage(product);
        this.setSaleAndReviewNumber(product);
        //设置产品图片
        List<ProductImage> detailImgs = productImageService.list(pid, ProductImageService.TYPE_DETAIL);
        List<ProductImage> singleImgs = productImageService.list(pid, ProductImageService.TYPE_SINGLE);
        product.setProductSingleImages(singleImgs);
        product.setProductDetailImages(detailImgs);

        return product;
    }

    @Override
    public List<Product> list(int cid) {
        List<Product> list = productDao.list(cid);
        this.setFirstProductImage(list);
        this.setSaleAndReviewNumber(list);
        return list;
    }

    @Override
    public List<Product> findByKeyword(String keyword) {
        List<Product> list = productDao.findByKeyword("%" + keyword + "%");
        this.setFirstProductImage(list);
        this.setSaleAndReviewNumber(list);
        return list;
    }

    /**
     * 填充一个分类的所有商品
     *
     * @param c
     */
    @Override
    public void fillCategory(Category c) {
        this.fillCategory(c, Short.MAX_VALUE);
    }

    @Override
    public void fillCategory(List<Category> cs) {
        for (Category c : cs) {
            this.fillCategory(c);
        }
    }

    /**
     * 为一个分类填充指定个数商品
     *
     * @param c
     * @param size
     */
    @Override
    public void fillCategory(Category c, int size) {
        List<Product> products = productDao.list(c.getCid());
        size = Math.min(products.size(), size);
        products = products.subList(0, size);
        this.setFirstProductImage(products);
        this.setSaleAndReviewNumber(products);
        c.setProducts(products);
    }

    @Override
    public void fillCategory(List<Category> cs, int size) {
        cs.forEach(category -> this.fillCategory(category,size));
    }

    @Override
    public void setFirstProductImage(Product p) {
        List<ProductImage> list = productImageDao.list(p.getPid(), ProductImageService.TYPE_SINGLE);
        if (!list.isEmpty()) {
            p.setFirstProductImage(list.get(0));
        }
    }

    @Override
    public void setFirstProductImage(List<Product> products) {
        products.forEach(this::setFirstProductImage);
    }

    @Override
    public void setSaleAndReviewNumber(List<Product> products) {
        products.forEach(this::setSaleAndReviewNumber);
    }

    @Override
    public void setSaleAndReviewNumber(Product p) {
        Integer saleCount = orderItemDao.getSaleCount(p.getPid());
        if (saleCount == null) {
            saleCount = 0;
        }
        p.setSaleCount(saleCount);

        int reviewCount = reviewDao.findByPid(p.getPid()).size();
        p.setReviewCount(reviewCount);
    }
}
