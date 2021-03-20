package cn.cb.baselibrary.fragment;

import android.app.Activity;
import android.content.Context;
import android.graphics.Outline;
import android.os.Bundle;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import cn.cb.baselibrary.widget.CBLoading;

public class BaseFragment extends Fragment {
    private CBLoading mLoadingDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLoadingDialog = new CBLoading(getContext());
    }

    /**
     * 修改状态栏颜色
     *
     * @param colorId
     */
    protected void setStatusBarColor(int colorId) {
        Window window = getActivity().getWindow();
        window.setStatusBarColor(getResources().getColor(colorId));
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

    protected void showLoading() {
        mLoadingDialog.showLoading();
    }

    public void showLoading(int countDownTimer) {
        mLoadingDialog.showLoading(countDownTimer);
    }

    public void showLoading(int countDownTimer, String title) {
        mLoadingDialog.showLoading(title, countDownTimer);
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

    public void showLoading(boolean cancelable, int countDownTimer) {
        mLoadingDialog.showLoading(cancelable, countDownTimer);
    }

    /**
     * 裁剪 View 圆角
     *
     * @param view
     */
    protected void setOutline(View view) {
        view.setOutlineProvider(new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), 20);
            }
        });
        view.setClipToOutline(true);
    }
}
