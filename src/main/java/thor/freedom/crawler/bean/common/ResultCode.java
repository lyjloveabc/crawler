package thor.freedom.crawler.bean.common;

import java.util.Objects;

/**
 * Created by Thor on 2018/1/18.
 */
public enum ResultCode {
    //================================ START default ================================//
    DEFAULT_SUCCESS("111111", "操作成功"),
    DEFAULT_ERROR("000000", "操作失败"),

    AUTH_FAIL("100001", "没有权限"),
    PARAM_ERROR("100002", "参数错误"),;

    private String code;

    private String msg;

    ResultCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static boolean isSucceed(String code) {
        return Objects.equals(code, DEFAULT_SUCCESS.code);
    }

    @Override
    public String toString() {
        return this.code + " " + this.msg;
    }
}
