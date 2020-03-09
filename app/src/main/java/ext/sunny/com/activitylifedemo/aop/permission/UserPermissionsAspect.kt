package ext.sunny.com.activitylifedemo.aop.permission

import android.util.Log
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut

/**@Annotation <p>描述</p>
 * @Auth  Sunny
 * @date 2020/3/9
 * @Version V1.0.0
 */
@Aspect
class UserPermissionsAspect {
    final val TAG: String = "AOP:UserPermissionAspect"

    @Pointcut("execution(@ext.sunny.com.activitylifedemo.aop.permission.UserPermissions * *(..)) ")
    fun onClassLoaded() {
        Log.e("xxxx", "$TAG>>>类加载")
    }

    @Around("onClassLoaded()")
    fun onClassLoadedAround(joinPoint: ProceedingJoinPoint) {
        Log.e("xxxx", "$TAG.Around>>>>开始准备代码编织，以替换原代码")
        joinPoint.proceed()
    }
}