package indi.xu.dao;


import indi.xu.domain.Review;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import javax.swing.plaf.SliderUI;
import java.util.List;

/**
 * 评价表---用户+商品+订单id
 *
 * @author a_apple
 * @create 2019-11-23 16:30
 */
@Repository
public interface ReviewDao {
    /**
     * 添加一条评价记录
     */
    @Insert("insert into review(content,uid,pid,createDate,oid) values( #{content} , #{user.uid} , #{product.pid} , #{createDate} , #{order.oid} )")
    @Options(useGeneratedKeys = true, keyProperty = "rid")
    void add(Review bean);

    @Select("select * from review where rid = #{rid}")
    @Results(id = "reviewMap", value = {
            @Result(id = true, column = "rid", property = "rid"),
            @Result(column = "content", property = "content"),
            @Result(column = "uid", property = "user", one = @One(select = "indi.xu.dao.UserDao.get", fetchType = FetchType.EAGER)),
            @Result(column = "pid", property = "product", one = @One(select = "indi.xu.dao.ProductDao.get", fetchType = FetchType.EAGER)),
            @Result(column = "createDate", property = "createDate"),
            @Result(column = "oid", property = "order", one = @One(select = "indi.xu.dao.OrderDao.get", fetchType = FetchType.EAGER))
    })
    Review get(int rid);

    /**
     * 查询某一用户的所有评价
     *
     * @param uid
     * @return
     */
    @Select("select * from review where uid = #{uid}")
    @ResultMap("reviewMap")
    List<Review> findByUid(int uid);

    /**
     * 查询某一商品下面的所有评价,分页
     * 指定商品有多少条评价
     *
     * @param pid
     * @return
     */
    @Select("select * from review where pid = #{pid}")
    @ResultMap("reviewMap")
    List<Review> findByPid(int pid);

}
