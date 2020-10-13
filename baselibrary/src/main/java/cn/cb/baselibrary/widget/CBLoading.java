package cn.cb.baselibrary.widget;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import cn.cb.baselibrary.R;

public class CBLoading extends Handler {
    private final int DIALOG_SHOW = 1129;
    private final int DIALOG_DISMISS = 1128;
    private final int DIALOG_SHOW_TITLE = 1130;
    private final int DIALOG_SHOW_CANCELABLE = 1131;
    private final int DIALOG_COUNT_DOWN_TIMER = 1132;
    private final int DIALOG_COUNT_DOWN_TIMER_TITLE = 1133;

    private String suffix = "";
    private int countDownTimers = 0;
    private final String point = ".";

    private Context mContext;
    private ProgressDialog mLoadingDialog;

    public CBLoading(Context context) {
        mContext = context;
        mLoadingDialog = new ProgressDialog(context);
    }

    public void showLoading() {
        Message message = new Message();
        message.what = DIALOG_SHOW;
        handleMessage(message);
    }

    public void showLoading(String title) {
        Message message = new Message();
        message.what = DIALOG_SHOW_TITLE;
        message.obj = title;
        handleMessage(message);
    }

    public void showLoading(int countDownTimer) {
        Message message = new Message();
        message.what = DIALOG_COUNT_DOWN_TIMER;
        message.arg1 = countDownTimer;
        handleMessage(message);
    }

    public void showLoading(String title, int countDownTimer) {
        Message message = new Message();
        message.what = DIALOG_COUNT_DOWN_TIMER_TITLE;
        message.obj = title;
        message.arg1 = countDownTimer;
        handleMessage(message);
    }

    public void showLoading(boolean cancelable) {
        Message message = new Message();
        message.what = cancelable ? DIALOG_SHOW_CANCELABLE : DIALOG_SHOW;
        handleMessage(message);
    }

    public void dismissLoading() {
        Message message = new Message();
        message.what = DIALOG_DISMISS;
        handleMessage(message);
    }

    @Override
    public void handleMessage(@NonNull Message msg) {
        super.handleMessage(msg);
        if (msg.what == DIALOG_DISMISS) {
            dismiss();
        } else if (msg.what == DIALOG_SHOW) {
            show();
        } else if (msg.what == DIALOG_SHOW_TITLE) {
            String msgStr = msg.obj.toString();
            show(msgStr);
        } else if (msg.what == DIALOG_SHOW_CANCELABLE) {
            show(true);
        } else if (msg.what == DIALOG_COUNT_DOWN_TIMER) {
            show(msg.arg1);
        } else if (msg.what == DIALOG_COUNT_DOWN_TIMER_TITLE) {
            String msgStr = msg.obj.toString();
            show(msg.arg1, msgStr);
        }
    }

    private void show(int countDownTimer) {
        show(countDownTimer, null);
    }

    private void show(int countDownTimer, final String title) {
        countDownTimers = countDownTimer;
        if (countDownTimers == 0) {
            dismiss();
            return;
        }
        String titleShow = title == null ? countDownTimer + " s" : title + " " + countDownTimer + " s";
        show(titleShow, false);
        postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mLoadingDialog.isShowing()) {
                    countDownTimers--;
                    //show(countDownTimers + " s", false);
                    //show(countDownTimers);

                    String titleShow = title == null ? countDownTimers + " s" : title + " " + countDownTimers + " s";
                    show(titleShow, false);
                    show(countDownTimers, title);
                }
            }
        }, 1000);
    }

    private void show() {
        show(false);
    }

    private void show(boolean cancelable) {
        show(null, cancelable);
    }

    private void show(String msg) {
        show(msg, false);
    }

    private void show(String msg, boolean cancelable) {
        if (msg == null) {
            msg = mContext.getString(R.string.loading_txt) + suffix;
            addPoint();
        }
        mLoadingDialog.setCancelable(cancelable);
        mLoadingDialog.setMessage(msg);
        mLoadingDialog.show();
    }

    private void addPoint() {
        postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mLoadingDialog.isShowing()) {
                    if (suffix.length() == 3) {
                        suffix = point;
                    } else {
                        suffix = suffix + point;
                    }
                    String msg = mContext.getString(R.string.loading_txt) + suffix;
                    mLoadingDialog.setMessage(msg);
                    addPoint();
                }
            }
        }, 800);
    }

    private void dismiss() {
        suffix = "";
        if (mLoadingDialog == null) return;
        if (mLoadingDialog.isShowing()) mLoadingDialog.dismiss();
    }
}
