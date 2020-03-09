package ext.sunny.com.aoplib.permission

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Build
import android.util.Log
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.*
import kotlin.reflect.KClass

/**@Annotation <p>需要权限的切入点</p>
 * @Auth  Sunny
 * @date 2020/3/7
 * @Version V1.0.0
 */
@Aspect
class PermissionAspect {

    //Pointcut:方法切入点（插桩）,称作连接点（joinpoint)
    @Pointcut("execution(@ext.sunny.com.aoplib.permission.PermissionsNeeded * *(..))")
    fun requestPermissionMethod(/*needPermission: PermissionsNeeded*/) {
        Log.e("aop", "AOP>>>>>找到了插入点")

    }

    //Advice:消息处理中心，也就是业务逻辑处理
    //包括：
    //1、Around:代码编织处（只在指定的切入点处替换旧代码）
    //2、Before:执行切入点前
    //3、After:执行切入点后
    /* @Around("requestPermissionMethod(needPermission)")
     fun AroundJoinPoint(joinPoint: ProceedingJoinPoint) {

         //利用切点，扫描获取到类信息，然后通过反射获取当前Activity的onRequestPermissionsResult方法
         //编织代码，替换掉原Activity中的onRequestPermissionsResult方法
         var obj = joinPoint.`this`
         var curActivity: Activity? = null
         var curClass: KClass<Any>? = null
         obj?.let { it ->
             if (it is Activity) {
                 curActivity = it
                 curClass = curActivity?.javaClass?.kotlin as KClass<Any>?

             }
         }

         var curActivityClass = curActivity

     }*/

    @SuppressLint("NewApi")
    @Around("requestPermissionMethod()")
    fun AfterOnCreate(joinPoint: ProceedingJoinPoint/*, needPermission: PermissionsNeeded*/) {
        Log.e("xxxx", "AOP>>>>AfterOnCreate")

        var permission: Array<String>? = null
       /* var requestCode: Int = 0
        needPermission?.let {
            permission = it.permission
            requestCode = it.requestCode
        }
        var context = joinPoint.`this`
        context.let {
            if (it is Activity) {
                it.requestPermissions(permission, requestCode)
            }
        }*/

        joinPoint.proceed()

    }

    /**
     * 指进入requestPermissionMethod()方法，正式开始执行逻辑前
     * 可以做一些初始化操作
     */
    @Before("requestPermissionMethod()")
    fun getPermissionBefore() {
        Log.e("xxxx", "AOP>>>>>准备开始requestPermissionMethod()")


    }

    /**
     * 执行完方法后
     */
    @After("requestPermissionMethod()")
    fun getPermissionAfter(/*needPermission: PermissionsNeeded*/) {
        Log.e("aop", "AOP>>>>>>pointcut后")
    }

    /**
     * 不使用自定义的PointCut(注解），
     * 直接使用目标类的全限定名
     */
//    @Around("execution(* ext.sunny.com.activitylifedemo.aop.AopActivity.onCreate(..))")
    fun aopActivityOncreate(joinPoint: ProceedingJoinPoint, needPermission: PermissionsNeeded) {
        Log.e("aop", "AOP::AopActivity#onCreate()")
        joinPoint.proceed()
        var permission: Array<String>? = null
        var requestCode: Int = 0
        needPermission?.let {
            permission = it.permission
            requestCode = it.requestCode
        }
        var context = joinPoint.`this`
        context.let {
            if (it is Activity) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    it.requestPermissions(permission, requestCode)
                }
            }
        }

    }
}