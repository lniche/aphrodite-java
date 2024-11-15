package top.threshold.aphrodite.pkg.exception;

import top.threshold.aphrodite.pkg.enums.Errors;

public class KtException extends RuntimeException {

    private int code;
    private String msg;
    private Object data;

    public KtException(int code, String msg) {
        this(code, msg, null);
    }

    public KtException(int code, String msg, Object data) {
        super(msg);
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public KtException(Errors errors) {
        this(errors.getCode(), errors.getMessage(), null);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
