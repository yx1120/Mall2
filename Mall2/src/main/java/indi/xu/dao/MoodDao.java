package indi.xu.dao;

import indi.xu.domain.Mood;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author a_apple
 * @create 2020-02-28 20:06
 */

@Repository
public interface MoodDao {

    @Insert("insert into mood(content,create_time,praise_num,status,uid) values (#{content},#{create_time},#{praise_num},#{status},#{uid});")
    @Options(useGeneratedKeys = true, keyProperty = "mid")
    void add(Mood mood);

    @Delete("delete from mood where mid = #{mid}")
    void delete(int mid);

    /**
     * 查询某一用户的所有说说
     *
     * @param uid
     * @return
     */
    @Select("select * from mood where uid = #{uid}")
    @ResultMap("moodMap")
    List<Mood> listByUid(int uid);

    /**
     * 查询所有动态
     * @return
     */
    @Select("select * from mood")
    @Results(id = "moodMap", value = {
            @Result(id = true, column = "mid", property = "mid"),
            @Result(column = "content", property = "content"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "praise_num", property = "praiseNum"),
            @Result(column = "status", property = "status"),
            @Result(column = "uid", property = "user",
                    one = @One(select = "indi.xu.dao.UserDao.get", fetchType = FetchType.EAGER)
            )

    })
    List<Mood> list();
}
