package top.threshold.aphrodite.pkg.exception;

public class BaseException extends RuntimeException {

    private final IResponseEnum responseEnum;


    private final Object[] args;

    // Constructor with only responseEnum
    public BaseException(IResponseEnum responseEnum) {
        super(responseEnum.getMessage());
        this.responseEnum = responseEnum;
        this.args = null;
    }

    // Constructor with responseEnum and custom message
    public BaseException(IResponseEnum responseEnum, String message) {
        super(message);
        this.responseEnum = responseEnum;
        this.args = null;
    }

    // Constructor with responseEnum and args (used for parameterized messages)
    public BaseException(IResponseEnum responseEnum, Object[] args) {
        super(responseEnum.getMessage());
        this.responseEnum = responseEnum;
        this.args = args;
    }

    // Constructor with responseEnum, args, and custom message
    public BaseException(IResponseEnum responseEnum, Object[] args, String message) {
        super(message);
        this.responseEnum = responseEnum;
        this.args = args;
    }

    // Constructor with responseEnum, args, custom message, and cause (another throwable)
    public BaseException(IResponseEnum responseEnum, Object[] args, String message, Throwable cause) {
        super(message, cause);
        this.responseEnum = responseEnum;
        this.args = args;
    }

    // Getter for responseEnum
    public IResponseEnum getResponseEnum() {
        return responseEnum;
    }

    // Getter for args
    public Object[] getArgs() {
        return args;
    }
}
