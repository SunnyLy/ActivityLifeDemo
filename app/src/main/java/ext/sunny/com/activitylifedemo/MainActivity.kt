package ext.sunny.com.activitylifedemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        printLog("MainActivity#onCreate()")
        setContentView(R.layout.activity_main)

    }

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

