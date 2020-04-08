package cn.cb.baselibrary.net.okhttp3;

/**
 * 创建： PengJunShan
 * 描述：自定义异常类，返回ecode,emsg到业务层
 */

public class OkHttpException extends Exception {

  private static final long serialVersionUID = 1L;

  private int eCode; //错误码
  private String eMsg; //错误消息

  public OkHttpException(int eCode, String eMsg) {
    this.eCode = eCode;
    this.eMsg = eMsg;
  }

  public int geteCode() {
    return eCode;
  }

  public String geteMsg() {
    return eMsg;
  }
}
