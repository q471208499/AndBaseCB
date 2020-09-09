package cn.cb.andbase.activity;

import android.os.Bundle;

import java.util.List;

import cn.cb.andbase.R;
import cn.cb.baselibrary.activity.BaseActivity;

public class CrashActivity extends BaseActivity {
    private List<String> list = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crash);
        initBarView();
        list.add("AAA");
    }
}