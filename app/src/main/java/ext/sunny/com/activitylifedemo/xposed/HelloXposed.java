package ext.sunny.com.activitylifedemo.xposed;

import android.content.pm.ApplicationInfo;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * @Annotation <p>用Xposed框架来Hook 新安装apk的包名</p>
 * @Auth Sunny
 * @date 2020/6/2
 * @Version V1.0.0
 */
public class HelloXposed implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        XposedBridge.log("=========Loaded APP:=====" + lpparam.packageName );

        ApplicationInfo applicationInfo = lpparam.appInfo;
    }
}
