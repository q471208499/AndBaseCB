package cn.cb.baselibrary.net.okhttp3;

/**
 * 创建： PengJunShan
 * 描述：封装回调接口和要转换的实体对象
 */

public class ResponseDataHandle {

    public ResponseCallback mListener = null;
    public Class<?> mClass = null;

    public ResponseDataHandle(ResponseCallback listener) {
        this.mListener = listener;
    }

    public ResponseDataHandle(ResponseCallback listener, Class<?> clazz) {
        this.mListener = listener;
        this.mClass = clazz;
    }
    
}
