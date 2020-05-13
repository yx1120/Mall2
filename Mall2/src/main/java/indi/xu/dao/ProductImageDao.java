package indi.xu.dao;


import indi.xu.domain.ProductImage;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author a_apple
 * @create 2019-11-23 16:29
 */
@Repository
public interface ProductImageDao {

    @Insert("insert into productimage values(null,#{product.pid},#{type})")
    @Options(useGeneratedKeys = true, keyProperty = "gid")
    void add(ProductImage bean);

    @Delete("delete from productimage where gid = #{id}")
    void delete(int gid);

    @Select("select * from productimage where gid = #{gid} ")
    ProductImage get(int gid);

    /**
     * 查询商品图片
     * findProductImg
     *
     * @param type 图片类型（单个图片、详情图片）
     * @return
     */
    @Select("select * from productimage where pid = #{pid} and type = #{type} ")
    List<ProductImage> list(@Param("pid") int pid, @Param("type") String type);

    @Delete("delete from productimage where pid = #{pid}")
    void deleteByPid(int pid);

}
