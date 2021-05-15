package com.iqmsoft.demo.core


import org.aspectj.lang.JoinPoint
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.*
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import java.util.*

@Aspect
@Component
class LogAspect {

    private val LOG = LoggerFactory.getLogger(LogAspect::class.java)


    @Pointcut("execution(public * com.spring.demo.controller..*.*(..))") //.*.*代表所有子目录下的所有方法，最后括号里(..)的两个..代表所有参数
    fun logPointCut() {

    }

    @Before("logPointCut()")
    @Throws(Throwable::class)
    fun doBefore(joinPoint: JoinPoint) {

        System.out.println("doBefore-------------")
       
        val attributes = RequestContextHolder.getRequestAttributes() as ServletRequestAttributes
        val request = attributes.request

       
        LOG.info(" : " + request.requestURL.toString())
        LOG.info("HTTP METHOD : " + request.method)
        LOG.info("IP : " + request.remoteAddr)
        LOG.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "."
                + joinPoint.getSignature().getName())
        LOG.info(": " + Arrays.toString(joinPoint.getArgs()))

    }

    @AfterReturning(returning = "ret", pointcut = "logPointCut()")
    @Throws(Throwable::class)
    fun doAfterReturning(ret: Any) {
        
        LOG.info(" : " + ret)
    }

    @Around("logPointCut()")
    @Throws(Throwable::class)
    fun doAround(pjp: ProceedingJoinPoint): Any {
        val startTime = System.currentTimeMillis()
        val ob = pjp.proceed()
        LOG.info(": " + (System.currentTimeMillis() - startTime))
        return ob
    }

}
