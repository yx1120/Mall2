package indi.xu.service.impl;

import indi.xu.dao.AdminUserDao;
import indi.xu.dao.CarouselDao;
import indi.xu.domain.Carousel;
import indi.xu.service.CarouselService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author a_apple
 * @create 2020-05-07 13:15
 */
@Service("carouselService")
public class CarouselServiceImpl implements CarouselService {

    @Resource
    private CarouselDao carouselDao;
    @Resource
    private AdminUserDao adminUserDao;

    @Override
    public Carousel get(int carouselId) {
        return carouselDao.get(carouselId);
    }

    @Override
    public void add(Carousel c) {
        carouselDao.add(c);
    }

    @Override
    public void delete(int carouselId) {
        carouselDao.delete(carouselId);
    }

    @Override
    public void update(Carousel c) {
        carouselDao.update(c);
    }

    @Override
    public List<Carousel> list() {
        List<Carousel> list = carouselDao.list();
        if(list == null || list.isEmpty()){
            return null;
        }
        return list;
    }

    @Override
    public List<Carousel> findPushed() {
        return carouselDao.findPushed();
    }
}
