package indi.xu.dao;


import indi.xu.domain.Category;
import indi.xu.domain.Product;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author a_apple
 * @create 2019-11-23 16:29
 */

@Repository
public interface ProductDao {

    @Insert("insert into product values(null, #{pname} , #{subTitle} , #{orignalPrice}, #{promotePrice}, #{stock} , #{category.cid} , #{createDate})")
    @Options(useGeneratedKeys = true, keyProperty = "pid")
    void add(Product bean);

    @Delete("delete from product where pid = #{pid} ")
    void delete(int pid);

    @Update("update product set pname = #{pname} ," +
            " subTitle=#{subTitle}, " +
            " orignalPrice=#{orignalPrice}," +
            " promotePrice=#{promotePrice}," +
            " stock=#{stock}," +
            " cid = #{category.cid}," +
            " createDate=#{createDate} where pid = #{pid}")
    void update(Product bean);

    @Select("select * from product where pid = #{pid} ")
    @Results(id = "productMap", value = {
            @Result(id = true, column = "pid", property = "pid"),
            @Result(column = "pname", property = "pname"),
            @Result(column = "subTitle", property = "subTitle"),
            @Result(column = "orignalPrice", property = "orignalPrice"),
            @Result(column = "promotePrice", property = "promotePrice"),
            @Result(column = "stock", property = "stock"),
            @Result(column = "cid", property = "category", one = @One(select = "indi.xu.dao.CategoryDao.get", fetchType = FetchType.EAGER))
    })
    Product get(@Param("pid") int pid);

    /**
     * 分页：
     * 查询某个分类下面的所有产品
     * 查询某个分类下面的商品数量
     *
     * @param cid
     * @return
     */
    @Select("select * from product where cid = #{cid}")
    //@ResultMap("productMap")
    List<Product> list(@Param("cid") int cid);

    /**
     * 分页：根据关键字查询产品
     */
    @Select("select * from Product where pname like #{keyword}")
    @ResultMap("productMap")
    List<Product> findByKeyword(String keyword);

}
