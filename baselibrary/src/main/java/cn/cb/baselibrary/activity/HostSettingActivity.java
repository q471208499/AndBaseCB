package cn.cb.baselibrary.activity;

import android.os.Bundle;

import cn.cb.baselibrary.R;

public class HostSettingActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host_setting);
        initBarView();
        findViewById(R.id.host_setting_value);
    }
}