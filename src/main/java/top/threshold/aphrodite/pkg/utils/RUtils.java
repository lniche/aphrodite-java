package top.threshold.aphrodite.pkg.utils;

import cn.hutool.json.JSONUtil;
import io.vertx.core.buffer.Buffer;
import top.threshold.aphrodite.app.model.R;

public class RUtils {

    public static Buffer buildSucessBuffer(R r) {
        r.setCode("0");
        r.setMessage("ok");
        Buffer buffer = Buffer.buffer();
        String data = JSONUtil.toJsonPrettyStr(r);
        buffer.appendBytes(data.getBytes());
        return buffer;

    }


    public static Buffer buildFailBuffer() {
        R r = new R();
        r.setCode("-1");
        r.setMessage("fail");
        Buffer buffer = Buffer.buffer();
        String data = JSONUtil.toJsonPrettyStr(r);
        buffer.appendBytes(data.getBytes());
        return buffer;

    }

    public static Buffer buildFailBuffer(String code, String msg) {
        R r = new R();
        r.setCode(code);
        r.setMessage(msg);
        Buffer buffer = Buffer.buffer();
        String data = JSONUtil.toJsonPrettyStr(r);
        buffer.appendBytes(data.getBytes());
        return buffer;

    }
}
