package cn.cb.baselibrary.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.yhao.floatwindow.FloatWindow;
import com.yhao.floatwindow.PermissionListener;
import com.yhao.floatwindow.Screen;

import java.lang.reflect.Method;

import cn.cb.baselibrary.R;
import cn.cb.baselibrary.utils.LogHelper;
import cn.cb.baselibrary.widget.CBLoading;
import es.dmoral.toasty.MyToast;

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
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
     * 设置默认全服浮动控件：不可拖动，不可点击
     */
    protected void setFloatWindowViewDefault(View view) {
        PermissionListener permissionListener = new PermissionListener() {
            @Override
            public void onSuccess() {
                LogHelper.i(TAG, "onSuccess: ");
            }

            @Override
            public void onFail() {
                LogHelper.i(TAG, "onFail: ");
            }
        };
        if (FloatWindow.get() == null || !FloatWindow.get().isShowing())
            FloatWindow
                    .with(getApplicationContext())
                    .setView(view)
                    .setWidth(view.getLayoutParams().width) //设置悬浮控件宽高
                    .setHeight(view.getLayoutParams().height)
                    .setX(Screen.width)
                    .setY(Screen.height, 0.3f)
                    //.setMoveType(MoveType.slide,100,-100)
                    //.setMoveStyle(500, new BounceInterpolator())
                    //.setFilter(true, BaseActivity.class)
                    //.setViewStateListener(mViewStateListener)
                    .setPermissionListener(permissionListener)
                    .setDesktopShow(true)
                    .build();
    }

    /**
     * 设置bar标题居中
     */
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
                .setPositiveButton(R.string.dialog_exit, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton(R.string.dialog_cancel, null)
                .show();
        builder.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);
        builder.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(Color.BLACK);
    }
}
