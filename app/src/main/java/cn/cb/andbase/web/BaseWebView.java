package cn.cb.andbase.web;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.ArrayList;
import java.util.List;

public class BaseWebView extends WebView {
    private final String TAG = getClass().getSimpleName();

    private static BaseWebView instance;
    private List<String> mUrlList = new ArrayList<>();
    private boolean isLoading = false;
    private int mPosition;

    public static BaseWebView getInstance() {
        if (instance == null) {
            throw new NullPointerException("instance is null. BaseWebView should be createInstance(cxt) at first.");
        }
        return instance;
    }

    public static void createInstance(Context context) {
        if (instance == null) {
            synchronized (BaseWebView.class) {
                if (instance == null) {
                    instance = new BaseWebView(context);
                }
            }
        }
    }

    private BaseWebView(Context context) {
        super(context);
        init();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void init() {
        WebSettings webSettings = getSettings();
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setUserAgentString("");
        setWebViewClient(viewClient);
    }

    private WebViewClient viewClient = new WebViewClient() {
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            mPosition++;
            loadUrls();
        }
    };

    public void addAllUrl(List<String> urlList) {
        if (mUrlList.containsAll(urlList)) {

        } else {
            mUrlList.addAll(urlList);
        }
    }

    public void cleanUrls() {
        isLoading = false;
        mPosition = 0;
        mUrlList.clear();
    }

    public void load() {
        if (mUrlList.isEmpty()) {
            throw new ArrayIndexOutOfBoundsException("mUrlList.isEmpty()");
        }
        if (isLoading) {
            Log.i(TAG, "load: isLoading.... true");
        } else {
            mPosition = 0;
            isLoading = true;
            loadUrls();
        }
    }

    private void loadUrls() {
        if (mPosition >= mUrlList.size()) {
            mPosition = 0;
            isLoading = false;
            mUrlList.clear();
            Log.i(TAG, "load: isLoading.... finish");
        } else {
            loadUrl(mUrlList.get(mPosition));
        }
    }

}
