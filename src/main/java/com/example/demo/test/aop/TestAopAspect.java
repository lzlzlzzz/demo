package com.example.demo.test.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class TestAopAspect {
    /**
     * Spring中使用@Pointcut注解来定义方法切入点
     *
     * @Pointcut 用来定义切点，针对方法。后面的aop增强均是围绕切入点来完成的
     * @Aspect 用来定义切面，针对类。
     * 此处仅配置被我们刚才定义的注解：AuthToken修饰的方法即可
     *
     */
    @Pointcut("@annotation(testAop)")
    public void doAuthToken(TestAop testAop) {
    }

    @Before("doAuthToken(testAop)")
    public void before(TestAop testAop){
        System.out.println("准备登录");
    }

    /**
     * 此处我使用环绕增强，在方法执行之前或者执行之后均会执行。
     */
    @Around("doAuthToken(testAop)")
    public Object deArrond(ProceedingJoinPoint pjp, TestAop testAop) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 获取访问该方法所需的role_name信息
        String name = testAop.name();
        if (name.equals("")){    //name没写，默认是“”，长度为1
            // 未登录，不执行方法，直接返回错误信息
            return "请登陆后再试！";
        } else {
            // 需要验证身份
            String role = request.getParameter("name");
            if (name.equals(role)) {
                // 身份匹配成功
                return pjp.proceed();
            }
            return "权限校验失败，不具有指定的身份";
        }
    }
}
