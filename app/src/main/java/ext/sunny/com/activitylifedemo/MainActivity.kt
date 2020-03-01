package ext.sunny.com.activitylifedemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import androidx.lifecycle.*
import ext.sunny.com.activitylifedemo.lifecycle.ActivityLifecylceObserverImpl

/**
 * LifecycleOwner:
 * 1、如果是用的高版本sdk,且Activity继承自AppCompatActivity时，不用实现LifecycleOwner接口，内部已经实现
 * 2、如果是继承Activity，则需手动实现LifecycleOwner接口，创建LifecycleRegistry，
 * 在相应方法中调用lifecycleOwner.handleLifecycleEvent()
 */
class MainActivity : AppCompatActivity() {
    private lateinit var lifecycleRegistry: LifecycleRegistry
    private lateinit var lifecycleObserver: ActivityLifecylceObserverImpl

    override fun onCreate(savedInstanceState: Bundle?) {
//        lifecycleRegistry = LifecycleRegistry(this)
//        lifecycleRegistry.currentState = Lifecycle.State.CREATED
        super.onCreate(savedInstanceState)
        printLog("MainActivity#onCreate()")
        setContentView(R.layout.activity_main)
        initParams()

    }

    private fun initParams() {

        lifecycleRegistry = lifecycle as LifecycleRegistry
        //lifecycleRegistry.addObserver(lifecycleObserver)
        lifecycleObserver = ActivityLifecylceObserverImpl()

        lifecycle.addObserver(lifecycleObserver)
        //GenericLifecycleObserver将从Lifecycle3.0中移除
        lifecycle.addObserver(object :GenericLifecycleObserver{
            override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
                Log.e("xxx","onStateChanged:$source,${event.name}")
            }

        })
//        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)



    }

//    override fun getLifecycle(): Lifecycle {
//
//        return lifecycleRegistry
//    }

    override fun onStart() {
        super.onStart()
        printLog("MainActivity#onStart()")
    }

    override fun onRestart() {
        super.onRestart()
        printLog("MainActivity#onRestart()")
    }

    override fun onResume() {
        super.onResume()
        printLog("MainActivity#onResume()")
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        setIntent(intent)
        printLog("MainActivity#onNewIntent()")
    }

    /*override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        super.onSaveInstanceState(outState, outPersistentState)
        printLog("MainActivity#onSaveInstanceState()")
    }*/

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        printLog("MainActivity#onSaveInstanceState()")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        printLog("MainActivity#onRestoreInstanceState()")
    }

    override fun onPause() {
        super.onPause()
        printLog("MainActivity#onPause()")
    }

    override fun onStop() {
        super.onStop()
        printLog("MainActivity#onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        printLog("MainActivity#onDestroy()")
    }



    fun printLog(msg:String){
        Log.e("ss",msg)
    }

    fun jump2Sencond(view: View) {
        var sencondAct:Intent = Intent(this,SecondActivity::class.java)
        startActivity(sencondAct)
    }
}

