package indi.xu.log.anno;

import java.lang.annotation.*;

/**
 * 用于Service层的日志注解
 *
 * @author a_apple
 * @create 2020-03-08 20:57
 */

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemServiceLog {

    //定义成员
    String description() default "";
}
