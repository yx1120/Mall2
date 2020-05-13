package indi.xu.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间转换类
 * 因为Bean里面使用的是java.util.Date       MySql里面保存时间戳需要用 java.sql.TimeStamp
 *
 * @author a_apple
 */
public class DateUtils {

    private DateUtils() {
    }

    /**
     * Date转TimeStamp
     *
     * @param date util.Date
     * @return
     */
    public static Timestamp dateToTime(Date date) {
        if (null == date) {
            return null;
        }
        return new Timestamp(date.getTime());
    }

    /**
     * TimeStamp转Date
     *
     * @param ts sql.Timestamp
     * @return
     */
    public static Date timeToDate(Timestamp ts) {
        if (null == ts) {
            return null;
        }
        return new Date(ts.getTime());
    }


}
