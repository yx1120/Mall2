package indi.xu.web.converter;

import org.aspectj.weaver.ast.Var;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 字符串类型-->时间类型
 *
 * @author a_apple
 * @create 2020-04-09 15:24
 */
@Component
public class DateConverter implements Converter<String, Date> {

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public Date convert(String date) {
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
