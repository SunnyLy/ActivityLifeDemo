package ext.sunny.com.activitylifedemo.net.okhttp

import android.util.Log
import ext.sunny.com.activitylifedemo.callback.INetCallback
import okhttp3.*
import java.io.IOException

/**@Annotation <p>OkHttp请求</p>
 * @Auth  Sunny
 * @date 2020/3/3
 * @Version V1.0.0
 */
class OkHttpService {


    constructor() {
        if (null == okHttpClient) {
            okHttpClient = OkHttpClient()
        }

        requestBuilder = Request.Builder()
    }


    /***
     * 静态代码块：
     * 静态变量、静态方法都写在里面
     */
    companion object {
        //玩Android BaseUrl
        private var baseUrl: String = "https://www.wanandroid.com"
        private var mInstace: OkHttpService? = null

        var requestBuilder: Request.Builder? = null
        private var okHttpClient: OkHttpClient? = null
        /**
         * 单例
         */
        @Synchronized
        fun getInstance(): OkHttpService {
            if (null == mInstace) {
                mInstace = OkHttpService()
            }
            return mInstace as OkHttpService
        }



    }

    /**
     * Get请求,获取返回公众号列表
     */
    fun getChaptersList(url: String,callback: INetCallback<String>): String {
        var request = requestBuilder!!.url("$baseUrl/$url").build()
        var getCall: Call = okHttpClient!!.newCall(request)
        var result: String = ""

        //同步调用
       /* var response: Response = getCall!!.execute().apply {
            if (isSuccessful) {
                result = body.toString()
                Log.e("xxxx", "请求返回结果:$result")
                callback.onSuccess(result)

            }

        }*/

        //利用enqueue异步调用
        getCall!!.enqueue(object:Callback{
            override fun onFailure(call: Call, e: IOException) {
                callback.onError("请求失败:${e.message}",-1)
            }

            override fun onResponse(call: Call, response: Response) {
                response.apply {
                    if (isSuccessful){
                        result = body!!.string()
                        Log.e("xxxx", "请求成功:$result")
                        callback.onSuccess(result)
                    }
                }
            }

        })

        return result

    }


}