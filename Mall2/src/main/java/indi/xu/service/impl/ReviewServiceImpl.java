package indi.xu.service.impl;

import indi.xu.dao.ReviewDao;
import indi.xu.domain.Review;
import indi.xu.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author a_apple
 * @create 2019-12-03 16:27
 */
@Service("reviewService")
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewDao reviewDao;

    @Override
    public void add(Review bean) {
        reviewDao.add(bean);
    }

    @Override
    public Review get(int rid) {
        return reviewDao.get(rid);
    }

    @Override
    public List<Review> findByUid(int uid) {
        return reviewDao.findByUid(uid);
    }

    @Override
    public List<Review> findByPid(int pid) {
        return reviewDao.findByPid(pid);
    }
}
