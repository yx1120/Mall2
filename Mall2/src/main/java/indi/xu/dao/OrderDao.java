package indi.xu.dao;


import indi.xu.domain.Order;
import indi.xu.domain.OrderItem;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

/**
 * 订单表
 *
 * @author a_apple
 * @create 2019-11-23 16:28
 */
@Repository
public interface OrderDao {

    @Insert("insert into tab_order values(null," +
            "#{orderCode}," +
            "#{address}," +
            "#{post}," +
            "#{receiver}," +
            "#{mobile}," +
            "#{userMessage}," +
            "#{createDate}," +
            "#{payDate}," +
            "#{deliveryDate}," +
            "#{confirmDate}," +
            "#{user.uid}," +
            "#{status})")
    @Options(useGeneratedKeys = true, keyProperty = "oid")
    void add(Order order);

    /**
     * 订单一旦生成，更新的只是状态
     *
     * @param bean
     */
    @Update("update tab_order set status = #{status}," +
            "payDate = #{payDate}," +
            "deliveryDate=#{deliveryDate}," +
            "confirmDate=#{confirmDate} " +
            "where oid = #{oid}")
    void update(Order bean);

    @Select("select * from tab_order where oid = #{oid}")
    @Results(id = "orderMap", value = {
            @Result(id = true, column = "oid", property = "oid"),
            @Result(column = "orderCode", property = "orderCode"),
            @Result(column = "address", property = "address"),
            @Result(column = "post", property = "post"),
            @Result(column = "receiver", property = "receiver"),
            @Result(column = "mobile", property = "mobile"),
            @Result(column = "userMessage", property = "userMessage"),
            @Result(column = "createDate", property = "createDate", javaType = Timestamp.class),
            @Result(column = "payDate", property = "payDate", javaType = Timestamp.class),
            @Result(column = "deliveryDate", property = "deliveryDate", javaType = Timestamp.class),
            @Result(column = "confirmDate", property = "confirmDate", javaType = Timestamp.class),
            @Result(column = "uid", property = "user", one = @One(select = "indi.xu.dao.UserDao.get", fetchType = FetchType.EAGER)),
            @Result(column = "status", property = "status")
    })
    Order get(int oid);

    @Delete("delete from tab_order where oid = #{oid}")
    void delete(int oid);

    /**
     * 所有状态订单
     *
     * @return
     */
    @Select("select * from tab_order")
    @ResultMap("orderMap")
    List<Order> list();

    /**
     * 某个状态订单
     * 条件查询
     * <p>
     * 当有多个参数，使用@Param注解标识一下
     *
     * @param uid
     * @param status
     * @return
     */
    @ResultMap("orderMap")
    @Select("select * from tab_order where uid = #{uid} and status = #{status} order by oid desc")
    List<Order> findByStatus(@Param("uid") int uid, @Param("status") String status);

    @Select("select * from tab_order where uid = #{uid} and status != 'delete'")
    @ResultMap("orderMap")
    List<Order> findByUid(int uid);

}
