package ext.sunny.com.activitylifedemo.aop.login

/**@Annotation <p>登录拦截</p>
 * @Auth  Sunny
 * @date 2020/3/8
 * @Version V1.0.0
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class LoginInterceptor(val needLogin: Boolean)