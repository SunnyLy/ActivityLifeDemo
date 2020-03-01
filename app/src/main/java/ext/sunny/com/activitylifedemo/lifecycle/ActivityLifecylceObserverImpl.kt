package ext.sunny.com.activitylifedemo.lifecycle

import android.util.Log

/**@Annotation <p>描述</p>
 * @Auth  Sunny
 * @date 2020/3/1
 * @Version V1.0.0
 */
class ActivityLifecylceObserverImpl:IActivityLifecycleObserver {
    override fun onCreate() {
        Log.e("xxx","Lifecycle#onCreate()")

    }

    override fun onStart() {
        Log.e("xxx","Lifecycle#onStart()")
    }

    override fun onResume() {
        Log.e("xxx","Lifecycle#onResume()")
    }

    override fun onPause() {
        Log.e("xxx","Lifecycle#onPause()")
    }

    override fun onDestroy() {
        Log.e("xxx","Lifecycle#onDestroy()")
    }

    override fun onSuperStar() {
        Log.e("xxx","Lifecycle#onSuperStar()")
    }
}