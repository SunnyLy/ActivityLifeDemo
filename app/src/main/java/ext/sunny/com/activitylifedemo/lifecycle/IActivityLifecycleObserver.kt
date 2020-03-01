package ext.sunny.com.activitylifedemo.lifecycle

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

/**@Annotation <p>LifeCycle生命周期组件感知Activity或Fragment等的观察者</p>
 * @Auth  Sunny
 * @date 2020/3/1
 * @Version V1.0.0
 */
interface IActivityLifecycleObserver : LifecycleObserver {

    /**
     * Activity/Fragment创建时调用
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate()

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart()

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume()

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause()

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy()

    /**
     * 测试一下超级方法，Lifecycle生命周期感知到的所有事件都将走这里
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    fun onSuperStar()


}