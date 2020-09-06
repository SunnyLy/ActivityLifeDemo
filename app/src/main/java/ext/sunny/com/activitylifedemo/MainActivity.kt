package ext.sunny.com.activitylifedemo

import android.content.Intent
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import ext.sunny.com.activitylifedemo.aop.AopActivity
import ext.sunny.com.activitylifedemo.callback.INetCallback
import ext.sunny.com.activitylifedemo.lifecycle.ActivityLifecylceObserverImpl
import ext.sunny.com.activitylifedemo.lifecycle.TestViewModel
import ext.sunny.com.activitylifedemo.net.okhttp.OkHttpService
import ext.sunny.com.activitylifedemo.net.retrofit.IChapterService
import ext.sunny.com.activitylifedemo.net.retrofit.StringConverterFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.security.InvalidKeyException
import java.security.NoSuchAlgorithmException
import java.security.spec.InvalidKeySpecException
import java.security.spec.KeySpec
import javax.crypto.*
import javax.crypto.spec.DESKeySpec

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
        decrptData()

    }

    private fun decrptData() {

//        String var1 = "Hello";
//        String var2 = "World";
//        boolean result = (var1 == var2);
//        String str1 = "result="+result;
//        String str2 = str1 + ",hello";
//        System.out.println(str2);
        try {
            val localObject1 =
                DESKeySpec("Captur3Th1s".toByteArray())
            val secretKey = SecretKeyFactory.getInstance("DES")
                .generateSecret(localObject1 as KeySpec)
            val decData = Base64.decode(
                "k3FElEG9lnoWbOateGhj5pX6QsXRNJKh///8Jxi8KXW7iDpk2xRxhQ==",
                0
            )
            val localCipher = Cipher.getInstance("DES")
            localCipher.init(2, secretKey)
            val decryptString = String(localCipher.doFinal(decData))
            println("解密数据：$decryptString")
            Toast.makeText(this, "解密数据：$decryptString", Toast.LENGTH_SHORT).show()
        } catch (e: InvalidKeySpecException) {
            e.printStackTrace()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        } catch (e: NoSuchPaddingException) {
            e.printStackTrace()
        } catch (e: InvalidKeyException) {
            e.printStackTrace()
        } catch (e: IllegalBlockSizeException) {
            e.printStackTrace()
        } catch (e: BadPaddingException) {
            e.printStackTrace()
        }

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
//        var sencondAct: Intent = Intent(this, SecondActivity::class.java)
        var sencondAct: Intent = Intent(this, TaskAffinyActivity::class.java)
        sencondAct.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
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

    fun testCameraX(view: View) {

    }
}

