package ext.sunny.com.activitylifedemo.component.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * @Annotation <p>描述</p>
 * @Auth Sunny
 * @date 2020/11/3
 * @Version V1.0.0
 */
public class SunnyBroadcast  extends BroadcastReceiver {

    public SunnyBroadcast() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("sunny","SunnyBroadcast$onReceivee");
    }
}
