package indi.xu.dao;

import indi.xu.domain.Carousel;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * 轮播图
 * 字段解释：is_deleted = 0   代表未删除
 *          is_deleted = 1   代表删除
 *
 *          is_push = 0 未推送
 *          is_push = 1 已推送
 * @author a_apple
 * @create 2020-05-07 12:31
 */
@Repository
public interface CarouselDao {

    @Select("select * from carousel where carousel_id = #{carouselId} and is_deleted = 0")
    @Results(id = "carouselMap", value = {
            @Result(id = true, column = "carousel_id", property = "carouselId"),
            @Result(column = "carousel_url", property = "carouselUrl"),
            @Result(column = "create_time",property = "createTime",javaType = Date.class),
            @Result(column = "create_user",property = "createUser",one = @One(select = "indi.xu.dao.AdminUserDao.get",fetchType = FetchType.EAGER)),
            @Result(column = "is_deleted",property = "isDeleted",javaType = Byte.class),
            @Result(column = "is_push",property = "isPush",javaType = Byte.class),
    })
    Carousel get(int carouselId);

    @Insert("insert into carousel(carousel_url,create_time,create_user) values(#{carouselUrl},#{createTime},#{createUser.id})")
    @Options(useGeneratedKeys = true, keyProperty = "carouselId")
    void add(Carousel c);

    @Update("update carousel set is_deleted = 1 where carousel_id = #{carouselId}")
    void delete(int carouselId);

    @Update("update carousel set carousel_url = #{carouselUrl},is_push = #{isPush} where carousel_id = #{carouselId}")
    void update(Carousel c);

    @Select("select * from carousel where is_deleted = 0 order by carousel_id desc")
    @ResultMap("carouselMap")
    List<Carousel> list();

    /**
     * 给首页用的
     */
    @Select("select * from carousel where is_push = 1 and is_deleted = 0")
    @ResultMap("carouselMap")
    List<Carousel> findPushed();
}
