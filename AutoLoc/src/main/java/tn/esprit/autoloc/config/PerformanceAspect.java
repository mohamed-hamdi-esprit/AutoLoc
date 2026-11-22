package tn.esprit.autoloc.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Measures execution time of service layer methods and logs warnings for slow calls.
 */
@Aspect
@Component
public class PerformanceAspect {

    private static final Logger logger = LoggerFactory.getLogger(PerformanceAspect.class);

    private static final String SERVICE_POINTCUT = "execution(* tn.esprit.autoloc.service..*(..))";

    @Around(SERVICE_POINTCUT)
    public Object measureExecutionTime(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = pjp.proceed();
        long elapsed = System.currentTimeMillis() - start;
        String methodName = pjp.getSignature().toShortString();
        logger.info("[PerformanceAspect] Method {} executed in {} ms", methodName, elapsed);
        if (elapsed > 1000) {
            logger.warn("[PerformanceAspect] Method {} took longer than 1000 ms ({} ms)", methodName, elapsed);
        }
        return result;
    }
}

