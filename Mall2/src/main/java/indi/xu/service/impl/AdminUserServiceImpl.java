package indi.xu.service.impl;

import indi.xu.dao.AdminUserDao;
import indi.xu.domain.AdminUser;
import indi.xu.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author a_apple
 * @create 2020-03-14 13:54
 */
@Service("adminUserService")
public class AdminUserServiceImpl implements AdminUserService {

    @Autowired
    private AdminUserDao adminUserDao;

    @Override
    public AdminUser findAdminUser(AdminUser user) {
        return adminUserDao.findAdminUser(user);
    }
}
