package indi.xu.service.impl;

import indi.xu.dao.ProductImageDao;
import indi.xu.domain.ProductImage;
import indi.xu.service.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author a_apple
 * @create 2019-12-03 16:26
 */
@Service("productImageService")
public class ProductImageServiceImpl implements ProductImageService {

    @Autowired
    private ProductImageDao productImageDao;

    @Override
    public void add(ProductImage bean) {
        productImageDao.add(bean);
    }

    @Override
    public void delete(int gid) {
        productImageDao.delete(gid);
    }

    @Override
    public ProductImage get(int gid) {
        return productImageDao.get(gid);
    }

    @Override
    public List<ProductImage> list(int pid, String type) {
        return productImageDao.list(pid, type);
    }
}
