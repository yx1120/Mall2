package indi.xu.service;


import indi.xu.domain.AdminUser;
import indi.xu.domain.User;

import java.util.List;

/**
 * @author a_apple
 * @create 2019-12-03 16:27
 */
public interface UserService {

    User get(Integer uid);

    void update(User user);

    void add(User user);

    List<User> list();

    /**
     * 检测重复名、登录查询
     *
     */
    User findRepeatTelNumber(String telNum);

    User findUser(User user);

}
