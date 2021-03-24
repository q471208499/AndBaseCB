package cn.cb.baselibrary.utils;

import android.util.Log;

import cn.cb.baselibrary.BaseApplication;

public class LogHelper {

    public static int v(String tag, String msg) {
        if (BaseApplication.DEBUG)
            return Log.v(tag, msg);
        return 0;
    }

    public static int v(String tag, String msg, Throwable tr) {
        if (BaseApplication.DEBUG)
            return Log.v(tag, msg, tr);
        return 0;
    }

    public static int d(String tag, String msg) {
        if (BaseApplication.DEBUG)
            return Log.d(tag, msg);
        return 0;
    }

    public static int d(String tag, String msg, Throwable tr) {
        if (BaseApplication.DEBUG)
            return Log.d(tag, msg, tr);
        return 0;
    }

    public static int i(String tag, String msg) {
        if (BaseApplication.DEBUG)
            return LogHelper.i(tag, msg);
        return 0;
    }

    public static int i(String tag, String msg, Throwable tr) {
        if (BaseApplication.DEBUG)
            return LogHelper.i(tag, msg, tr);
        return 0;
    }

    public static int w(String tag, String msg) {
        if (BaseApplication.DEBUG)
            return Log.w(tag, msg);
        return 0;
    }

    public static int w(String tag, String msg, Throwable tr) {
        if (BaseApplication.DEBUG)
            return Log.w(tag, msg, tr);
        return 0;
    }

    public static int w(String tag, Throwable tr) {
        if (BaseApplication.DEBUG)
            return Log.w(tag, tr);
        return 0;
    }

    public static int e(String tag, String msg) {
        if (BaseApplication.DEBUG)
            return Log.e(tag, msg);
        return 0;
    }

    public static int e(String tag, String msg, Throwable tr) {
        if (BaseApplication.DEBUG)
            return Log.e(tag, msg, tr);
        return 0;
    }
}
