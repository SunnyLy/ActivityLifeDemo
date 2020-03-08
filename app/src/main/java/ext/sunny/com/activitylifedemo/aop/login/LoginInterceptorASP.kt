package ext.sunny.com.activitylifedemo.aop.login

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

    /**
     * 连接点：
     * call:表示调用（还未开始执行）
     * execution:表示正式开始执行
     * annotation是用于获取自定义的Pointcut参数,后面有个空格，切记切记
     */
    @Pointcut("execution(@ext.sunny.com.activitylifedemo.aop.login.LoginInterceptor * *(..)) && @annotation(needLogin) ")
    fun loginInterceptor(needLogin: LoginInterceptor) {

    }

    /**
     * 直接指定方法面的连接点
     */
    /* @Pointcut("execution(* ext.sunny.com.activitylifedemo.aop.AopActivity.login(..))")
     fun loginMethod(){

     }
     @Around("loginMethod()")
    fun beforloginMethod(joinPoint: ProceedingJoinPoint) {
        //判断是否需要登录
        Log.e("aop", "AOP>>>>beforloginMethod::before")
//        joinPoint.proceed()
    }

     */

    @Around("loginInterceptor(needLogin)")
    fun beforloginInterceptor(joinPoint: ProceedingJoinPoint, needLogin: LoginInterceptor) {
        //判断是否需要登录
        Log.e("aop", "AOP>>>>loginInterceptor::before")
        needLogin?.let {
            if (it.needLogin){
                joinPoint.proceed()
            }
        }
    }


}