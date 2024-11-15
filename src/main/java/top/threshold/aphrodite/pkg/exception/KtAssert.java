package top.threshold.aphrodite.pkg.exception;

public interface KtAssert extends IResponseEnum, Assert {

    default KtException newException(Object... args) {
        return new KtException(this.getCode(), this.getMessage());
    }

    default KtException newException(Throwable t, Object... args) {
        return new KtException(this.getCode(), this.getMessage());
    }
}
