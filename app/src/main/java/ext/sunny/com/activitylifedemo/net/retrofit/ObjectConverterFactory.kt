package ext.sunny.com.activitylifedemo.net.retrofit

import com.google.gson.Gson
import com.google.gson.TypeAdapter
import com.google.gson.reflect.TypeToken
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

/**@Annotation <p>针对RespnseBody返回数据格式成指定对象转换工厂</p>
 * @Auth  Sunny
 * @date 2020/3/4
 * @Version V1.0.0
 */
class ObjectConverterFactory(gson: Gson) : Converter.Factory() {
    private var gson: Gson = gson


    companion object {
        private var mInstance: ObjectConverterFactory? = null
        @Synchronized
        fun create(): ObjectConverterFactory {
            if (mInstance == null) {
                mInstance = ObjectConverterFactory(Gson())
            }
            return mInstance as ObjectConverterFactory

        }
    }


    /**
     * 将请求返回结果转换为String返回
     */
    override fun responseBodyConverter(
        type: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *>? {

        var adapter: TypeAdapter<out Any>? = gson.getAdapter(TypeToken.get(type))

        return StringResponseBodeyConverter(adapter!!)
    }


    /**
     * 将ResponseBody 转换为String输出
     */
    public class StringResponseBodeyConverter(adapter: TypeAdapter<out Any>) :
        Converter<ResponseBody, Any> {
        private final var adapter: TypeAdapter<out Any> = adapter

        override fun convert(value: ResponseBody): Any? {
            var temp: String = String(value.bytes())
            return adapter.fromJson(temp)
        }
    }

}
