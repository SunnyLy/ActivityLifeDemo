package ext.sunny.com.activitylifedemo.callback

/**@Annotation <p>网络请求回调接口</p>
 * @Auth  Sunny
 * @date 2020/3/3
 * @Version V1.0.0
 */
interface INetCallback<T> {
    fun onSuccess(result:T)
    fun onError(msg:String?,errorCode:Int)
}