package cn.cb.baselibrary.net.okhttp3;

import android.util.Log;

import cn.cb.baselibrary.R;
import cn.cb.baselibrary.provider.ABConstant;
import es.dmoral.toasty.MyToast;

public abstract class NetCallback implements ResponseCallback {
    private final String TAG = getClass().getSimpleName();

    @Override
    public void onFailure(OkHttpException failure) {
        if (failure.geteCode() == ABConstant.HTTP_NETWORK_ERROR) {
            MyToast.show(R.string.net_err);
        } else if (failure.geteCode() == ABConstant.HTTP_TIMEOUT_ERROR) {
            MyToast.show(R.string.net_timeout);
        } else if (failure.geteCode() == ABConstant.HTTP_OTHER_ERROR) {
            MyToast.show(R.string.net_fail);
        } else {
            MyToast.show(R.string.server_error);
        }
        Log.e(TAG, "onFailure: " + failure.geteMsg() + "\n" + failure.getMessage(), failure);
        onSuccess(failure);
    }
}
