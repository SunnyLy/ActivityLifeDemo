package ext.sunny.com.activitylifedemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.*
import com.google.gson.Gson
import ext.sunny.com.activitylifedemo.aop.AopActivity
import ext.sunny.com.activitylifedemo.callback.INetCallback
import ext.sunny.com.activitylifedemo.lifecycle.ActivityLifecylceObserverImpl
import ext.sunny.com.activitylifedemo.lifecycle.TestViewModel
import ext.sunny.com.activitylifedemo.net.ChapterBean
import ext.sunny.com.activitylifedemo.net.okhttp.OkHttpService
import ext.sunny.com.activitylifedemo.net.retrofit.IChapterService
import ext.sunny.com.activitylifedemo.net.retrofit.StringConverterFactory
import kotlinx.android.synthetic.main.activity_second.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * LifecycleOwner:
 * 1、如果是用的高版本sdk,且Activity继承自AppCompatActivity时，不用实现LifecycleOwner接口，内部已经实现
 * 2、如果是继承Activity，则需手动实现LifecycleOwner接口，创建LifecycleRegistry，
 * 在相应方法中调用lifecycleOwner.handleLifecycleEvent()
 */
class MainActivity : AppCompatActivity() {
    private lateinit var lifecycleRegistry: LifecycleRegistry
    private lateinit var lifecycleObserver: ActivityLifecylceObserverImpl

    private lateinit var nameViewModel: TestViewModel
    private var nameLiveData: MutableLiveData<String>? = null

    private lateinit var etLiveData: EditText
    private lateinit var btnLiveData: Button

    override fun onCreate(savedInstanceState: Bundle?) {
//        lifecycleRegistry = LifecycleRegistry(this)
//        lifecycleRegistry.currentState = Lifecycle.State.CREATED
        super.onCreate(savedInstanceState)
        printLog("MainActivity#onCreate()")
        setContentView(R.layout.activity_main)
        etLiveData = findViewById(R.id.et_livedata)
        btnLiveData = findViewById(R.id.btn_livedata)
        initParams()

    }

    private fun initParams() {

        /**
         * LifeCycle相关初始化
         */
        lifecycleRegistry = lifecycle as LifecycleRegistry
        //lifecycleRegistry.addObserver(lifecycleObserver)
        lifecycleObserver = ActivityLifecylceObserverImpl()

        lifecycle.addObserver(lifecycleObserver)
        //GenericLifecycleObserver将从Lifecycle3.0中移除
        lifecycle.addObserver(object : GenericLifecycleObserver {
            override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
                Log.e("xxx", "onStateChanged:$source,${event.name}")
            }

        })
//        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)

        /**
         * LiveData与ViewModel
         */
        nameViewModel = TestViewModel()
        nameLiveData = nameViewModel.getLiveData()
        nameLiveData!!.observe(this, object : Observer<String> {
            override fun onChanged(name: String?) {
                findViewById<TextView>(R.id.tv_main).text = name

            }

        })

        btnLiveData.setOnClickListener {
            var str = etLiveData.text.trim().toString()
            nameViewModel.setUserName(str)
        }


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


    fun printLog(msg: String) {
        Log.e("ss", msg)
    }

    fun jump2Sencond(view: View) {
        var sencondAct: Intent = Intent(this, SecondActivity::class.java)
        startActivity(sencondAct)
    }

    fun sendLiveData(view: View) {}
    fun okHttpGet(view: View) {
        var chapterListUrl: String = "/wxarticle/chapters/json"
        OkHttpService.getInstance().getChaptersList(chapterListUrl, object : INetCallback<String> {
            override fun onSuccess(result: String) {
                runOnUiThread() {
                    findViewById<TextView>(R.id.tv_main).text = result
                }
            }

            override fun onError(msg: String?, errorCode: Int) {
                runOnUiThread {
                    findViewById<TextView>(R.id.tv_main).text = msg
                }
            }

        })
    }

    /**
     * retrofit请求
     */
    fun retrofitGet(view: View) {
        var retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://www.wanandroid.com")
            .addConverterFactory(StringConverterFactory.create())
            .build()
        var chapterService: IChapterService = retrofit.create(IChapterService::class.java)
        chapterService.getChapterList().enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {

                printLog("请求失败:${call.request().url}\n错误信息:${t.message}")

            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                var request = call.request()
                var url = request.url
                printLog("请求连接:$url")

                var code = response.code()
                printLog("返回状态码：$code")

                if (response.isSuccessful) {
                    var data = response.body()
                    printLog("返回的数据:$data")
                }
                runOnUiThread {
                    var result = response!!.body()
                    findViewById<TextView>(R.id.tv_main).text = response.body().toString()
                }
            }

        })

    }

    fun startAOP(view: View) {
        startActivity(Intent(this, AopActivity::class.java))
    }
}

