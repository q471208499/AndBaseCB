package cn.cb.andbase;

import android.net.http.SslError;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import cn.cb.baselibrary.activity.BaseActivity;
import cn.cb.baselibrary.utils.ABFileUtils;

public class TestBrowserActivity extends BaseActivity {
    private final String TAG = getClass().getSimpleName();

    private WebView webView;
    private String cookieStr;
    public final String USER_AGENT_MOBILE = "Mozilla/5.0 (iPhone; CPU iPhone OS 13_2_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.0.3 Mobile/15E148 Safari/604.1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_browser);
        initBarView();
        findViewById(R.id.btn).setOnClickListener(clickListener);

        /*CookieManager.getInstance().removeAllCookies(new ValueCallback<Boolean>() {
            @Override
            public void onReceiveValue(Boolean value) {
                Log.e(TAG, "onReceiveValue: " + value);
            }
        });
        CookieManager.getInstance().setCookie("https://m.jd.com", ABFileUtils.readFileByKey("MOBILE"));*/

        //ABFileUtils.coverSysCookieFolderFromName("MOBILE");

        webView = findViewById(R.id.web);
        webView.loadUrl("https://m.jd.com");
        webView.setWebViewClient(viewClient);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setUserAgentString(USER_AGENT_MOBILE);
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            webView.loadUrl("https://bean.m.jd.com/bean/signIndex.action");
        }
    };

    private WebViewClient viewClient = new WebViewClient() {
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            Log.e(TAG, "onPageFinished: " + url);
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
