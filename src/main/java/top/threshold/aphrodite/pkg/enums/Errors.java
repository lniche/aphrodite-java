package top.threshold.aphrodite.pkg.enums;

import top.threshold.aphrodite.pkg.exception.KtAssert;

public enum Errors implements KtAssert {

    // common errors
    ERR(-1, "err"),
    ERR_BAD_REQUEST(400, "Bad Request"),
    ERR_UNAUTHORIZED(401, "Unauthorized"),
    ERR_FORBIDDEN(403, "Forbidden"),
    ERR_NOT_FOUND(404, "Not Found"),
    ERR_METHOD_NOT_ALLOWED(405, "Method Not Allowed"),
    ERR_INTERNAL_SERVER_ERROR(500, "Internal Server Error"),

    // more biz errors
    ERR_DATA(1001, "Data Error"),
    ERR_SERVICE(1002, "Service Error");

    private final int code;
    private final String message;

    // Constructor
    Errors(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
