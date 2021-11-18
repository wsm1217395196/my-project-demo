package com.study.config;

import com.study.utils.RequestUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


/**
 * 添加日志切面
 */
@Slf4j
@Aspect
@Component
public class AddLogAspect {

    Logger logger = LoggerFactory.getLogger("testAppoint");

    @Pointcut("@annotation(com.study.config.AddLog)")
    public void addLog() {
    }

    //前置增强：在目标方法执行之前执行
    @Before("addLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        MethodSignature ms = (MethodSignature) joinPoint.getSignature();
        Method method = ms.getMethod();

        //参数名（路径上的）
        Map<String, String[]> parameterMap = request.getParameterMap();

        //所有参数名
        LocalVariableTableParameterNameDiscoverer localVariableTable = new LocalVariableTableParameterNameDiscoverer();
        String[] paraNameArr = localVariableTable.getParameterNames(method);
        //所有参数值
        Object[] args = joinPoint.getArgs();
        //所有参数名和值（包括请求体上的）
        Map<String, Object> allParam = new HashMap<>();
        for (int i = 0; i < paraNameArr.length; i++) {
            allParam.put(paraNameArr[i], args[i]);
        }

        // 获取IP地址
        String ip = RequestUtils.getIpAddress(request);
        //接口地址
        String requestURI = request.getRequestURI();
        String requestURL = request.getRequestURL().toString();

        //类方法路径
        String methodName = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();

        //注解上的值
        AddLog addLog = method.getAnnotation(AddLog.class);
        String desc = addLog.desc();
        String interfaceParam = addLog.interfaceParam();

        //使用SPEL进行key的解析
        ExpressionParser parser = new SpelExpressionParser();
        //SPEL上下文
        StandardEvaluationContext context = new StandardEvaluationContext();
        //把方法参数放入SPEL上下文中
        for (int i = 0; i < paraNameArr.length; i++) {
            context.setVariable(paraNameArr[i], args[i]);
        }
        // 使用变量方式传入业务动态数据
        if (interfaceParam.matches("^#.*.$")) {
            interfaceParam = parser.parseExpression(interfaceParam).getValue(context, String.class);
        }

        logger.info("测试aop日志打印");
    }

    //环绕增强：目标方法执行前后都可以织入增强处理
    @Around("addLog()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = joinPoint.proceed();
        return result;
    }

    //后置增强：在目标方法执行后执行，无论目标方法运行期间是否出现异常
    //注意：后置增强无法获取目标方法执行结果，可以在返回增强中获取
    @After("addLog()")
    public void after(JoinPoint joinPoint) {
        Object object = joinPoint.getTarget();
        String name = joinPoint.getSignature().getName();
        System.out.println(this.getClass().getName() + "：The " + name + "method ends.");
    }

    //返回增强：在目标方法正常结束后执行，可以获取目标方法的执行结果
    @AfterReturning(value = "addLog()", returning = "returnValue")
    public void logMethodCall(JoinPoint joinPoint, Object returnValue) throws Throwable {
        System.out.println("方法返回值为：" + returnValue);
    }

    //异常增强： 目标方法抛出异常之后执行，可以访问到异常对象，且可以指定在出现哪种异常时才执行增强代码
    @AfterThrowing(value = "addLog()", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Exception e) {
        String name = joinPoint.getSignature().getName();
        System.out.println(this.getClass().getName() + "：Result of the " + name + " method：" + e);
    }
}
