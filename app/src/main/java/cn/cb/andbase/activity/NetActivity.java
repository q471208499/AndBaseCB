package cn.cb.andbase.activity;

import android.os.Bundle;

import cn.cb.andbase.R;
import cn.cb.baselibrary.activity.BaseActivity;
import cn.cb.baselibrary.net.okhttp3.NetCallback;
import cn.cb.baselibrary.net.okhttp3.RequestMode;
import cn.cb.baselibrary.utils.LogHelper;

public class NetActivity extends BaseActivity {

    private final String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net);
        initBarView();
        RequestMode.getRequest("https://www.baidu.com", null, netCallback, null);
    }

    private NetCallback netCallback = new NetCallback() {
        @Override
        public void onSuccess(Object responseObj) {
            LogHelper.i(TAG, "onSuccess: " + responseObj);
        }
    };
}