package indi.xu.dao;

import indi.xu.domain.AdminUser;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.SessionAttribute;

/**
 * 管理员Dao
 *
 * @author a_apple
 * @create 2020-03-14 13:52
 */
@Repository
public interface AdminUserDao {

    /**
     * 管理员登录
     *
     */
    @Select("select * from adminuser where name = #{name} and password = #{password}")
    AdminUser findAdminUser(AdminUser user);

    @Select("select id,name from adminuser where id = #{id}")
    AdminUser get(int id);
}
