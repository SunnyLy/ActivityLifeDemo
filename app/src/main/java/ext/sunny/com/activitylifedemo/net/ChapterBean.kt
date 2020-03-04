package ext.sunny.com.activitylifedemo.net

import android.os.Parcel
import android.os.Parcelable

/**@Annotation <p>公众号数据</p>
 * @Auth  Sunny
 * @date 2020/3/4
 * @Version V1.0.0
 */
class ChapterBean() :Parcelable {
    var id:Int = 0
    var courseId:Int = 0
    var name:String = ""

    constructor(parcel: Parcel) : this() {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ChapterBean> {
        override fun createFromParcel(parcel: Parcel): ChapterBean {
            return ChapterBean(parcel)
        }

        override fun newArray(size: Int): Array<ChapterBean?> {
            return arrayOfNulls(size)
        }
    }
}