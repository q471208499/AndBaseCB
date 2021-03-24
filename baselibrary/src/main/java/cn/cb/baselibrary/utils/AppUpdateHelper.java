package cn.cb.baselibrary.utils;

import com.cretin.www.cretinautoupdatelibrary.interfaces.AppDownloadListener;
import com.cretin.www.cretinautoupdatelibrary.utils.AppUpdateUtils;
import com.cretin.www.cretinautoupdatelibrary.utils.AppUtils;
import com.liulishuo.filedownloader.FileDownloader;

import java.io.File;

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
                LogHelper.i(TAG, "downloading: " + progress);
            }

            @Override
            public void downloadFail(String msg) {
                LogHelper.i(TAG, "downloadFail: " + msg);
                MyToast.show(msg);
            }

            @Override
            public void downloadComplete(String path) {
                LogHelper.i(TAG, "downloadComplete: " + path);
            }

            @Override
            public void downloadStart() {
                LogHelper.i(TAG, "downloadStart: ");
            }

            @Override
            public void reDownload() {
                LogHelper.i(TAG, "reDownload: ");
            }

            @Override
            public void pause() {
                LogHelper.i(TAG, "pause: ");
            }
        }).checkUpdate(jsonStr);
    }

    private ResponseCallback callback = new ResponseCallback() {
        @Override
        public void onSuccess(Object responseObj) {
            LogHelper.i(TAG, "onSuccess: " + responseObj.toString());
            update(responseObj.toString());
        }

        @Override
        public void onFailure(OkHttpException failure) {
            MyToast.show(failure.geteMsg());
        }
    };

    public static void cleanUpdate() {
        //删除任务中的缓存文件
        FileDownloader.getImpl().clearAllTaskData();
        //删除已经下载好的文件
        AppUtils.delAllFile(new File(AppUtils.getAppRootPath()));
    }
}
