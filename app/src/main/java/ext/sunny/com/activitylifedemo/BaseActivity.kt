package ext.sunny.com.activitylifedemo

import android.util.Log
import androidx.appcompat.app.AppCompatActivity

/**@Annotation <p>描述</p>
 * @Auth  Sunny
 * @date 2020/2/17
 * @Version V1.0.0
 */
abstract class BaseActivity : AppCompatActivity() {

    fun printLog(msg: String) {
        Log.e("SS", msg)
    }
}