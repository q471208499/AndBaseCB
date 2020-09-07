package cn.cb.andbase;

import android.os.Bundle;
import android.util.Log;

import com.cretin.www.cretinautoupdatelibrary.interfaces.AppDownloadListener;
import com.cretin.www.cretinautoupdatelibrary.utils.AppUpdateUtils;

import cn.cb.baselibrary.activity.BaseActivity;
import cn.cb.baselibrary.net.okhttp3.OkHttpException;
import cn.cb.baselibrary.net.okhttp3.RequestMode;
import cn.cb.baselibrary.net.okhttp3.ResponseCallback;
import es.dmoral.toasty.MyToast;

public class UpdateActivity extends BaseActivity {
    private final String TAG = getClass().getSimpleName();
    protected String updateInfoUrl = "http://192.168.137.1:8080/proxy/appUpdate/applicationId/update.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        initBarView();
        getUpdateInfo();
    }

    private void update(String jsonStr) {
        //String jsonData = "{\"versionCode\": 25,\"isForceUpdate\": 1,\"preBaselineCode\": 24,\"versionName\": \"v2.3.1\",\"downurl\": \"http://192.168.1.88:8080/MIServer/mobile/app.apk\",\"updateLog\": \"1、优化细节和体验，更加稳定\n2、引入大量优质用户\r\n3、修复已知bug\n4、风格修改\",\"size\": \"31338250\",\"hasAffectCodes\": \"1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24\"}";
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