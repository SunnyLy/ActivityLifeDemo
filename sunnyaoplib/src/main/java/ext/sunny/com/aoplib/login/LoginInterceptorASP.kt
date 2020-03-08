package ext.sunny.com.aoplib.login

import android.util.Log
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Pointcut

/**@Annotation <p>描述</p>
 * @Auth  Sunny
 * @date 2020/3/8
 * @Version V1.0.0
 */
@Aspect
class LoginInterceptorASP {

    @Pointcut("call(@ext.sunny.com.aoplib.login.LoginInterceptor * *(..))")
    fun loginInterceptor() {

    }

    @Around("loginInterceptor()")
    fun beforloginInterceptor(joinPoint: ProceedingJoinPoint) {
        //判断是否需要登录
        Log.e("aop", "AOP>>>>loginInterceptor::before")
//        joinPoint.proceed()
    }
}