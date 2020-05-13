package indi.xu.service;


import indi.xu.domain.Favorite;

import java.util.List;

/**
 * @author a_apple
 * @create 2019-12-03 16:24
 */
public interface FavoriteService {

    void add(Favorite bean);

    void delete(int pid, int uid);

    List<Favorite> list(int uid);

    Favorite get(int pid, int uid);
}
