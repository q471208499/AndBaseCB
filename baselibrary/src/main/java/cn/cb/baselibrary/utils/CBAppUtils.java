package cn.cb.baselibrary.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;

import java.util.Locale;

import cn.cb.baselibrary.R;
import es.dmoral.toasty.MyToast;

public class CBAppUtils {

    public static void restartAPP(Context context) {
        launchToAPP(context, context.getPackageName());
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    public static void launchToAPP(Context context, String packageName) {
        PackageManager packageManager = context.getPackageManager();
        if (checkPackInfo(context, packageName)) {
            Intent intent = packageManager.getLaunchIntentForPackage(packageName);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } else {
            MyToast.showL(R.string.toast_not_installed);
        }
    }

    private static boolean checkPackInfo(Context context, String packName) {
        PackageInfo packageInfo = null;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(packName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return packageInfo != null;
    }

    public static void setLocalLanguageZh(Context context) {
        setLocalLanguage(context, Locale.SIMPLIFIED_CHINESE.getLanguage());
    }

    public static void setLocalLanguageEn(Context context) {
        setLocalLanguage(context, Locale.ENGLISH.getLanguage());
    }

    public static void setLocalLanguage(Context context, String language) {
        Configuration configuration = new Configuration();
        Locale locale = new Locale(language);
        configuration.setLocale(locale);
        context.createConfigurationContext(configuration);
    }
}
