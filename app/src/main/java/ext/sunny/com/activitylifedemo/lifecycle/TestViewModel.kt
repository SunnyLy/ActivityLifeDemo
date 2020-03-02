package ext.sunny.com.activitylifedemo.lifecycle

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**@Annotation <p>描述</p>
 * @Auth  Sunny
 * @date 2020/3/1
 * @Version V1.0.0
 */
class TestViewModel: ViewModel() {
    private var username:String =""
    private var name:MutableLiveData<String>?=MutableLiveData()

    fun setUserName(name:String){
        this.username = name
        this.name!!.value = name
    }

    fun getUserName():String{
        var result:String = this.name!!.value.toString()
        return result
    }

    fun getLiveData():MutableLiveData<String>?{
        return this.name
    }
}