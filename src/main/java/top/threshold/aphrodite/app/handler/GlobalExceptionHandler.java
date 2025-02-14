package top.threshold.aphrodite.app.handler;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.threshold.aphrodite.pkg.entity.R;
import top.threshold.aphrodite.pkg.enums.Errors;
import top.threshold.aphrodite.pkg.exception.ApException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public R<?> notValidException(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getFieldError().getDefaultMessage();
        log.error("Parameter verification failed", e);
        return R.err(Errors.ERR_BAD_REQUEST.getCode(), message);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public R<?> messageNotReadable(HttpServletRequest req, HttpMessageNotReadableException e) {
        log.warn("messageNotReadable , {}, {}", req.getRequestURI(), e.getMessage());
        return R.err(Errors.ERR_BAD_REQUEST);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public R<String> badMethodHandler(HttpServletRequest req, HttpRequestMethodNotSupportedException e) {
        log.warn("HttpRequestMethodNotSupportedException , {}, {}", req.getRequestURI(), e.getMessage());
        return R.err(Errors.ERR_METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(ApException.class)
    @ResponseBody
    public R<?> ktExceptionHandler(HttpServletRequest req, ApException e) {
        printStackTrace(e);
        return R.err(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R<String> exceptionHandler(Exception e) {
        log.error("Exception:", e);
        return R.err(Errors.ERR_INTERNAL_SERVER_ERROR.getCode(), Errors.ERR_INTERNAL_SERVER_ERROR.getMessage());
    }

    private void printStackTrace(Throwable t) {
        StackTraceElement[] stack = t.getStackTrace();
        StringBuilder sb = new StringBuilder();
        sb.append("\n")
            .append(t.getClass().getName()).append(":").append(t.getMessage()).append("\n")
            .append("stack trace:\n");

        for (int i = 2; i < stack.length; i++) {
            StackTraceElement trace = stack[i];
            sb.append("  at ").append(trace.getClassName()).append(".").append(trace.getMethodName())
                .append("(").append(trace.getFileName()).append(":").append(trace.getLineNumber()).append(")\n");
        }
        log.error(sb.toString());
    }
}
