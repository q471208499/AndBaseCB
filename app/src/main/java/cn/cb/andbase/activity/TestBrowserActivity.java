package cn.cb.andbase.activity;

import android.net.http.SslError;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import cn.cb.andbase.R;
import cn.cb.baselibrary.activity.BaseActivity;
import cn.cb.baselibrary.utils.ABDateUtils;
import cn.cb.baselibrary.utils.LogHelper;

public class TestBrowserActivity extends BaseActivity {
    private final String TAG = getClass().getSimpleName();

    private WebView webView;
    private String cookieStr;
    public final String USER_AGENT_MOBILE = "Mozilla/5.0 (iPhone; CPU iPhone OS 13_2_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.0.3 Mobile/15E148 Safari/604.1";
    private TextView log;
    private StringBuilder sb = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_browser);
        initBarView();
        findViewById(R.id.btn).setOnClickListener(clickListener);
        findViewById(R.id.btn2).setOnClickListener(clickListener);
        findViewById(R.id.btn_mobile).setOnClickListener(clickListener);

        /*CookieManager.getInstance().removeAllCookies(new ValueCallback<Boolean>() {
            @Override
            public void onReceiveValue(Boolean value) {
                Log.e(TAG, "onReceiveValue: " + value);
            }
        });
        CookieManager.getInstance().setCookie("https://m.jd.com", ABFileUtils.readFileByKey("MOBILE"));*/

        //ABFileUtils.coverSysCookieFolderFromName("MOBILE");

        webView = findViewById(R.id.web);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDefaultTextEncodingName("UTF-8");

        webSettings.setDomStorageEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setAllowContentAccess(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setUserAgentString(USER_AGENT_MOBILE);

        //webView.loadUrl("https://m.jd.com");

        webView.setWebViewClient(viewClient);
        log = findViewById(R.id.log);
    }


    private long start = 0L;
    private final View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            start = System.currentTimeMillis();
            sb.append("\n").append("点击：").append(ABDateUtils.getCurDateStr(ABDateUtils.FORMAT_FULL)).append("\n");
            log.setText(sb.toString());
            if (v.getId() == R.id.btn) {
                webView.loadUrl("file:///android_asset/filing.html");
            } else if (v.getId() == R.id.btn2) {
                webView.loadUrl("file:///android_asset/filing2.html");
            } else if (v.getId() == R.id.btn_mobile) {
                webView.loadUrl("file:///android_asset/1619765914863.html");
            }
        }
    };

    private final WebViewClient viewClient = new WebViewClient() {
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            Log.e(TAG, "onPageFinished: " + url);
            long end = System.currentTimeMillis();
            sb.append("加载完成：")
                    .append(ABDateUtils.getCurDateStr(ABDateUtils.FORMAT_FULL))
                    .append("\n")
                    .append("耗时：")
                    .append((end - start))
                    .append(" ms.")
                    .append("\n");
            log.setText(sb.toString());
            LogHelper.d(TAG, (end - start) + " ms.");
            //ABFileUtils.copyCookieFolderAsName("MOBILE");
            /*CookieManager manager = CookieManager.getInstance();
            String cookieStr = manager.getCookie(url);
            ABFileUtils.writeFile("CHROME", cookieStr);
            manager.setCookie("https://m.jd.com", cookieStr);*/
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed();
            //super.onReceivedSslError(view, handler, error);
        }
    };
}
