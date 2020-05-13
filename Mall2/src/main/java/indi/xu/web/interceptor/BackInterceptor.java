package indi.xu.web.interceptor;

import indi.xu.domain.AdminUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 后台管理员权限拦截器。
 *
 * @author a_apple
 * @create 2020-03-04 13:56
 */
public class BackInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(BackInterceptor.class);

    /**
     * 后台操作拦截---判断是否登录管理员
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        LOGGER.info("进入----->BackInterceptor");
        LOGGER.info("请求URI:{}", request.getRequestURI());
        //访问需要登录才能操作的页面
        AdminUser user = (AdminUser) request.getSession().getAttribute("adUser");
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/fore/adminLogin");
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
