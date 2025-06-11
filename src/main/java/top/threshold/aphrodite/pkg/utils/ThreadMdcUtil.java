package top.threshold.aphrodite.pkg.utils;

import org.slf4j.MDC;
import top.threshold.aphrodite.pkg.constant.Const;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Callable;

public class ThreadMdcUtil {

    private static void setTraceIdIfAbsent() {
        if (MDC.get(Const.TRACE_ID) == null) {
            MDC.put(Const.TRACE_ID, UUID.randomUUID().toString().replace("-", ""));
        }
    }

    public static <T> Callable<T> wrap(final Callable<T> callable, final Map<String, String> context) {
        return new Callable<T>() {
            @Override
            public T call() throws Exception {
                if (context == null) {
                    MDC.clear();
                } else {
                    MDC.setContextMap(context);
                }
                setTraceIdIfAbsent();
                try {
                    return callable.call();
                } finally {
                    MDC.clear();
                }
            }
        };
    }

    public static Runnable wrap(final Runnable runnable, final Map<String, String> context) {
        return new Runnable() {
            @Override
            public void run() {
                if (context == null) {
                    MDC.clear();
                } else {
                    MDC.setContextMap(context);
                }
                setTraceIdIfAbsent();
                try {
                    runnable.run();
                } finally {
                    MDC.clear();
                }
            }
        };
    }
}
