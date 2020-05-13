package indi.xu.dao;


import indi.xu.domain.Favorite;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

/**
 * 收藏表
 *
 * @author a_apple
 * @create 2019-11-23 16:28
 */
@Repository
public interface FavoriteDao {

    /**
     * 根据用户id查询收藏记录
     * findUserFavorite
     *
     * @param uid
     * @return
     */
    @Select("select * from tab_favorite where uid = #{uid}")
    @Results(id = "favoriteMap", value = {
            @Result(id = true, column = "pid", property = "product", one = @One(select = "indi.xu.dao.ProductDao.get", fetchType = FetchType.EAGER)),
            @Result(id = true, column = "uid", property = "user", one = @One(select = "indi.xu.dao.UserDao.get", fetchType = FetchType.EAGER)),
            @Result(column = "fdate", property = "fdate")
    })
    List<Favorite> list(int uid);

    /**
     * 用户收藏商品
     * addFavorite
     *
     * @param bean
     */
    @Insert("insert into tab_favorite values( #{product.pid} , #{user.uid}, #{fdate})")
    void add(Favorite bean);


    /**
     * 用户取消收藏，或删除记录
     * deleteFavorite
     *
     * @param pid
     * @param uid
     */
    @Delete("delete from tab_favorite where pid = #{pid} and uid = #{uid}")
    void delete(@Param("pid") int pid, @Param("uid") int uid);

    @Select("select * from tab_favorite where pid = #{pid} and uid = #{uid} ")
    Favorite get(@Param("pid") int pid, @Param("uid") int uid);

}
