package cn.cb.baselibrary.net.okhttp3;

import android.util.Log;

import cn.cb.baselibrary.R;
import cn.cb.baselibrary.provider.ABConstant;
import es.dmoral.toasty.MyToast;

public abstract class NetCallback implements ResponseCallback {
    private final String TAG = getClass().getSimpleName();

    @Override
    public void onFailure(OkHttpException failure) {
        switch (failure.geteCode()) {
            case ABConstant.HTTP_NETWORK_ERROR:
                MyToast.show(R.string.net_err);
                return;
            case ABConstant.HTTP_TIMEOUT_ERROR:
                MyToast.show(R.string.net_timeout);
                return;
            case ABConstant.HTTP_OTHER_ERROR:
                MyToast.show(R.string.net_fail);
                return;
        }
        Log.e(TAG, "onFailure: " + failure.geteMsg() + "\n" + failure.getMessage(), failure);
        onSuccess(failure);
    }
}
