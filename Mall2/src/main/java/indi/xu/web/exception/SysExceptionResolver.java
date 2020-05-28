package indi.xu.web.exception;

import indi.xu.domain.Log;
import indi.xu.service.LogService;
import indi.xu.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * 全局异常处理器，代理Controller层的抛出的异常，自定义抛出的异常，都会到这里
 *      作用：记录错误日志
 *
 * @author a_apple
 * @create 2020-03-03 12:52
 */
public class SysExceptionResolver implements HandlerExceptionResolver {

    @Autowired
    private LogService logService;

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        //获取请求URI
        String uri = request.getRequestURI();
        //保存错误日志
        Log log = new Log();
        log.setUid(-1);
        log.setLogType("error");
        log.setContent("错误报告：" + ex);
        log.setErrorCode("500");
        log.setRequestUri(uri);
        log.setLogDate(DateUtils.dateToTime(new Date()));

        logService.addLog(log);

        //服务器端的异常都--->500.jsp
        ModelAndView mv = new ModelAndView();
        mv.setViewName("error/500");
        return mv;
    }
}
