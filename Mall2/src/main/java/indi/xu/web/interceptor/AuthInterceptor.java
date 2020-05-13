package indi.xu.web.interceptor;

import indi.xu.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 前端用户权限拦截器
 *
 * @author a_apple
 * @create 2020-03-04 13:56
 */
public class AuthInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthInterceptor.class);

    /**
     * 预处理：在Controller方法执行之前执行
     * 返回true:放行，进入下一个拦截器  （（如果没有，执行controlLer中的方法
     * 返回false:不放行
     * <p>
     * 登录判断
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 配置拦截的是/auth
        LOGGER.info("进入----->AuthInterceptor");
        //访问需要登录才能操作的页面。
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/fore/login");
            return false;
        }
        return true;
    }

    /**
     * 从Controller回来后处理方法
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

}
