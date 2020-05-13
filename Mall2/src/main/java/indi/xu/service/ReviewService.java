package indi.xu.service;


import indi.xu.domain.Review;

import java.util.List;

/**
 * @author a_apple
 * @create 2019-12-03 16:27
 */
public interface ReviewService {

    void add(Review bean);

    Review get(int rid);

    List<Review> findByUid(int uid);

    List<Review> findByPid(int pid);
}
