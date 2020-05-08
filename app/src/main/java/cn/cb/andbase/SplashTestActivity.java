package cn.cb.andbase;


import android.os.Bundle;

import cn.cb.baselibrary.activity.SplashActivity;

public class SplashTestActivity extends SplashActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cls = MainActivity.class;
        mContentView.setImageResource(R.mipmap.ic_launcher);
    }
}
