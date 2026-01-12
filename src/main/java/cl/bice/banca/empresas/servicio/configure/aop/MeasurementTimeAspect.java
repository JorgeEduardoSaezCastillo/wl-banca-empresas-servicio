package cl.bice.banca.empresas.servicio.configure.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MeasurementTimeAspect {

    private final Logger biceLogger = LoggerFactory.getLogger(MeasurementTimeAspect.class);

    @Around("@annotation(cl.bice.banca.empresas.servicio.configure.Timed)")
    public Object measureExecutionTime(final ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;
        biceLogger.info("El metodo [{}] se ejecutó en [{}] milisegundos ",joinPoint.getSignature().getName(), executionTime);
        return proceed;
    }
}
