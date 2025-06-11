package top.threshold.aphrodite.app.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Order(0)
@Aspect
@Component
public class LogAspect {
    @Autowired
    private ObjectMapper objectMapper;

    private static final String LINE_SEPARATOR = System.lineSeparator();

    @Pointcut("execution(public * top.threshold.aphrodite.controller..*.*(..))")
    public void requestAspect() {
    }

    @Around("requestAspect()")
    public Object process(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes != null ? requestAttributes.getRequest() : null;

        if (request != null && request.getRequestURL().toString().endsWith("health.do")) {
            return proceedingJoinPoint.proceed();
        }
        log.info("========================================== Start ==========================================");
        log.info("URL            : {}", request.getRequestURL().toString());
        log.info("HTTP Method    : {}", request.getMethod());
        log.info("Class Method   : {}.{}", proceedingJoinPoint.getSignature().getDeclaringTypeName(), proceedingJoinPoint.getSignature().getName());
        log.info("IP             : {}", request.getRemoteAddr());
        log.info("Request Args   : {}", getParams(proceedingJoinPoint));

        long startTime = System.currentTimeMillis();
        Object proceed = proceedingJoinPoint.proceed();

        log.info("Response Args  : {}", objectMapper.writeValueAsString(proceed).substring(0, 1024));
        log.info("Time-Consuming : {} ms", System.currentTimeMillis() - startTime);
        log.info("=========================================== End ===========================================" + LINE_SEPARATOR);

        return proceed;
    }

    private String getParams(JoinPoint joinPoint) {
        StringBuilder params = new StringBuilder();
        if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
            for (Object arg : joinPoint.getArgs()) {
                if (arg instanceof HttpServletResponse || arg instanceof HttpServletRequest
                    || arg instanceof MultipartFile || (arg instanceof Object[] && ((Object[]) arg).getClass().getComponentType() == MultipartFile.class)) {
                    continue;
                }
                try {
                    params.append(objectMapper.writeValueAsString(arg));
                } catch (Exception e1) {
                    log.error(e1.getMessage());
                }
            }
        }
        return params.toString();
    }
}
