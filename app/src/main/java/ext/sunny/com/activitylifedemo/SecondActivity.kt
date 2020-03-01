package ext.sunny.com.activitylifedemo

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import ext.sunny.com.activitylifedemo.services.PersonService
import kotlinx.coroutines.*
import kotlin.coroutines.Continuation

/**@Annotation <p>描述</p>
 * @Auth  Sunny
 * @date 2020/2/16
 * @Version V1.0.0
 */
class SecondActivity : BaseActivity() {

    lateinit var tvInfo:TextView
    lateinit var tvReceiver:TextView
    lateinit var btnAIDL:Button
    lateinit var btnSend:Button
    lateinit var etUserName:EditText


    var personService:PersonService?=null
    var iPerson: IPerson?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        printLog("SecondActivity#onCreate()")
        setContentView(R.layout.activity_second)
        tvInfo = findViewById(R.id.tv_info)
        btnAIDL = findViewById(R.id.btn_aidl)
        btnSend = findViewById(R.id.btn_send)
        etUserName = findViewById(R.id.editText)
        tvReceiver = findViewById(R.id.tv_receiver)
        personService = PersonService()

//        GlobalScope.launch{ }
       // testCoroutine()





    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return super.onTouchEvent(event)
    }

    fun test1(){
        printLog("没有suspend关键字修饰的Test1()")
    }

    suspend fun test2() = coroutineScope{
        printLog("有suspend关键字修饰的Test2()")
        val a = async {
            6
            "Value1"
            tvInfo.text="设置文字"
            7
            return@async tvInfo.text


        }

        val b = async {
            7
            "Value2"
        }

        var result = a.await()
        printLog(result.toString())

    }



     fun testCoroutine(){
        val testJob = GlobalScope.launch(Dispatchers.IO){
            test1()
            test2()
        }


    }

    override fun onStart() {
        super.onStart()
        printLog("SecondActivity#onStart()")
    }

    override fun onRestart() {
        super.onRestart()
        printLog("SecondActivity#onRestart()")
    }

    override fun onResume() {
        super.onResume()
        printLog("SecondActivity#onResume()")
        testCoroutine()
    }

    override fun onPause() {
        super.onPause()
        printLog("SecondActivity#onPause()")
    }

    override fun onStop() {
        super.onStop()
        printLog("SecondActivity#onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        printLog("SecondActivity#onDestroy()")
    }

    /***
     * 开始绑定Service
     */
    fun startAIDL(view: View) {
        var aidlIntent = Intent(this,personService!!::class.java)
        this.bindService(aidlIntent,object :ServiceConnection{
            override fun onServiceDisconnected(name: ComponentName?) {
                iPerson=null
            }

            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                iPerson = IPerson.Stub.asInterface(service)
            }

        }, Context.BIND_AUTO_CREATE)
    }

    /**
     * 调用aidl向服务端发送信息
     */
    fun sendInfo(view: View) {

        iPerson!!.name = "Sunny哥:${etUserName.text.trim()}"

        var info:String = iPerson!!.name

        tvReceiver!!.text = "接收到的信息：$info"


    }
}