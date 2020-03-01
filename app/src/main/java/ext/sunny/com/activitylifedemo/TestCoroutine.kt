package ext.sunny.com.activitylifedemo

import kotlinx.coroutines.*

/**@Annotation <p>协程测试类</p>
 * @Auth  Sunny
 * @date 2020/2/20
 * @Version V1.0.0
 */
class TestCoroutine {

    var uiData:String=""

    //静态代码块
    companion object{

        @JvmStatic
        fun main(args:Array<String>){

            GlobalScope.launch {
                delay(500)
                println("World:我是协程里面打印出来的")

            }
            println("Hello：我是主线程里面的")
            Thread.sleep(1000)





        }
    }

    suspend fun testScope() = coroutineScope {
        val result1 = async(Dispatchers.IO){
            delay(2000)
            uiData="IO获取返回数据01010101"

        }

        withContext(Dispatchers.Main){
            val result2 = result1.await()
            println("获取IO线程返回数据:$result2")

        }


    }


}