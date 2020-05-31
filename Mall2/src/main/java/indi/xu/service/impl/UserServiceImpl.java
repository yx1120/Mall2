package indi.xu.service.impl;

import indi.xu.dao.UserDao;
import indi.xu.domain.User;
import indi.xu.service.UserService;
import indi.xu.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author a_apple
 * @create 2019-12-03 16:27
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User get(Integer uid) {
        //把日期的输出格式化再封装
        return userDao.get(uid);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Override
    public User findRepeatTelNumber(String telNum) {
        return userDao.findRepeatTelNumber(telNum);
    }

    @Override
    public User findUser(String telNum,String password) {
        User user = new User();
        user.setPassword(MD5Util.MD5Encode(password, "UTF-8"));
        user.setTelNum(telNum);
        return userDao.findUser(user);
    }

    @Override
    public List<User> list() {
        return userDao.list();
    }

}
