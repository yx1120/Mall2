package indi.xu.dao;


import indi.xu.domain.Category;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author a_apple
 * @create 2019-11-23 16:27
 */

@Repository
public interface CategoryDao {
    /**
     * 添加分类
     * addCategory
     *
     */
    @Insert("insert into category(cname,parent_id,category_level) values( #{cname} ,#{parentCategory.cid},#{categoryLevel})")
    @Options(useGeneratedKeys = true, keyProperty = "cid")
    void add(Category bean);

    /**
     * 根据id删除(1/2级)分类
     * deleteCategory
     *
     */
    @Update("update category set is_deleted = 1 where cid = #{cid}")
    void delete(int cid);

    @Update("update category set is_deleted =1  where parent_id = #{parentId}")
    void deleteByParentId(int parentId);

    /**
     * 更新1/2级分类信息：这里只更新名称
     * fixCategory
     *
     */
    @Update("update category set cname = #{cname} where cid = #{cid} ")
    void update(Category bean);

    /**
     * 根据id获取1/2级分类
     * findCategory
     *
     */
    @Select("select * from category where cid = #{cid}")
    @Results(id = "categoryMap", value = {
            @Result(id = true, column = "cid", property = "cid"),
            @Result(column = "cname", property = "cname"),
            @Result(column = "category_level",property = "categoryLevel"),
            @Result(column = "is_deleted",property = "isDeleted",javaType = Byte.class),
            @Result(column = "parent_id", property = "parentCategory", one = @One(select = "indi.xu.dao.CategoryDao.get", fetchType = FetchType.EAGER))
    })
    Category get(int cid);

    /**
     * 根据分类级别获取分类
     */
    @Select("select * from category where category_level = #{level} and is_deleted = 0 order by cid desc")
    @ResultMap("categoryMap")
    List<Category> findByLevel(int level);

    /**
     * 根据一级分类查找二级分类
     * 作用与：首页，只需要姓名即可
     */
    @Select("select * from category where parent_id = #{parentId} and is_deleted = 0 order by cid desc")
    @ResultMap("categoryMap")
    List<Category> findByParentId(int parentId);

    /**
     * 查询指定一级分类多少条二级分类
     */
    @Select("select * from category where parent_id = #{parentId} and is_deleted = 0 limit 0,#{size} ")
    List<Category> findByFidAndSize(@Param("parentId") int parentId, @Param("size") int size);

    /**
     * 重名问题
     */
    @Select("select * from category where cname = #{cname} and is_deleted = 0")
    Category findByName(String cname);
}
