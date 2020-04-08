package cn.cb.andbase.activity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.lang.reflect.Method;
import java.util.Calendar;

import cn.cb.andbase.R;
import cn.pedant.SweetAlert.SweetAlertDialog;
import es.dmoral.toasty.MyToast;

public class BaseActivity extends AppCompatActivity {
    protected Toolbar toolbar;
    private int whichSelect;
    private SweetAlertDialog mLoadingDialog;

    protected final String INTENT_EXTRA_RESULT_STR = "INTENT_EXTRA_RESULT_STR";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
    }

    protected void hideInput() {
        InputMethodManager manager = (InputMethodManager)this.getSystemService(Context.INPUT_METHOD_SERVICE);
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
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    protected void dialogWhich(String[] items, DialogInterface.OnClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setSingleChoiceItems(items, whichSelect, listener);
        builder.show();
    }

    protected void dialogWhichSex(@Nullable final View view) {
        final String[] sexs = new String[]{"男", "女"};
        dialogWhich(sexs, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                String sex = sexs[which];
                whichSelect = which;
                if (view instanceof TextView) {
                    ((TextView) view).setText(sex);
                }
            }
        });
    }

    protected void showDatePicker(@Nullable final View view) {
        hideInput();
        datePicker(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker dialog, int year, int month, int dayOfMonth) {
                String dateStr = year + "-" + (month + 1) + "-" + dayOfMonth;
                if (view instanceof TextView) {
                    ((TextView) view).setText(dateStr);
                }
            }
        });
    }

    protected void datePicker(@Nullable DatePickerDialog.OnDateSetListener listener) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog = new DatePickerDialog(this, listener, year, month, day);
        dialog.show();
    }

    protected void restartAPP(){
        launchToAPP(getPackageName());
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    protected void launchToAPP(String packageName){
        PackageManager packageManager = getPackageManager();
        if (checkPackInfo(packageName)) {
            Intent intent = packageManager.getLaunchIntentForPackage(packageName);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else {
            MyToast.showL("没有安装");
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

    protected void showDialog(String title, String msg, String pName, String nName, DialogInterface.OnClickListener pListener, DialogInterface.OnClickListener nListener) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        if (!TextUtils.isEmpty(title))
            dialog.setTitle(title);
        if (!TextUtils.isEmpty(msg))
            dialog.setMessage(msg);
        if (!TextUtils.isEmpty(pName))
            dialog.setPositiveButton(pName, pListener);
        if (!TextUtils.isEmpty(nName))
            dialog.setNegativeButton(nName, nListener);
        dialog.show();
    }

    protected void showLoading() {
        mLoadingDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        mLoadingDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        mLoadingDialog.setTitleText("Loading");
        mLoadingDialog.setCancelable(false);
        mLoadingDialog.show();
    }

    protected void dismissLoading() {
        if (mLoadingDialog == null) return;
        mLoadingDialog.dismiss();
    }
}
