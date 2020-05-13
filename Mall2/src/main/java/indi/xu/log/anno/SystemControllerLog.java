package indi.xu.log.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于Controller层的日志注解
 *
 * @author a_apple
 * @create 2020-03-08 20:57
 */
@Target({ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface SystemControllerLog {

    //描述操作
    String description() default "";

    //操作类型：1，用户操作  增删改查
    String actionType() default "";
}
