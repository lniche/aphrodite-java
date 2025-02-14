package top.threshold.aphrodite.pkg.exception;

public interface ApAssert extends IResponseEnum, Assert {

    default ApException newException(Object... args) {
        return new ApException(this.getCode(), this.getMessage());
    }

    default ApException newException(Throwable t, Object... args) {
        return new ApException(this.getCode(), this.getMessage());
    }
}
