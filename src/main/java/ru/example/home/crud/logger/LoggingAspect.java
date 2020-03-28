package ru.example.home.crud.logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import org.apache.log4j.Logger;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger LOGGER = Logger.getLogger(LoggingAspect.class.getName());

    @AfterThrowing("within(ru.example.home.crud..*)")
    public void exception(JoinPoint joinPoint) {

        LOGGER.error("Exception from " + joinPoint.getClass());
        LOGGER.error("Method call : " + joinPoint.getSignature().getName());
        LOGGER.error("With args : " + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterThrowing(pointcut = "execution(* ru.example.home.crud.*.*.*(..))", throwing = "ex")
    public void logError(Exception ex) {
        LOGGER.error(ex.getMessage()+ " "+ ex.getCause());
    }

}
