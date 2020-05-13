package indi.xu.service;

import indi.xu.domain.Mood;

import java.util.List;

/**
 * @author a_apple
 * @create 2020-02-28 20:06
 */
public interface MoodService {

    void add(Mood mood);

    void delete(int mid);

    List<Mood> list();

    List<Mood> listByUid(int uid);
}
