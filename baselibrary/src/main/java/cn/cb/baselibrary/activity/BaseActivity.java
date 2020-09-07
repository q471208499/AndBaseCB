package cn.cb.baselibrary.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.lang.reflect.Method;

import cn.cb.baselibrary.R;
import es.dmoral.toasty.MyToast;

public class BaseActivity extends AppCompatActivity {
    protected Toolbar toolbar;
    //private SweetAlertDialog mLoadingDialog;
    private ProgressDialog mLoadingDialog;
    private final int DIALOG_DISMISS = 1128;
    private final int DIALOG_SHOW = 1129;
    private final int DIALOG_SET_TITLE = 1130;
    private final int DIALOG_COUNT_DOWN_TIMER = 1131;

    protected final String INTENT_EXTRA_RESULT_STR = "INTENT_EXTRA_RESULT_STR";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
    }

    protected void hideInput() {
        InputMethodManager manager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    protected void initBarView() {
        toolbar = findViewById(R.id.toolbar);
        String title = getIntent().getStringExtra(Intent.EXTRA_TITLE);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        if (!TextUtils.isEmpty(title)) {
            if (getSupportActionBar() != null)
                getSupportActionBar().setTitle(title);
        }
    }

    protected void setBarTitleTextCentre() {
        //获取到屏幕的宽度
        Point point = new Point();
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getSize(point);
        int width = point.x;

        Paint paint = new TextPaint();
        float textWidth = paint.measureText(getSupportActionBar().getTitle().toString());
        float f = (width - textWidth) / 2 - 260;//1080分辨率下正常

        if (toolbar == null) {
            toolbar = findViewById(R.id.toolbar);
            if (toolbar == null) {
                MyToast.show("no toolbar for this activity");
                return;
            }
        }
        toolbar.setTitleMarginStart((int) f);
    }

    /**
     * 暴力调用，使menu加载图标
     *
     * @param featureId
     * @param menu
     * @return
     */
    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if (menu != null) {
            if (menu.getClass().getSimpleName().equalsIgnoreCase("MenuBuilder")) {
                try {
                    Method method = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    method.setAccessible(true);
                    method.invoke(menu, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return super.onMenuOpened(featureId, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void restartAPP() {
        launchToAPP(getPackageName());
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    protected void launchToAPP(String packageName) {
        PackageManager packageManager = getPackageManager();
        if (checkPackInfo(packageName)) {
            Intent intent = packageManager.getLaunchIntentForPackage(packageName);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else {
            MyToast.showL(R.string.toast_not_installed);
        }
    }

    private boolean checkPackInfo(String packname) {
        PackageInfo packageInfo = null;
        try {
            packageInfo = getPackageManager().getPackageInfo(packname, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return packageInfo != null;
    }

    protected void showLoading() {
        Message message = new Message();
        message.what = DIALOG_SHOW;
        handler.handleMessage(message);
    }

    protected void showCountDownTimerDialogS(int second) {
        Message message = new Message();
        message.what = DIALOG_COUNT_DOWN_TIMER;
        message.obj = second + " s";
        message.arg1 = second;
        handler.handleMessage(message);
    }

    protected void showLoading(String title) {
        Message message = new Message();
        message.what = DIALOG_SHOW;
        message.obj = title;
        handler.handleMessage(message);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull final Message msg) {
            super.handleMessage(msg);
            if (msg.what == DIALOG_DISMISS) {
                if (mLoadingDialog.isShowing()) mLoadingDialog.dismiss();
            } else if (msg.what == DIALOG_SHOW) {
                mLoadingDialog = new ProgressDialog(BaseActivity.this);
                mLoadingDialog.setCancelable(false);
                mLoadingDialog.show();
            } else if (msg.what == DIALOG_SET_TITLE) {
                mLoadingDialog = new ProgressDialog(BaseActivity.this);
                mLoadingDialog.setCancelable(false);
                mLoadingDialog.setTitle(msg.obj.toString());
                mLoadingDialog.show();
            } else if (msg.what == DIALOG_COUNT_DOWN_TIMER) {
                if (mLoadingDialog == null) {
                    mLoadingDialog = new ProgressDialog(BaseActivity.this);
                    mLoadingDialog.setCancelable(false);
                }
                mLoadingDialog.setTitle(msg.obj.toString());
                mLoadingDialog.show();
                if (msg.arg1 == 0) {
                    if (mLoadingDialog.isShowing()) mLoadingDialog.dismiss();
                    return;
                }
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        showCountDownTimerDialogS(msg.arg1 - 1);
                    }
                }, 1000);
            }
        }
    };

    protected void dismissLoading() {
        Message message = new Message();
        message.what = DIALOG_DISMISS;
        handler.handleMessage(message);
    }

    protected void setLoadingCancelable(boolean flag) {
        mLoadingDialog.setCancelable(flag);
    }

    protected void showFinishDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(BaseActivity.this);
        builder.setTitle(R.string.dialog_prompt);
        builder.setMessage(R.string.dialog_exit_software);
        builder.setPositiveButton(R.string.dialog_exit, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setNegativeButton(R.string.dialog_cancel, null);
        builder.show();
    }
}
