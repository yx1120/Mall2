package indi.xu.service;


import indi.xu.domain.Category;
import indi.xu.domain.Product;

import java.util.List;
import java.util.SimpleTimeZone;

/**
 * @author a_apple
 * @create 2019-12-03 16:25
 */
public interface ProductService {

    void add(Product bean);

    void delete(int pid);

    void update(Product bean);

    Product get(int pid);

    List<Product> list(int cid);

    List<Product> findByKeyword(String keyword);

    /**
     * 为分类填充产品
     */
    void fillCategory(Category c);

    void fillCategory(List<Category> cs);

    /**
     * 为分类填充指定个数商品
     *
     * @param c
     * @param size
     */
    void fillCategory(Category c, int size);

    void fillCategory(List<Category> cs, int size);

    /**
     * 一个产品有多个图片，但是只有一个主图片，把第一个图片设置为主图片
     */
    void setFirstProductImage(Product p);

    void setFirstProductImage(List<Product> products);

    //为产品设置销售和评价数量
    void setSaleAndReviewNumber(List<Product> products);

    void setSaleAndReviewNumber(Product p);
}
