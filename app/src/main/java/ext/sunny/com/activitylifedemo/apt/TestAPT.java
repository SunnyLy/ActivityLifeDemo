package ext.sunny.com.activitylifedemo.apt;

import ext.sunny.com.annotationlib.ShapeFactory;

/**
 * @Annotation <p>描述</p>
 * @Auth Sunny
 * @date 2020/3/6
 * @Version V1.0.0
 */
@ShapeFactory(id = "TestAPT", type = TestAPT.class)
public class TestAPT implements IShape {
    @Override
    public void printShape() {

    }
}
