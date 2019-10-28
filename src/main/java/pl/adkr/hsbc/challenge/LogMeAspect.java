package pl.adkr.hsbc.challenge;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LogMeAspect {

    @Pointcut("within(@org.springframework.stereotype.Repository *)" +
            " || within(@org.springframework.stereotype.Service *)" +
            " || within(@org.springframework.stereotype.Component *)" +
            " || within(@org.springframework.web.bind.annotation.RestController *)")
    public void springBeanPointcut() {
    }

    @Pointcut("within(pl.adkr.hsbc.challenge.posting..*)" +
            " || within(pl.adkr.hsbc.challenge.user..*)")
    public void applicationPackagePointcut() {
    }

    @AfterThrowing(pointcut = "applicationPackagePointcut() && springBeanPointcut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        Logger log = LoggerFactory.getLogger(joinPoint.getSignature().getDeclaringType());
        log.error("Exception in {} with cause = {}", joinPoint.getSignature().toShortString(),
                e.getCause() != null ? e.getCause() : "NULL");
    }

    @Around("applicationPackagePointcut() && springBeanPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Logger log = LoggerFactory.getLogger(joinPoint.getSignature().getDeclaringType());
        String methodName = joinPoint.getSignature().toShortString();

        if (log.isDebugEnabled()) {
            log.debug("Invoke {} with argument[s] = {}", methodName, Arrays.toString(joinPoint.getArgs()));
        }
        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (IllegalArgumentException e) {
            log.error("Illegal argument {} in {}", Arrays.toString(joinPoint.getArgs()),
                    joinPoint.getSignature().toShortString());
            throw e;
        } finally {
            if (log.isDebugEnabled()) {
                log.debug("End {} with result = {}", joinPoint.getSignature().toShortString(), result);
            }
        }
        return result;
    }
}

