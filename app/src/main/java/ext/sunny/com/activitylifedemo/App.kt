package ext.sunny.com.activitylifedemo

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex

/**@Annotation <p>描述</p>
 * @Auth  Sunny
 * @date 2020/3/8
 * @Version V1.0.0
 */
class App : Application() {
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}