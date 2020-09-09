package cn.cb.baselibrary.crash.reflection;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;

import java.lang.reflect.Method;

public class Reflection {

    static {
        System.loadLibrary("free-reflection");
    }

    private static native int unsealNative(int targetSdkVersion);

    private static int UNKNOWN = -9999;

    private static final int ERROR_SET_APPLICATION_FAILED = -20;

    private static int unsealed = UNKNOWN;

    public static int unseal(Context context) {
        if (Build.VERSION.SDK_INT < 28) {
            // Below Android P, ignore
            return 0;
        }

        if (context == null) {
            return -10;
        }

        ApplicationInfo applicationInfo = context.getApplicationInfo();
        int targetSdkVersion = applicationInfo.targetSdkVersion;

        synchronized (Reflection.class) {
            if (unsealed == UNKNOWN) {
                unsealed = unsealNative(targetSdkVersion);
                if (unsealed >= 0) {
                    try {
                        @SuppressLint("SoonBlockedPrivateApi") Method setHiddenApiEnforcementPolicy = ApplicationInfo.class.getDeclaredMethod("setHiddenApiEnforcementPolicy", int.class);
                        setHiddenApiEnforcementPolicy.invoke(applicationInfo, 0);
                    } catch (Throwable e) {
                        e.printStackTrace();
                        unsealed = ERROR_SET_APPLICATION_FAILED;
                    }
                }
            }
        }
        return unsealed;
    }
}

