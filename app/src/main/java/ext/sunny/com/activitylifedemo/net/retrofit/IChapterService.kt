package ext.sunny.com.activitylifedemo.net.retrofit

import ext.sunny.com.activitylifedemo.net.ChapterBean
import retrofit2.Call
import retrofit2.http.GET

/**@Annotation <p>利用Retrofit实现网络请求</p>
 * @Auth  Sunny
 * @date 2020/3/4
 * @Version V1.0.0
 */
interface IChapterService {


    @GET("/wxarticle/chapters/json")
    fun getChapterList(): Call<String>
}