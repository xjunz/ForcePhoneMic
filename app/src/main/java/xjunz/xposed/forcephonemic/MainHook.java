package xjunz.xposed.forcephonemic;

import android.media.MediaRecorder;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class MainHook implements IXposedHookLoadPackage {

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        XposedHelpers.findAndHookMethod("android.media.MediaRecorder", lpparam.classLoader, "setAudioSource", int.class, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                super.beforeHookedMethod(param);
                param.args[0] = MediaRecorder.AudioSource.CAMCORDER;
                XposedBridge.log("media recorder api hooked");
            }

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
            }
        });
        XposedHelpers.findAndHookConstructor("android.media.AudioRecord", lpparam.classLoader, int.class, int.class, int.class, int.class, int.class, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                super.beforeHookedMethod(param);
                param.args[0] = MediaRecorder.AudioSource.CAMCORDER;
                XposedBridge.log("audio record constructor hooked");
            }
        });

        XposedHelpers.findAndHookMethod("android.media.AudioRecord.Builder", lpparam.classLoader, "setAudioSource", int.class, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                super.beforeHookedMethod(param);
                param.args[0] = MediaRecorder.AudioSource.CAMCORDER;
                XposedBridge.log("audio record builder api hooked");
            }
        });
    }
}
