package indi.xu.dao;


import indi.xu.domain.Order;
import indi.xu.domain.OrderItem;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * 订单项表=uid+pid
 *
 * @author a_apple
 * @create 2019-11-23 16:28
 */

@Repository
public interface OrderItemDao {

    @Insert("insert into orderitem values(null , #{product.pid} , #{order.oid} , #{user.uid} , #{number},#{status} )")
    @Options(useGeneratedKeys = true, keyProperty = "tid")
    void add(OrderItem bean);

    @Select("select * from orderitem where tid = #{tid}")
    @Results(id = "orderitemMap", value = {
            @Result(id = true, column = "tid", property = "tid"),
            @Result(column = "number", property = "number", javaType = Integer.class),
            @Result(column = "status", property = "status"),
            @Result(column = "pid", property = "product", one = @One(select = "indi.xu.dao.ProductDao.get", fetchType = FetchType.EAGER)),
            @Result(column = "oid", property = "order", one = @One(select = "indi.xu.dao.OrderDao.get", fetchType = FetchType.EAGER)),
            @Result(column = "uid", property = "user", one = @One(select = "indi.xu.dao.UserDao.get", fetchType = FetchType.EAGER))

    })
    OrderItem get(int tid);

    @Delete("delete from orderitem where tid = #{tid} ")
    void delete(int tid);

    @Update("update orderitem set number = #{number} , pid = #{product.pid} , uid = #{user.uid} , oid = #{order.oid},status=#{status} where tid = #{tid}")
    void update(OrderItem bean);

    /**
     * 获取某一种商品的销量--商品展示
     * findSaleCount
     */
    @Select("select sum(orderitem.number) from orderitem where pid = #{pid} and oid != -1")
    Integer getSaleCount(int pid);

    /**
     * 查询某一订单的订单项
     * findOrderDetail
     */
    @Select("select * from orderitem where oid = #{oid}")
    @ResultMap("orderitemMap")
    List<OrderItem> findItemsByOid(int oid);

    /**
     * uid+oid=-1
     * 查询用户的购物车
     */
    @Select("select * from orderitem where uid = #{uid} and oid = -1 order by tid desc")
    @ResultMap("orderitemMap")
    List<OrderItem> findItemsByUid(int uid);

    @Select("select * from orderitem where uid = #{uid} and pid = #{pid}")
    @ResultMap("orderitemMap")
    OrderItem isExist(@Param("uid") int uid, @Param("pid") int pid);

    /**
     * 查询某一用户待评价的商品
     * 注：一次订单=>多个订单项=>多种商品待评价
     */
    @Select("select * from orderitem where uid = #{uid} and oid != -1 order by tid desc")
    @ResultMap("orderitemMap")
    List<OrderItem> findByUidAndOid(int uid);

    /**
     * 根据状态查找订单项
     *
     * @param uid
     * @param status
     * @return
     */
    @Select("select * from orderitem where uid = #{uid} and status = #{status} order by tid desc")
    @ResultMap("orderitemMap")
    List<OrderItem> findByStatus(@Param("uid") int uid, @Param("status") String status);

    /**
     * 查询用户所有评价
     *
     * @param uid
     * @return
     */
    @Select("select * from orderitem where uid = #{uid} and status = 'reviewed' or status = 'waitReview' order by tid desc")
    @ResultMap("orderitemMap")
    List<OrderItem> findUserItemsByUid(Integer uid);
}
