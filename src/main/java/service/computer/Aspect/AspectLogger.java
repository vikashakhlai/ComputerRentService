package service.computer.Aspect;

import service.computer.Rest.MainRestController;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class AspectLogger {
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(AspectLogger.class);

    @Pointcut("execution(* service.computer.Rest.MainRestController.*(..))")
    public void calledAtMainREstController(){}

    @After("calledAtMainREstController()")
    public void log(JoinPoint point) {
        log.info(point.getSignature().getName() + " called...");
    }

    @Pointcut("execution(* service.computer.Rest.ComputerRestController.*(..))")
    public void calledAtComputerRestController(){}

    @After("calledAtComputerRestController()")
    public void log2(JoinPoint point) {
        log.info(point.getSignature().getName() + " called...");
    }

    @Pointcut("execution(* service.computer.Rest.UserRentRestController.*(..))")
    public void calledAtUserRentRestController(){}

    @After("calledAtUserRentRestController()")
    public void log3(JoinPoint point) {
        log.info(point.getSignature().getName() + " called...");
    }
}
