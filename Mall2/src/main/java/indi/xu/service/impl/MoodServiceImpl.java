package indi.xu.service.impl;

import indi.xu.dao.MoodDao;
import indi.xu.domain.Mood;
import indi.xu.service.MoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author a_apple
 * @create 2020-02-28 20:07
 */

@Service("moodService")
public class MoodServiceImpl implements MoodService {

    @Autowired
    private MoodDao moodDao;

    @Override
    public void add(Mood mood) {
        moodDao.add(mood);
    }

    @Override
    public void delete(int mid) {
        moodDao.delete(mid);
    }

    @Override
    public List<Mood> list() {
        return moodDao.list();
    }

    @Override
    public List<Mood> listByUid(int uid) {
        return moodDao.listByUid(uid);
    }
}
