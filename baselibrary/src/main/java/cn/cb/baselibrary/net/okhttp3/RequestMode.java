package cn.cb.baselibrary.net.okhttp3;

import java.io.File;
import java.util.List;

/**
 * 创建： PengJunShan
 * 描述：请求模式
 */

public class RequestMode {

    /**
     * GET请求
     *
     * @param url      URL请求地址
     * @param params   入参
     * @param callback 回调接口
     * @param clazz    需要解析的实体类
     */
    public static void getRequest(String url, RequestParams params,
                                  ResponseCallback callback, Class<?> clazz) {
        CommonOkHttpClient.get(CommonRequest.createGetRequest(url, params),
                new ResponseDataHandle(callback, clazz));
    }

    /**
     * POST请求
     *
     * @param url      URL请求地址
     * @param params   入参
     * @param callback 回调接口
     * @param clazz    需要解析的实体类
     */
    public static void postRequest(String url, RequestParams params,
                                   ResponseCallback callback, Class<?> clazz) {
        CommonOkHttpClient.post(CommonRequest.createPostRequest(url, params),
                new ResponseDataHandle(callback, clazz));
    }

    /**
     * 下载图片 Get方式
     */
    public static void getDownloadFile(String url, RequestParams params, String filePath, ResponseByteCallback callback) {
        CommonOkHttpClient.downloadFile(CommonRequest.createGetRequest(url, params), filePath, callback);
    }

    /**
     * 下载图片 Post方式
     */
    public static void postDownloadFile(String url, RequestParams params, String imgPath, ResponseByteCallback callback) {
        CommonOkHttpClient.downloadFile(CommonRequest.createPostRequest(url, params), imgPath, callback);
    }

    /**
     * 表单和媒体 图文混合
     */
    public static void postMultipart(String url, RequestParams params,
                                     List<File> files, ResponseCallback callback, Class<?> clazz) {
        CommonOkHttpClient.post(CommonRequest.createMultipartRequest(url, params, files),
                new ResponseDataHandle(callback, clazz));
    }

}
