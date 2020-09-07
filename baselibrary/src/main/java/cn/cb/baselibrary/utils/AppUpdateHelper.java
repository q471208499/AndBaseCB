package cn.cb.baselibrary.utils;

import android.util.Log;

import com.cretin.www.cretinautoupdatelibrary.interfaces.AppDownloadListener;
import com.cretin.www.cretinautoupdatelibrary.utils.AppUpdateUtils;

import cn.cb.baselibrary.net.okhttp3.OkHttpException;
import cn.cb.baselibrary.net.okhttp3.RequestMode;
import cn.cb.baselibrary.net.okhttp3.ResponseCallback;
import es.dmoral.toasty.MyToast;

public class AppUpdateHelper {
    private final String TAG = getClass().getSimpleName();
    private String updateInfoUrl;

    public AppUpdateHelper(String updateInfoUrl) {
        this.updateInfoUrl = updateInfoUrl;
    }

    public void getUpdateInfo() {
        RequestMode.getRequest(updateInfoUrl, null, callback, null);
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
