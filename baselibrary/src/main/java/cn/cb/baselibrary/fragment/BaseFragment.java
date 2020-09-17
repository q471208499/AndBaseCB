package cn.cb.baselibrary.fragment;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import java.util.Calendar;

import cn.cb.baselibrary.widget.CBLoading;

public class BaseFragment extends Fragment {
    private CBLoading mLoadingDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLoadingDialog = new CBLoading(getContext());
    }

    @Override
    public void onResume() {
        super.onResume();
        hideInput();
    }

    protected void hideInput() {
        Activity activity = getActivity();
        if (activity != null) {
            InputMethodManager manager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            View view = activity.getCurrentFocus();
            if (view != null) {
                assert manager != null;
                manager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    protected void showDatePicker(@Nullable final View view) {
        datePicker(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker dialog, int year, int month, int dayOfMonth) {
                hideInput();
                String dateStr = year + "-" + (month + 1) + "-" + dayOfMonth;
                if (view instanceof TextView) {
                    ((TextView) view).setText(dateStr);
                }
            }
        });
    }

    protected void showDialog(String title, String msg, String pName, String nName, DialogInterface.OnClickListener pListener, DialogInterface.OnClickListener nListener) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
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

    protected void datePicker(@Nullable DatePickerDialog.OnDateSetListener listener) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog = new DatePickerDialog(getContext(), listener, year, month, day);
        dialog.show();
    }

    protected void goSettings() {
        //Intent intent = new Intent(getActivity(), SettingsActivity.class);
        //intent.putExtra(Intent.EXTRA_TITLE, "设置");
        //startActivity(intent);
    }

    protected void showLoading() {
        mLoadingDialog.showLoading();
    }

    public void showLoading(int countDownTimer) {
        mLoadingDialog.showLoading(countDownTimer);
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
}
