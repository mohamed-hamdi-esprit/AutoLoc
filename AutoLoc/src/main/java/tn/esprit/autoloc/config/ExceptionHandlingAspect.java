package tn.esprit.autoloc.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Logs exceptions thrown by any method in the service layer.
 */
@Aspect
@Component
public class ExceptionHandlingAspect {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlingAspect.class);

    private static final String SERVICE_POINTCUT = "execution(* tn.esprit.autoloc.service..*(..))";

    @AfterThrowing(pointcut = SERVICE_POINTCUT, throwing = "ex")
    public void logException(JoinPoint joinPoint, Throwable ex) {
        String methodName = joinPoint.getSignature().toShortString();
        logger.error("[ExceptionHandlingAspect] Exception in {}: {} - {}", methodName,
                ex.getClass().getSimpleName(), ex.getMessage());
    }
}

