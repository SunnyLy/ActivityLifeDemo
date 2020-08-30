package ext.sunny.com.app2.xposed;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.text.TextUtils;
import android.widget.Toast;

import java.lang.reflect.Field;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import ext.sunny.com.app2.R;

/**
 * @Annotation <p>描述</p>
 * @Auth Sunny
 * @date 2020/7/12
 * @Version V1.0.0
 */
public class SunnyXposed implements IXposedHookLoadPackage {
    private static final String LOG_TAG = "======Sunny:Xposed:====";

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        //只Hook指定应用
        //这里以AOP切面编程的一个Demo为例,包名:shixin.aopdemo
        String desPackageName = lpparam.packageName;
        XposedBridge.log(LOG_TAG + desPackageName);
        if (!TextUtils.isEmpty(desPackageName) && desPackageName.equalsIgnoreCase("shixin.aopdemo")) {
            ApplicationInfo applicationInfo = lpparam.appInfo;
            XposedBridge.log(LOG_TAG + "===comein");
            //开始Hook指定类中的指定方法
          /*  Class clz = Class.forName("shixin.aopdemo.activity.MainActivity");
            Field field = XposedHelpers.findField(clz, "TAG");
            String test = new String();
            field.set(test, "Xposed.Sunny");

            field.setAccessible(true);
            XposedBridge.log(LOG_TAG + ":" + field.get("TAG"));*/


            XposedHelpers.findAndHookMethod("shixin.aopdemo.activity.MainActivity", lpparam.classLoader,
                    "testLog", int.class, new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            //开始Hook前
                            String methodName = param.method.getName();
                            XposedBridge.log(LOG_TAG + methodName);
                            param.args[0] = 1000;
                        }

                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {

                            XposedBridge.log(LOG_TAG + "...." + param.args[0]);

                            XposedBridge.log(LOG_TAG + "....参数修改完毕：" + param.args[0]);
                            param.setResult(true);
                        }
                    });
        }
    }
}
