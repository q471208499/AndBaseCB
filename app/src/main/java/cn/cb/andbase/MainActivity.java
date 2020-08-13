package cn.cb.andbase;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.HashMap;
import java.util.Map;

import cn.cb.baselibrary.activity.BaseActivity;
import cn.cb.baselibrary.net.okhttp3.OkHttpException;
import cn.cb.baselibrary.net.okhttp3.ResponseCallback;
import cn.cb.baselibrary.utils.ABFileUtils;
import cn.cb.baselibrary.widget.PullLoadMoreRecyclerView;


public class MainActivity extends BaseActivity {
    private final String TAG = MainActivity.class.getSimpleName();
    PullLoadMoreRecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initBarView();
        ABFileUtils.coverSysCookieDBFromName("MOBILE");
        loadWebView();

        //testHttpCookie();
        findViewById(R.id.main_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //CookieManager.getInstance().flush();
                webView.clearCache(true);
                ABFileUtils.coverSysCookieDBFromName("MOBILE");
                startActivity(new Intent(MainActivity.this, TestBrowserActivity.class));
//                webView.loadUrl("https://bean.m.jd.com/bean/signIndex.action");
                /*webView.post(new Runnable() {
                    @Override
                    public void run() {
                        webView.evaluateJavascript("document.getElementsByClassName('my_header_name')[0].innerText;", new ValueCallback<String>() {
                            @Override
                            public void onReceiveValue(String value) {
                                Log.e(TAG, "onReceiveValue: " + value);
                            }
                        });
                    }
                });*/
            }
        });

        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLinearLayout();
        recyclerView.setAdapter(new RecyclerAdapter());
        recyclerView.setPushRefreshEnable(true);

        recyclerView.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        recyclerView.setPullLoadMoreCompleted();
                    }
                }, 2000);
            }

            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        recyclerView.setPullLoadMoreCompleted();
                    }
                }, 2000);
            }
        });
    }

    public final String USER_AGENT_MOBILE = "Mozilla/5.0 (iPhone; CPU iPhone OS 13_2_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.0.3 Mobile/15E148 Safari/604.1";
    public final String USER_AGENT_CHROME = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.61 Safari/537.36";

    WebView webView;

    private void loadWebView() {
        //ABFileUtils.coverSysCookieFolderFromName("CHROME");

        webView = findViewById(R.id.main_web);
        //webView = new WebView(this);
//        webView.loadUrl("https://www.jd.com/");
        webView.loadUrl("https://m.jd.com");
        //webView.loadUrl("https://home.m.jd.com/myJd/home.action");
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
//        webSettings.setUserAgentString(USER_AGENT_CHROME);
        webSettings.setUserAgentString(USER_AGENT_MOBILE);
        webView.setWebViewClient(viewClient);
    }

    private WebViewClient viewClient = new WebViewClient() {
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            Log.e(TAG, "onPageFinished: " + url);
            //ABFileUtils.copyCookieFolderAsName("CHROME");
            /*CookieManager manager = CookieManager.getInstance();
            String cookieStr = manager.getCookie(url);
            ABFileUtils.writeFile("CHROME", cookieStr);
            manager.setCookie("https://www.jd.com/", cookieStr);*/
        }
    };

    final String signIndex = "https://bean.m.jd.com/bean/signIndex.action";
    final String queryJDUserInfo = "https://wq.jd.com/user/info/QueryJDUserInfo?sceneid=80027&_=1590162232600&sceneval=2&g_login_type=1&callback=getUserInfoCb&g_tk=897483403&g_ty=ls";

    private void testHttpCookie() {
        //String cookiesStr = "__jdu=1586503892681666464210; shshshfpa=1bbf5a39-33dc-103f-11b5-02f03488b293-1586935979; shshshfpb=o%2Fgn2u7FyEq4kwHsdRLzOYQ%3D%3D; areaId=19; ipLoc-djd=19-1607-4773-0; TrackID=1VqnHL5HS3Y_W1rjJIW7Sh5fpVaInWshMzX4GFM0KLBoluWVIa3dvFvtWHnE1tIWVA55GnA_5UyDw9gQ5IawPCrtbZmqsjRuO1ci3iHAHEYT5T6dZ4TepD4v-ZNs6Pfe1; pinId=hdLwW49aXmB2ZIcJhR0lmLV9-x-f3wj7; pin=jd_5260362edab40; _tp=agHStuCK9FkRuL6fOzgm0IF3RZ1MwCA9tBAUCaDh6q0%3D; wxa_level=1; webp=1; mba_muid=1586503892681666464210; visitkey=41972097043893604; 3AB9D23F7A4B3C9B=WOLMQQNTWZX563NQWV3I6AGOELRMBNZTNI6UVMX5JQTL4JVHCVBVYCMMJBUHU6SVLCTX2G4ULQG6IYJXILIQ6IP2DY; jxsid=15901362004396550036; jcap_dvzw_fp=c5490363602925bfcac9a9c2aa3fa200$754002795591; pt_token=ti5x38lo; pwdt_id=jd_5260362edab40; PPRD_P=UUID.1586503892681666464210; sc_width=375; unpl=V2_ZzNtbUZfRBJwWBEGeEpaAGJQGlhKUEQUJgsRXHIaWldgABtfclRCFnQUR1BnGl8UZwQZWUdcQB1FCEdkexhdBGYKGlRKVXMVcQ8oVRUZVQAJbUBUR1NBQSAOQQFyHA8DYFEibUFXcxRFCEJdfB1dB24LEVpLV0QScg1BUXgZXTVXAxpZcmeUvNveyvKvoPbS1YEiWURURRF0CUdXSxhsBFdVfF1DVkIUdAtBU31UXAFuBBZcQF5LFnIBRlN8HlkCYgASXHJWcxY%3d; buy_uin=15134000273; jdpin=jd_5260362edab40; wq_skey=zm9C84FE09445D71DDC0893485DED1163F9D0C5C7E56C372D91159403E0194AE0CE864E1E6FA2C71F183FCB7120148AD44EEBB7F56882AE585BF7940BDA890C35987CFD714E40D522F37B22BAEE46F37C75601E9046A69538A8BD7105BA07E7446; wq_uin=15134000273; __jdv=76161171%7Cwww.linkstars.com%7Ct_1000089893_156_0_184__c8453ed76d84b76c%7Ctuiguang%7C59664afc2b744b949660b2f9827c6283%7C1590137723341; shshshfp=890eaeefd286068d6907461cb169bd99; downloadAppPlugIn_downCloseDate=1590157492622_21600000; autoOpenApp_downCloseDate_auto=1590158183566_21600000; qd_ad=-%7C-%7C-%7C-%7C0; qd_uid=KAIBV1RS-RYSQDQXUS2N1BK7XYVB9; qd_fs=1590159420854; qd_ls=1590159420854; qd_ts=1590159420854; qd_sq=1; mcossmd=c588e807ba885a7eb3d108a27a8c775b; pinStatus=0; retina=1; mt_xid=V2_52007VwMWW1pcU1gWQRpbDGcEFVVYX1dZH0gpXAZvV0IACV9OCUxBHkAAMFASTlVQBVoDSB5YB2cHEVEOD1pSL0oYXwR7AhZOXltDWR1CHF0OYwciUm1YYlkfSxFUAGcHFFNtWlVZHw%3D%3D; mobilev=html5; __jda=168115133.1586503892681666464210.1586503893.1590156874.1590164999.12; __jdc=168115133; cid=9; wqmnx1=MDEyNjM5MnRlbW0zNzQ1blAxayBXNUhlVjNpODYzNDJLRUYoJQ%3D%3D; __jdb=168115133.2.1586503892681666464210|12.1590164999; mba_sid=15901649991713187656773197191.2; __wga=1590165003899.1590165003899.1590156932661.1590136324970.1.3; jxsid_s_t=1590165004009; jxsid_s_u=https%3A//home.m.jd.com/myJd/home.action; shshshsID=a1695ba3fa4260e26db360ad7e6a8037_1_1590165004797";
        String cookiesStr = "__jdu=1586503892681666464210; shshshfpa=1bbf5a39-33dc-103f-11b5-02f03488b293-1586935979; shshshfpb=o%2Fgn2u7FyEq4kwHsdRLzOYQ%3D%3D; areaId=19; ipLoc-djd=19-1607-4773-0; pinId=hdLwW49aXmB2ZIcJhR0lmLV9-x-f3wj7; pin=jd_5260362edab40; _tp=agHStuCK9FkRuL6fOzgm0IF3RZ1MwCA9tBAUCaDh6q0%3D; unpl=V2_ZzNtbUZfRBJwWBEGeEpaAGJQGlhKUEQUJgsRXHIaWldgABtfclRCFnQUR1BnGl8UZwQZWUdcQB1FCEdkexhdBGYKGlRKVXMVcQ8oVRUZVQAJbUBUR1NBQSAOQQFyHA8DYFEibUFXcxRFCEJdfB1dB24LEVpLV0QScg1BUXgZXTVXAxpZcmeUvNveyvKvoPbS1YEiWURURRF0CUdXSxhsBFdVfF1DVkIUdAtBU31UXAFuBBZcQF5LFnIBRlN8HlkCYgASXHJWcxY%3d; __jdv=76161171%7Cwww.linkstars.com%7Ct_1000089893_156_0_184__c8453ed76d84b76c%7Ctuiguang%7C59664afc2b744b949660b2f9827c6283%7C1590137723341; mt_xid=V2_52007VwMWW1pcU1gWQRpbDGcEFVVYX1dZH0gpXAZvV0IACV9OCUxBHkAAMFASTlVQBVoDSB5YB2cHEVEOD1pSL0oYXwR7AhZOXltDWR1CHF0OYwciUm1YYlkfSxFUAGcHFFNtWlVZHw%3D%3D; TrackID=1yGGgIKDeLfvvVpr1UTEMMQLAvgv10mjCCJxCtR2Xi2hXywzth_GWcxN4o72SEGMABrgxEMPQRBQUq6fHofQmKaTRfmEkYia3uloPZfUfrwbhZEpVCajhxuWhni3j1Yjm; ceshi3.com=201; list_sign_-480240657=f3f5dbbf8697701dbba8328ed52e3fc3; __jda=122270672.1586503892681666464210.1586503893.1590156874.1590164999.12; __jdc=122270672; 3AB9D23F7A4B3C9B=JUMUMQVSIDZO3RARZBTJRVLNGPZSY6GMKSBMFMOLDP2O6ZI4LY4JFIWARPNQWHVX6EECCAJHB36MVUMSSBRFXSH4YM; shshshfp=435ec04963b96790e744cc7edb18cd09; shshshsID=a1695ba3fa4260e26db360ad7e6a8037_7_1590165269757; __jdb=122270672.21.1586503892681666464210|12.1590164999";
        String[] cookieArr = cookiesStr.split(";");
        Map<String, Object> cookies = new HashMap<>();
        for (String s : cookieArr) {
            String[] entry = s.split("=");
            cookies.put(entry[0].trim(), entry[1].trim());
        }
        //RequestMode.getRequest(signIndex, null, callback, null, cookies);
    }

    private ResponseCallback callback = new ResponseCallback() {
        @Override
        public void onSuccess(Object responseObj) {
            Log.e("MainActivity", responseObj.toString());
        }

        @Override
        public void onFailure(OkHttpException failure) {
            Log.e("MainActivity", failure.geteMsg(), failure);
        }
    };
}
