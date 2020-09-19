package ext.sunny.com.activitylifedemo.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.os.Parcel
import ext.sunny.com.activitylifedemo.IPerson.Stub

/**@Annotation <p>服务端</p>
 * @Auth  Sunny
 * @date 2020/2/29
 * @Version V1.0.0
 */
class PersonService : Service() {
    final val tag: String = PersonService::class.java.simpleName
    var userName: String? = ""
    val mBinder: Stub = object : Stub() {
        override fun getName(): String {
            return "SunnyAIDL:$userName"
        }

        override fun setName(name: String?) {
            userName = name
        }

        override fun onTransact(code: Int, data: Parcel, reply: Parcel?, flags: Int): Boolean {
            //这里可以增加对远程调用的权限校验。
            //流程：
            //1、Server服务端自定义权限，比如：com.ext.sunny.aidl.permission
            //2、Client客户端在AndroidManifest.xml中声明此权限
            return super.onTransact(code, data, reply, flags)
        }

    }

    override fun onBind(intent: Intent?): IBinder? {

        return mBinder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        return super.onUnbind(intent)
    }


}