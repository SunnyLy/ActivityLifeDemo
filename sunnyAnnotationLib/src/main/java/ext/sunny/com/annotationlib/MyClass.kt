package ext.sunny.com.annotationlib

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import kotlin.reflect.KClass

@Target(AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.CLASS)
@Retention(RetentionPolicy.CLASS)
annotation class MyClass(val type: KClass<*>, val id: String)