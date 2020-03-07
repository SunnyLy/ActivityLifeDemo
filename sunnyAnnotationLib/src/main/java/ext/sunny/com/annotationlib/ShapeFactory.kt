package ext.sunny.com.annotationlib

import javax.annotation.processing.SupportedAnnotationTypes
import kotlin.reflect.KClass

/**@Annotation <p>自定义注解</p>
 * 作用范围为类
 * @Auth  Sunny
 * @date 2020/3/6
 * @Version V1.0.0
 */
@Target(AnnotationTarget.CLASS, AnnotationTarget.ANNOTATION_CLASS)
@Retention(AnnotationRetention.BINARY)
annotation class ShapeFactory(val id: String, val type: KClass<*>)