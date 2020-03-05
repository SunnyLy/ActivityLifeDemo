package ext.sunny.com.activitylifedemo.reflect

import android.os.Build
import androidx.annotation.RequiresApi
import ext.sunny.com.activitylifedemo.UserInfoBean
import java.lang.reflect.Constructor
import java.lang.reflect.Method

/**@Annotation <p>Java中Type超级接口的用法</p>
 * @Auth  Sunny
 * @date 2020/3/4
 * @Version V1.0.0
 */
class TypeTest {

    companion object {
        @RequiresApi(Build.VERSION_CODES.O)
        @JvmStatic
        fun main(args: Array<String>) {
            var listUserInfo = ArrayList<UserInfoBean>()
            var type = UserInfoBean::class.java.typeName
            var simpleName = UserInfoBean::class.java.simpleName
            var canonicalName = UserInfoBean::class.java.canonicalName
            println("type=$type\nsimpleName:$simpleName:\ncanonicalName:$canonicalName")

            var typeName = listUserInfo.javaClass.typeName
            println("typeName:$typeName")

            //动态代理 就是利用反射，创建类实例对象，然后调用相应的方法
            var constructor: Constructor<UserInfoBean> = UserInfoBean::class.java.getConstructor()
            var userInfoBean = UserInfoBean::class.java.newInstance()
            var setNameMethod: Method =
                UserInfoBean::class.java.getMethod("setName", String::class.java)
            setNameMethod.invoke(userInfoBean, "SunnyGeGe")

            var getNameMethod: Method = UserInfoBean::class.java.getMethod("getName")
            var result: String = getNameMethod.invoke(userInfoBean) as String
            println("返回值为:$result")

            println("constructorName:${constructor.name}\nuserInfoBean:$userInfoBean")


        }
    }
}