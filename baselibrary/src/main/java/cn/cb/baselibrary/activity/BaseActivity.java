package cn.cb.baselibrary.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.lang.reflect.Method;
import java.util.Objects;

import cn.cb.baselibrary.R;
import cn.cb.baselibrary.utils.LogHelper;
import cn.cb.baselibrary.widget.CBLoading;

public class BaseActivity extends AppCompatActivity {
    protected Toolbar toolbar;
    private CBLoading mLoadingDialog;
    private final String TAG = getClass().getSimpleName();

    protected final String INTENT_EXTRA_RESULT_STR = "INTENT_EXTRA_RESULT_STR";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        mLoadingDialog = new CBLoading(this);
    }

    /**
     * 修改状态栏颜色
     *
     * @param colorId
     */
    protected void setStatusBarColor(int colorId) {
        Window window = getWindow();
        window.setStatusBarColor(getResources().getColor(colorId));
    }

    protected void hideInput() {
        hideInput(getCurrentFocus().getWindowToken());
    }

    protected void hideInput(View view) {
        hideInput(view.getWindowToken());
    }

    private void hideInput(IBinder windowToken) {
        if (windowToken == null) return;
        InputMethodManager manager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(windowToken, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    protected void initBarView() {
        toolbar = findViewById(R.id.toolbar);
        String title = getIntent().getStringExtra(Intent.EXTRA_TITLE);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
            toolbar.setBackgroundResource(R.color.colorPrimary);
        }
        if (!TextUtils.isEmpty(title)) {
            if (getSupportActionBar() != null)
                getSupportActionBar().setTitle(title);
        }
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }*/
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            if (getSupportActionBar() != null && getSupportActionBar().isShowing()) {
                getWindow().setStatusBarColor(getColor(R.color.colorPrimary));
            }
        }
    }

    /**
     * 设置bar标题居中：使用居中属性
     */
    protected void setBarTitleTextCentre() {
        if (toolbar == null) {
            toolbar = findViewById(R.id.toolbar);
            if (toolbar == null) {
                LogHelper.e(TAG, "no toolbar for this activity");
                return;
            }
        }

        for (int i = 0; i < toolbar.getChildCount(); i++) {
            View view = toolbar.getChildAt(i);
            if (view instanceof TextView) {
                TextView textView = (TextView) view;
                String title = getIntent().getStringExtra(Intent.EXTRA_TITLE);
                if (title == null) {
                    LogHelper.e(TAG, "未使用Intent设置标题，无法居中！");
                    return;
                }
                if (title.equals(textView.getText())) {
                    textView.setGravity(Gravity.CENTER);
                    Toolbar.LayoutParams params = new Toolbar.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.MATCH_PARENT);
                    params.gravity = Gravity.CENTER;
                    textView.setLayoutParams(params);
                }
            }
        }
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

    protected void showLoading() {
        mLoadingDialog.showLoading();
    }

    public void showLoading(int countDownTimer) {
        mLoadingDialog.showLoading(countDownTimer);
    }

    public void showLoading(int countDownTimer, String title) {
        mLoadingDialog.showLoading(title, countDownTimer);
    }

    public void showLoading(boolean cancelable, int countDownTimer) {
        mLoadingDialog.showLoading(cancelable, countDownTimer);
    }

    public void showLoading(String title) {
        mLoadingDialog.showLoading(title);
    }

    public void showLoading(boolean cancelable) {
        mLoadingDialog.showLoading(cancelable);
    }

    public void dismissLoading() {
        mLoadingDialog.dismissLoading();
    }

    protected void showFinishDialog() {
        AlertDialog builder = new AlertDialog.Builder(BaseActivity.this)
                .setTitle(R.string.dialog_prompt)
                .setMessage(R.string.dialog_exit_software)
                .setPositiveButton(R.string.dialog_exit, (dialog, which) -> finish())
                .setNegativeButton(R.string.dialog_cancel, null)
                .show();
        builder.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);
        builder.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(Color.BLACK);
    }
}
