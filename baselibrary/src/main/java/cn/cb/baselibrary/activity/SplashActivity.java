package cn.cb.baselibrary.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;

import cn.cb.baselibrary.R;
import es.dmoral.toasty.MyToast;

public class SplashActivity extends BaseActivity {
    private final String TAG = "SplashActivity";

    protected ImageView mContentView;
    protected Class<?> cls;
    protected long delayMillis = 1000;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (cls == null) {
                MyToast.errorL(R.string.toast_intent_cls);
                Log.e(TAG, "intent class is null, please init \"cls\".");
                return;
            }
            Intent intent = new Intent(SplashActivity.this, cls);
            //intent.putExtra(Intent.EXTRA_TITLE, "首次启动信息录入");
            startActivity(intent);
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalsh);
        mContentView = findViewById(R.id.fullscreen_content);
        hide();
    }

    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

    @Override
    protected void onResume() {
        super.onResume();
        handler.sendEmptyMessageDelayed(0, delayMillis);
    }
}
