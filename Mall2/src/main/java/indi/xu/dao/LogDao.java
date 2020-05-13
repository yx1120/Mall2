package indi.xu.dao;

import indi.xu.domain.Log;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author a_apple
 * @create 2020-03-08 21:04
 */
@Repository
public interface LogDao {

    @Select("insert into tab_log values(null,#{logType},#{remoteAddr},#{requestUri},#{errorCode},#{content},#{uid},#{logDate})")
    void addLog(Log log);
}
