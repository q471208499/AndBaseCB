package cn.cb.baselibrary.net;

import org.json.JSONException;
import org.json.JSONObject;

public class Result {
    /**
     * 消息
     */
    private String resultMsgKey = "message";
    private String message = "";
    /**
     * 状态码
     */
    private String resultCodeKey = "code";
    private int code = 0;
    /**
     * 数据
     */
    private String resultDataKey = "data";
    private Object data;
    /**
     * 是否成功
     */
    private String resultSuccessKey = "success";
    private boolean success = false;

    // private static final int CODE_ERROR_1 = -1;
    private static final int CODE_SUCCESS_201 = 201;
    private static final int CODE_FAIL_401 = 401;
    private static final int CODE_ERROR_501 = 501;

    public Result() {
    }

    public Result(String msg, int code, boolean success, Object obj) {
        this.message = msg;
        this.code = code;
        this.success = success;
        this.data = obj;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public static JSONObject getMissParamsObj() {
        return getError("请求错误，参数为空或参数缺失。");
    }

    public static JSONObject getSuccess() {
        return getSuccess("success", null);
    }

    public static JSONObject getSuccess(String msg) {
        return getSuccess(msg, null);
    }

    public static JSONObject getSuccess(String msg, Object object) {
        Result result = new Result(msg, CODE_SUCCESS_201, true, object);
        return result.toJson();
    }

    public static JSONObject getFail() {
        return getFail("fail", null);
    }

    public static JSONObject getFail(String msg) {
        return getFail(msg, null);
    }

    public static JSONObject getFail(String msg, Object object) {
        Result result = new Result(msg, CODE_FAIL_401, false, object);
        return result.toJson();
    }

    public static JSONObject getError() {
        return getError(null, null);
    }

    public static JSONObject getError(String msg) {
        return getError(msg, null);
    }

    public static JSONObject getError(String msg, Object object) {
        Result result = new Result(msg, CODE_ERROR_501, false, object);
        return result.toJson();
    }

    public JSONObject toJson() {
        JSONObject object = new JSONObject();
        try {
            object.put(resultMsgKey, message);
            object.put(resultCodeKey, code);
            object.put(resultDataKey, data);
            object.put(resultSuccessKey, success);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }

    @Override
    public String toString() {
        return toJson().toString();
    }
}
