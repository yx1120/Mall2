package indi.xu.service;

import indi.xu.domain.Carousel;

import java.util.List;

/**
 * @author a_apple
 * @create 2020-05-07 13:14
 */
public interface CarouselService {

    Carousel get(int carouselId);

    void add(Carousel c);

    void delete(int carouselId);

    void update(Carousel c);

    List<Carousel> list();

    List<Carousel> findPushed();
}
