package ext.sunny.com.activitylifedemo

/**@Annotation <p>描述</p>
 * @Auth  Sunny
 * @date 2020/2/25
 * @Version V1.0.0
 */
class TestLet {

    companion object {
        @JvmStatic
        fun main(ars: Array<String>) {

            var userInfoBean = UserInfoBean()
            var result = userInfoBean?.run {
                userName = "Sunny"
                addr = "ChangSha"
                System.out.println("userName:$userName\naddress:$addr")
                77777
            }

            System.out.println("result:$result")

            var result2 = userInfoBean?.apply {
                userName = "Sunny2"
                addr = "ShenZhen"

            }

            with(result2){
                System.out.println("result2:userName:$userName\naddr:$addr")
            }


        }
    }
}