package ext.sunny.com.activitylifedemo.xposed;

import android.content.pm.ApplicationInfo;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
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
        XposedBridge.log("=========Sunny:Loaded APP:=====" + lpparam.packageName);

        if (lpparam.packageName.equalsIgnoreCase("shixin.aopdemo")) {
            ApplicationInfo applicationInfo = lpparam.appInfo;

            XposedHelpers.findAndHookMethod("shixin.aopdemo.activity.MainActivity", lpparam.classLoader,
                    "testNeedLoginEL2LoginActivity", "", new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            XposedBridge.log("============Sunny:beforeHookedMethod:" + param.method.getName());
                            // super.beforeHookedMethod(param);
                        }

                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            param.setThrowable(new NullPointerException("Xposed NullPointerException"));
                        }
                    });
        }


    }


}
