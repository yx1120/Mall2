package indi.xu.log;

import indi.xu.log.anno.SystemControllerLog;
import indi.xu.domain.Log;
import indi.xu.domain.User;
import indi.xu.service.LogService;
import indi.xu.utils.DateUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * Spring Aop 实现日志切入类
 * 使用的是注解：需要使用环绕通知
 *
 * @author a_apple
 * @create 2020-03-08 21:03
 */
@Aspect
@Component
public class SystemLogAspect {

    @Autowired
    private LogService logService;

    private static final Logger logger = LoggerFactory.getLogger(SystemLogAspect.class);

    /**
     * Controller切入点表达式
     */
    @Pointcut("execution (* indi.xu.web.controller.auth..*.*(..))")
    public void controllerAspect() {
    }

    /**
     * 前置通知
     * 用于拦截Controller层记录用户的操作
     */
    private void before(JoinPoint joinPoint) {
        /*用isDebugEnabled方法判断下是能提升性能的*/
        if (logger.isInfoEnabled()) {
            logger.info("进入方法--" + joinPoint.getSignature());
        }
    }

    /**
     * [最终通知]
     * 用于拦截Controller层记录用户的操作
     * JoinPoint:接口
     * ---MethodInvocationProceedingJoinPoint  实现了
     * 方法: getTarget()：获取被连接的点所在的控制器xxxController对象。（通过切入点表达式
     *       getSingnature():获取连接点（方法）的完整签名    .getName():获取方法名
     *       getArgs():返回连接点（方法）的参数
     */
    private void after(JoinPoint joinPoint) {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        //读取session中的用户
        User user = (User) session.getAttribute("user");
        //请求的IP
        String ip = request.getRemoteAddr();
        String uri = request.getRequestURI();
        //未登录不记录日志
        if (user == null) {
            return;
        }
        try {

            String targetName = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            Object[] arguments = joinPoint.getArgs();
            //反射创建目标对象类，获取目标类的所有方法
            Class targetClass = Class.forName(targetName);
            Method[] methods = targetClass.getMethods();

            String logType = "";
            String logContent = "";
            for (Method method : methods) {
                //判断，当前拦截的方法名是否等于目标类中的某个方法
                if (method.getName().equals(methodName)) {
                    //再获取方法的参数类型
                    Class[] clazzs = method.getParameterTypes();
                    //如果长度相同。则判定为同一方法。   这里有问题，如果参数类型不同，而个数相同呢？
                    if (clazzs.length == arguments.length) {
                        //解析方法上的注解
                        logType = method.getAnnotation(SystemControllerLog.class).actionType();
                        logContent = method.getAnnotation(SystemControllerLog.class).description();
                        break;
                    }
                }
            }

            //*========控制台输出=========*//
            //System.out.println("[最终通知]");
            System.out.println("请求方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
            System.out.println("方法描述:" + logContent);
            System.out.println("请求人:" + user.getUname());
            System.out.println("IP地址：" + ip);
            System.out.println("------------------------>>>>>>>>>>>-------------------------\n");

            //*========数据库日志=========*//
            Log log = new Log();
            log.setUid(user.getUid());
            log.setLogType(logType);
            log.setContent(logContent);
            log.setErrorCode(HttpStatus.OK.toString());
            log.setLogDate(DateUtils.dateToTime(new Date()));
            log.setRequestUri(uri);
            log.setRemoteAddr(ip);
            //保存数据库
            logService.addLog(log);
        } catch (Exception e) {
            //记录本地异常日志
            logger.error("after:异常信息:{}", e.getMessage());
        }
    }

    /**
     * 后置返回通知
     * 方法正常执行后，会使用这个通知
     */
    private void afterReturn(JoinPoint joinPoint) {
        if (logger.isInfoEnabled()) {
            logger.info("正常结束--" + joinPoint.getSignature() + "\n");
        }
    }

    /**
     * 异常通知 用于拦截记录异常日志
     * 抛出异常后执行
     *
     * @param joinPoint 连接点（方法
     * @param e         代理对象执行方法抛出的异常
     */
    private void afterThrowing(JoinPoint joinPoint, Throwable e) {

        //控制台输出xxxService的异常信息
        printError(joinPoint, e);

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        //读取session中的用户
        User user = (User) session.getAttribute("user");
        //请求的IP
        String ip = request.getRemoteAddr();
        String uri = request.getRequestURI();
        if ((uri.contains("/fore") || uri.contains("/auth")) && user == null) {
            return;
        }

        try {

            String targetName = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            Object[] arguments = joinPoint.getArgs();
            Class targetClass = Class.forName(targetName);
            Method[] methods = targetClass.getMethods();
            String logType = "";
            String logContent = "";
            for (Method method : methods) {
                if (method.getName().equals(methodName)) {
                    Class[] clazzs = method.getParameterTypes();
                    if (clazzs.length == arguments.length) {
                        logType = method.getAnnotation(SystemControllerLog.class).actionType();
                        logContent = method.getAnnotation(SystemControllerLog.class).description();
                        break;
                    }
                }
            }

        } catch (Exception ex) {
            //记录本地异常日志
            logger.error("afterThrowing:异常信息:{}", ex.getMessage());
        }

    }

    private void printError(JoinPoint joinPoint, Throwable e) {
        System.out.println("[异常通知]");
        System.out.println("异常代码:" + e.getClass().getName());
        System.out.println("异常信息:" + e.getCause());
        System.out.println("异常方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
        System.out.println("------------------------>>>>>>>>>>>-------------------------\n");
    }

    /**
     * 环绕通知
     */
    @Around("controllerAspect()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        //返回值：因为拦截的是Controller,需要方法的返回值进行页面跳转
        Object value = null;
        try {
            //得到方法执行参数
            Object[] args = point.getArgs();
            //前置通知:打印连接点信息
            before(point);
            //执行方法
            value = point.proceed(args);
            //后置通知：打印连接点信息
            afterReturn(point);
            return value;
        } catch (Throwable e) {
            //异常通知：获取异常信息，保存数据库
            afterThrowing(point, e);
            //把异常抛给 SysExceptionResover类
            throw e;
        } finally {
            //最终通知
            after(point);
        }
    }

}
