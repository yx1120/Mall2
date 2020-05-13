package indi.xu.dao;

import indi.xu.domain.Product;
import indi.xu.domain.PropertyValue;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 属性值=属性+产品 决定
 *
 * @author a_apple
 * @create 2019-11-23 16:29
 */

@Repository
public interface PropertyValueDao {

    /**
     * 添加一条记录，某个商品的属性值
     *
     * @param bean
     */
    @Insert("insert into propertyvalue(pid,pyid,value) values( #{product.pid} , #{property.pyid} , #{value} )")
    @Options(useGeneratedKeys = true, keyProperty = "vid")
    void add(PropertyValue bean);

    /**
     * 更新商品属性值
     *
     * @param bean
     */
    @Update("update propertyvalue set value = #{value} where vid = #{vid} ")
    void update(PropertyValue bean);

    @Select("select * from propertyvalue where vid = #{vid}")
    @ResultMap("pvMap")
    PropertyValue get(int vid);

    /**
     * 查询某个产品下所有的属性值-->商品详情
     *
     * @param pid
     * @return
     */
    @Select("select * from propertyvalue where pid = #{pid} ")
    @Results(id = "pvMap", value = {
            @Result(id = true, column = "vid", property = "vid"),
            @Result(column = "pid", property = "product", one = @One(select = "indi.xu.dao.ProductDao.get", fetchType = FetchType.EAGER)),
            @Result(column = "pyid", property = "property", one = @One(select = "indi.xu.dao.PropertyDao.get", fetchType = FetchType.EAGER)),
            @Result(column = "value", property = "value")
    })
    List<PropertyValue> list(int pid);

    /**
     * 根据属性id和产品id，获取一个PropertyValue对象
     *
     * @param pid
     * @param pyid
     * @return
     */
    @Select("select * from propertyvalue where pid = #{pid} and pyid = #{pyid}")
    @ResultMap("pvMap")
    PropertyValue findByPidAndPyid(int pid, int pyid);

    /**
     * 删除一个商品所有属性值
     */
    @Delete("delete from propertyvalue where pid = #{pid}")
    void delete(int pid);

}
