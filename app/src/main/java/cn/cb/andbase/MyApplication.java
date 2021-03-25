package cn.cb.andbase;

import cn.cb.baselibrary.BaseApplication;
import cn.cb.baselibrary.utils.LogHelper;
import es.dmoral.toasty.MyToast;

public class MyApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        MyToast.init(this, true, true);
        DEBUG = BuildConfig.DEBUG;
        LogHelper.i("aaaa", MyApplication.FIRST + "");
    }
}
