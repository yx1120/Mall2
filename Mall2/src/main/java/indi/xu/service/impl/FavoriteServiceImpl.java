package indi.xu.service.impl;

import indi.xu.dao.FavoriteDao;
import indi.xu.domain.Favorite;
import indi.xu.domain.Product;
import indi.xu.service.FavoriteService;
import indi.xu.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author a_apple
 * @create 2019-12-03 16:24
 */
@Service("favoriteService")
public class FavoriteServiceImpl implements FavoriteService {

    @Resource
    private FavoriteDao favoriteDao;
    @Resource
    private ProductService productService;

    @Override
    public void add(Favorite bean) {
        favoriteDao.add(bean);
    }

    @Override
    public void delete(int pid, int uid) {
        favoriteDao.delete(pid, uid);
    }

    @Override
    public List<Favorite> list(int uid) {
        List<Favorite> list = favoriteDao.list(uid);
        if(list==null){
            return null;
        }

        list.forEach(favorite -> {
            Product product = favorite.getProduct();
            productService.setFirstProductImage(product);
        });
        return list;
    }

    @Override
    public Favorite get(int pid, int uid) {
        return favoriteDao.get(pid, uid);
    }
}
