package cn.cb.andbase.activity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import cn.cb.andbase.R;
import cn.cb.baselibrary.activity.BaseActivity;

public class LoadingActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        initBarView();

        findViewById(R.id.loading_btn).setOnClickListener(clickListener);
        findViewById(R.id.loading_btn2).setOnClickListener(clickListener);
        findViewById(R.id.loading_btn3).setOnClickListener(clickListener);
        findViewById(R.id.loading_btn4).setOnClickListener(clickListener);
        findViewById(R.id.loading_btn5).setOnClickListener(clickListener);
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
                }, 5000);
            } else if (v.getId() == R.id.loading_btn2) {
                //showCountDownTimerDialogS(10);
                showLoading(10);
            } else if (v.getId() == R.id.loading_btn3) {
                showLoading();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dismissLoading();
                    }
                }, 5000);
            } else if (v.getId() == R.id.loading_btn4) {
                showLoading("title");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dismissLoading();
                    }
                }, 5000);
            } else if (v.getId() == R.id.loading_btn5) {
                showLoading(true);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dismissLoading();
                    }
                }, 5000);
            }
        }
    };
}