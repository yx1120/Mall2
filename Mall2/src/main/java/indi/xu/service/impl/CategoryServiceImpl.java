package indi.xu.service.impl;

import indi.xu.dao.*;
import indi.xu.domain.Category;
import indi.xu.domain.Product;
import indi.xu.domain.Property;
import indi.xu.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author a_apple
 * @create 2019-12-03 16:24
 */

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;
    @Autowired
    private PropertyDao propertyDao;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private PropertyValueDao propertyValueDao;
    @Autowired
    private ProductImageDao productImageDao;

    @Override
    public void add(Category bean) {
        categoryDao.add(bean);
    }

    @Override
    public void delete(int cid) {
        //级联
        //List<Property> properties = propertyDao.list(cid);
        //List<Product> products = productDao.list(cid);
        //
        //
        //
        //for (Product product : products) {
        //
        //    //1.   删除一个商品的属性值
        //    propertyValueDao.delete(product.getPid());
        //
        //    //2.  删除这个商品的图片
        //    productImageDao.deleteByPid(product.getPid());
        //
        //    productDao.delete(product.getPid());
        //}
        //
        //for (Property property : properties) {
        //    propertyDao.delete(property.getPyid());
        //}

        //最后删除分类
        categoryDao.delete(cid);
    }

    @Override
    public void update(Category bean) {
        categoryDao.update(bean);
    }

    @Override
    public Category get(int cid) {
        return categoryDao.get(cid);
    }

    @Override
    public List<Category> findByLevel(int level) {
        return categoryDao.findByLevel(level);
    }

    @Override
    public List<Category> findByParentId(int parentId) {
        return categoryDao.findByParentId(parentId);
    }

    @Override
    public Category findByName(String cname) {
        return categoryDao.findByName(cname);
    }

    @Override
    public List<Category> findByFidAndSize(int parentId, int size) {
        return categoryDao.findByFidAndSize(parentId, size);
    }
}
