package cn.cb.andbase;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import cn.cb.baselibrary.activity.BaseActivity;

public class LoadingActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        initBarView();

        findViewById(R.id.loading_btn).setOnClickListener(clickListener);
        findViewById(R.id.loading_btn2).setOnClickListener(clickListener);
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.loading_btn) {
                showLoading();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dismissLoading();
                    }
                }, 10000);
            } else if (v.getId() == R.id.loading_btn2) {
                showCountDownTimerDialogS(10);
            }
        }
    };
}