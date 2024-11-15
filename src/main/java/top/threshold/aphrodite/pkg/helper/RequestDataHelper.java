package top.threshold.aphrodite.pkg.helper;

import com.alibaba.ttl.TransmittableThreadLocal;

import java.util.Map;
import java.util.Objects;

public class RequestDataHelper {

    // TransmittableThreadLocal to hold the request data
    private static final TransmittableThreadLocal<Map<String, String>> REQUEST_DATA = new TransmittableThreadLocal<>();

    // Get a specific parameter from the request data
    public static String getParam(String param) {
        Map<String, String> dataMap = getRequestData();
        return (dataMap != null) ? dataMap.get(param) : null;
    }

    // Retrieve the entire request data map
    public static Map<String, String> getRequestData() {
        return REQUEST_DATA.get();
    }

    // Set the request data
    public static void setRequestData(Map<String, String> requestData) {
        REQUEST_DATA.set(requestData);
    }

    // Clear the request data
    public static void clear() {
        if (Objects.nonNull(REQUEST_DATA.get())) {
            REQUEST_DATA.remove();
        }
    }
}
