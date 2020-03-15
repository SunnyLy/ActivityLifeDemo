package ext.sunny.com.aoplib.permission

import androidx.annotation.NonNull

/**@Annotation <p>需要的权限</p>
 * 此自定义的PointCut将作用于类，方法等
 * @Auth  Sunny
 * @date 2020/3/7
 * @Version V1.0.0
 */
@Target(
    AnnotationTarget.CLASS,
    AnnotationTarget.TYPE,
    AnnotationTarget.ANNOTATION_CLASS,
    AnnotationTarget.FUNCTION
)
@Retention(AnnotationRetention.RUNTIME)
annotation class PermissionsNeeded(@NonNull val permission: Array<String>, val requestCode: Int)