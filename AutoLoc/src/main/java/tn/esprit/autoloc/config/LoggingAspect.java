package tn.esprit.autoloc.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Logs method entry and exit for all service layer methods.
 */
@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    private static final String SERVICE_POINTCUT = "execution(* tn.esprit.autoloc.service..*(..))";
//logging gball ayy methode tsta3malha
    @Before(SERVICE_POINTCUT)
    public void logMethodEntry(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        StringBuilder params = new StringBuilder();
        for (int i = 0; i < args.length; i++) {
            params.append(args[i]);
            if (i < args.length - 1) {
                params.append(", ");
            }
        }
        logger.info("[LoggingAspect] Entering method: {} with args: [{}]", methodName, params);
    }

    @After(SERVICE_POINTCUT)
    public void logMethodExit(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        logger.info("[LoggingAspect] Exiting method: {}", methodName);
    }
}

