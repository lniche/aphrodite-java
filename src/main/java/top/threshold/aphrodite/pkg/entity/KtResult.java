package top.threshold.aphrodite.pkg.entity;

import top.threshold.aphrodite.pkg.enums.Errors;

import java.io.Serializable;

public class KtResult<T> implements Serializable {

    private int code = 0;
    private String message = "ok";
    private T data;

    public static <T> KtResult<T> ok() {
        return new KtResult<>();
    }

    public static <T> KtResult<T> ok(T data) {
        return ok(data, "ok");
    }

    public static <T> KtResult<T> ok(T data, String message) {
        KtResult<T> ktResult = new KtResult<>();
        ktResult.setData(data);
        ktResult.setMessage(message);
        return ktResult;
    }

    public static <T> KtResult<T> err() {
        return err(Errors.ERR);
    }

    public static <T> KtResult<T> err(String message) {
        return err(Errors.ERR.getCode(), message);
    }

    public static <T> KtResult<T> err(int code, String message) {
        KtResult<T> ktResult = new KtResult<>();
        ktResult.setCode(code);
        ktResult.setMessage(message);
        return ktResult;
    }

    public static <T> KtResult<T> err(Errors errors) {
        KtResult<T> ktResult = new KtResult<>();
        ktResult.setCode(errors.getCode());
        ktResult.setMessage(errors.getMessage());
        return ktResult;
    }

    public static <T> KtResult<T> err(Errors errors, T data) {
        KtResult<T> ktResult = new KtResult<>();
        ktResult.setCode(errors.getCode());
        ktResult.setMessage(errors.getMessage());
        ktResult.setData(data);
        return ktResult;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
