package cn.cb.baselibrary.provider;

public class ABConstant {
    public static final int REQUEST_CODE_SCAN = 1000;
    public static final int REQUEST_CODE_PERMISSION = 1001;

    public static final String APP_UPDATE_SUFFIX = "appUpdate/%1$s/update.txt";

    public static final int HTTP_NETWORK_ERROR = -1; //网络失败
    public static final int HTTP_JSON_ERROR = -2; //解析失败
    public static final int HTTP_OTHER_ERROR = -3; //未知错误
    public static final int HTTP_TIMEOUT_ERROR = -4; //请求超时
}
