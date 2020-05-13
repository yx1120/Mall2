package indi.xu.dao;


import indi.xu.domain.AdminUser;
import indi.xu.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author a_apple
 * @create 2019-11-23 16:30
 */
@Repository
public interface UserDao {

    @Select("select * from user where uid = #{uid}")
    User get(Integer uid);

    @Update("update user set " +
            " uname = #{uname}," +
            " realName=#{realName}," +
            " sex=#{sex}," +
            " birthday=#{birthday}," +
            " email=#{email}," +
            " password=#{password}" +
            " where uid = #{uid}")
    void update(User user);

    @Insert("insert into user(Uname,telNum,password,createTime) values (#{uname},#{telNum},#{password},#{createTime})")
    @Options(useGeneratedKeys = true, keyProperty = "uid")
    void add(User user);

    /**
     * 查询所有用户--后台管理
     */
    @Select("select * from user")
    List<User> list();

    /**
     * 查询是否用重复名-ajax-注册
     */
    @Select("select * from user where telNum = #{telNum}")
    User findRepeatTelNumber(String telNum);

    /**
     * 用户登录:手机号+密码
     */
    @Select("select * from user where telNum = #{telNum} and password = #{password}")
    User findUser(User user);

}
