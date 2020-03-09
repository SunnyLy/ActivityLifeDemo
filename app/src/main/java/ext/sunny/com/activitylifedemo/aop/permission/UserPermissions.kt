package ext.sunny.com.activitylifedemo.aop.permission

/**@Annotation <p>用户权限</p>
 * @Auth  Sunny
 * @date 2020/3/9
 * @Version V1.0.0
 */
@Target(
    AnnotationTarget.TYPE,
    AnnotationTarget.CLASS,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.CONSTRUCTOR
)
@Retention(AnnotationRetention.RUNTIME)
annotation class UserPermissions(val permissions: Array<String>, val requestCode: Int = 0)