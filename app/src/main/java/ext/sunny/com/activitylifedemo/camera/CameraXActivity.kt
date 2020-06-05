package ext.sunny.com.activitylifedemo.camera

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.SparseArray
import android.view.TextureView
import android.widget.Toast
import androidx.core.content.ContextCompat
import ext.sunny.com.activitylifedemo.BaseActivity
import ext.sunny.com.activitylifedemo.R

/**@Annotation <p>学习CameraX</p>
 * @Auth  Sunny
 * @date 2020/3/15
 * @Version V1.0.0
 */
class CameraXActivity : BaseActivity() {

    private val REQURE_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
    private var REQUEST_CODE: Int = 100;
    lateinit var textureView: TextureView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camerax)
        textureView = findViewById(R.id.camerax_tv)

        if (allPermissionsGranted()) {
            textureView.post { startCamera() }
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(REQURE_PERMISSIONS, REQUEST_CODE)
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE) {
            if (allPermissionsGranted()) {
                textureView.post { startCamera() }
            } else {
                Toast.makeText(this, "请允许所有权限", Toast.LENGTH_SHORT).show()
            }
        }
    }

    /**
     * 开启相机
     */
    private fun startCamera() {


    }

    /**
     * 判断所有的权限是否都被授权
     */
    private fun allPermissionsGranted() = REQURE_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_GRANTED
    }
}