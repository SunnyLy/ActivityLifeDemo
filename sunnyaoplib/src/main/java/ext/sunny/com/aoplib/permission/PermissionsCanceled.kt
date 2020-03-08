package ext.sunny.com.aoplib.permission

/**@Annotation <p>被取消的权限</p>
 * 作用于方法，适用于回调
 * @Auth  Sunny
 * @date 2020/3/7
 * @Version V1.0.0
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.BINARY)
annotation class PermissionsCanceled