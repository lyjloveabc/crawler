package thor.freedom.crawler.sys.aspect;

import thor.freedom.crawler.core.PrintUtil;
import thor.freedom.crawler.core.RequestUtil;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * Created by Thor on 2018/1/18.
 */
@Aspect
@Component
@Slf4j
public class ControllerLogAspect {
    @Value("${spring.profiles.active}")
    private String activeProfile;

    //控制层切入点
    @Pointcut("execution(* thor.freedom.crawler.controller.*.* (..))")
    public void controllerLogPointcut() {
    }

    //控制层请求打印
    @Around("controllerLogPointcut()")
    public Object controllerLogV2(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Gson gson = new Gson();
        HttpServletRequest httpServletRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        PrintUtil.printEmptyLine(2);

        log.info("====== Before request biz handle ======");
        log.info("请求URI: " + httpServletRequest.getRequestURI());
        if ("GET".equalsIgnoreCase(httpServletRequest.getMethod())) {
            String prefix;
            switch (activeProfile) {
                case "local":
                    prefix = "http://127.0.0.1:7000";
                    break;
                case "prod":
                    prefix = "http://121.43.166.200:7000";
                    break;
                default:
                    prefix = "";
                    break;
            }

            log.info("模拟出客户端的请求: " + prefix + httpServletRequest.getRequestURI() + "?" + RequestUtil.paramMapToString(RequestUtil.getParamMapFromRequest(httpServletRequest)));
        }
        log.info("请求参数:" + gson.toJson(httpServletRequest.getParameterMap()));
        log.info("内部方法: " + proceedingJoinPoint.getSignature().toString());
        log.info("方法参数: " + Arrays.toString(proceedingJoinPoint.getArgs()));

        Object object = proceedingJoinPoint.proceed();

        String result = gson.toJson(object);

        log.info("请求返回值(最多只打印50字符): " + (result.length() > 50 ? result.substring(0, 50) : result));
        log.info("====== After request biz handle ======\n");

        return object;
    }

}
