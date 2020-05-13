package indi.xu.dao;


import indi.xu.domain.Property;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 属性名表---分类1,多
 *
 * @author a_apple
 * @create 2019-11-23 16:29
 */
@Repository
public interface PropertyDao {

    /**
     * 给某个分类添加属性
     */
    @Insert("insert into Property values(null,#{category.cid},#{pyname})")
    @Options(useGeneratedKeys = true, keyProperty = "pyid")
    void add(Property bean);

    /**
     * 删除某个分类，某个属性名
     */
    @Delete("delete from property where pyid = #{pyid}")
    void delete(int pyid);

    /**
     * 修改某个分类，某个属性名
     */
    @Update("update property set pyname = #{pyname} where pyid = #{pyid}")
    void update(Property bean);

    @Select("select * from property where pyid = #{pyid}")
    @Results(id = "propertyMap", value = {
            @Result(id = true, column = "pyid", property = "pyid"),
            @Result(column = "cid", property = "category", one = @One(select = "indi.xu.dao.CategoryDao.get", fetchType = FetchType.EAGER)),
            @Result(column = "pyname", property = "pyname")
    })
    Property get(int pyid);

    /**
     * 分页
     * 查询某个分类下的的所有属性对象
     * <p>
     * 查询某个分类有多少个属性
     *
     * @param cid
     * @return
     */
    @Select("select * from property where cid = #{cid}")
    @ResultMap("propertyMap")
    List<Property> list(int cid);

}
