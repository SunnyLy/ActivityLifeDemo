package ext.sunny.com.activitylifedemo.apt

import ext.sunny.com.annotationlib.ShapeFactory

/**@Annotation <p>圆形图</p>
 * @Auth  Sunny
 * @date 2020/3/6
 * @Version V1.0.0
 */
@ShapeFactory(id = "CircleShape", type = IShape::class)
class CircleShape : IShape {
    override fun printShape() {
        println("I'm a Circle!")
    }
}