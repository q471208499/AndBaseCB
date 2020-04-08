package cn.cb.andbase;

import android.os.Bundle;

import cn.cb.baselibrary.activity.BaseActivity;


public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initBarView();
    }
}
