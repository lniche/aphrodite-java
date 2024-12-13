package top.threshold.aphrodite.pkg.entity;

import lombok.Getter;
import lombok.Setter;
import top.threshold.aphrodite.pkg.enums.Errors;

import java.io.Serializable;

@Setter
@Getter
public class R<T> implements Serializable {

    private int code = 0;
    private String message = "ok";
    private T data;

    public static <T> R<T> ok() {
        return new R<>();
    }

    public static <T> R<T> ok(T data) {
        return ok(data, "ok");
    }

    public static <T> R<T> ok(T data, String message) {
        R<T> r = new R<>();
        r.setData(data);
        r.setMessage(message);
        return r;
    }

    public static <T> R<T> err() {
        return err(Errors.ERR);
    }

    public static <T> R<T> err(String message) {
        return err(Errors.ERR.getCode(), message);
    }

    public static <T> R<T> err(int code, String message) {
        R<T> r = new R<>();
        r.setCode(code);
        r.setMessage(message);
        return r;
    }

    public static <T> R<T> err(Errors errors) {
        R<T> r = new R<>();
        r.setCode(errors.getCode());
        r.setMessage(errors.getMessage());
        return r;
    }

    public static <T> R<T> err(Errors errors, T data) {
        R<T> r = new R<>();
        r.setCode(errors.getCode());
        r.setMessage(errors.getMessage());
        r.setData(data);
        return r;
    }

}
