package cn.cb.andbase.activity;

import android.os.Bundle;
import android.util.Log;

import com.cretin.www.cretinautoupdatelibrary.interfaces.AppDownloadListener;
import com.cretin.www.cretinautoupdatelibrary.utils.AppUpdateUtils;

import cn.cb.andbase.R;
import cn.cb.baselibrary.activity.BaseActivity;
import cn.cb.baselibrary.net.okhttp3.OkHttpException;
import cn.cb.baselibrary.net.okhttp3.RequestMode;
import cn.cb.baselibrary.net.okhttp3.ResponseCallback;
import cn.cb.baselibrary.utils.AppUpdateHelper;
import es.dmoral.toasty.MyToast;

public class UpdateActivity extends BaseActivity {
    private final String TAG = getClass().getSimpleName();
    protected String updateInfoUrl = "http://www.xinchuang-link.cn:8321/appUpdate/cn.hk.easyscribebao/update.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        initBarView();
        //getUpdateInfo();
        new AppUpdateHelper(updateInfoUrl).getUpdateInfo();
    }

    private void update(String jsonStr) {
        AppUpdateUtils.getInstance().addAppDownloadListener(new AppDownloadListener() {
            @Override
            public void downloading(int progress) {
                Log.i(TAG, "downloading: " + progress);
            }

            @Override
            public void downloadFail(String msg) {
                Log.i(TAG, "downloadFail: " + msg);
                MyToast.show(msg);
            }

            @Override
            public void downloadComplete(String path) {
                Log.i(TAG, "downloadComplete: " + path);
            }

            @Override
            public void downloadStart() {
                Log.i(TAG, "downloadStart: ");
            }

            @Override
            public void reDownload() {
                Log.i(TAG, "reDownload: ");
            }

            @Override
            public void pause() {
                Log.i(TAG, "pause: ");
            }
        }).checkUpdate(jsonStr);
    }

    private void getUpdateInfo() {
        RequestMode.getRequest(updateInfoUrl, null, callback, null);
    }

    private ResponseCallback callback = new ResponseCallback() {
        @Override
        public void onSuccess(Object responseObj) {
            Log.i(TAG, "onSuccess: " + responseObj.toString());
            update(responseObj.toString());
        }

        @Override
        public void onFailure(OkHttpException failure) {
            MyToast.show(failure.geteMsg());
        }
    };
}