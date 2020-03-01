package ext.sunny.com.activitylifedemo.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
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

    }

    override fun onBind(intent: Intent?): IBinder? {

        return mBinder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        return super.onUnbind(intent)
    }
}