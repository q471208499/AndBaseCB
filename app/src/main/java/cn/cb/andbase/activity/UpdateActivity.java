package cn.cb.andbase.activity;

import android.os.Bundle;

import cn.cb.andbase.R;
import cn.cb.baselibrary.activity.BaseActivity;
import cn.cb.baselibrary.utils.AppUpdateHelper;

public class UpdateActivity extends BaseActivity {
    private final String TAG = getClass().getSimpleName();
    //protected String updateInfoUrl = "http://www.xinchuang-link.cn:8321/appUpdate/cn.hk.easyscribebao/update.txt";
    protected String updateInfoUrl = "http://121.15.209.245:9002/appUpdate/cn.hk.btctrlreading/update.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        initBarView();
        new AppUpdateHelper(updateInfoUrl).getUpdateInfo();
    }
}