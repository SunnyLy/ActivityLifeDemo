package ext.sunny.com.activitylifedemo.aop

import android.Manifest
import android.os.Bundle
import android.view.View
import android.widget.Toast
import ext.sunny.com.activitylifedemo.BaseActivity
import ext.sunny.com.activitylifedemo.R
import ext.sunny.com.activitylifedemo.aop.login.LoginInterceptor

/**@Annotation <p>描述</p>
 * @Auth  Sunny
 * @date 2020/3/7
 * @Version V1.0.0
 */
//@PermissionsNeeded(arrayOf(Manifest.permission.CAMERA), requestCode = 100)
class AopActivity : BaseActivity() {

    //    @PermissionsNeeded(arrayOf(Manifest.permission.CALL_PHONE), requestCode = 101)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aop)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }


    @LoginInterceptor(needLogin = true)
    fun login(view: View) {
        Toast.makeText(this, "登录拦截", Toast.LENGTH_SHORT).show()
    }

}