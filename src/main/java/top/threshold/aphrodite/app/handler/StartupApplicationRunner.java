package top.threshold.aphrodite.app.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import top.threshold.aphrodite.pkg.constant.CacheKey;
import top.threshold.aphrodite.pkg.utils.RedisUtil;

@Slf4j
@Component
@RequiredArgsConstructor
public class StartupApplicationRunner implements ApplicationRunner {

    private final RedisUtil redisUtil;
    @Value("${server.address:127.0.0.1}")
    private String address;
    @Value("${server.port:8000}")
    private String port;


    @Override
    public void run(ApplicationArguments args) {
        if (!redisUtil.hasKey(CacheKey.NEXT_UNO)) {
            redisUtil.setLong(CacheKey.NEXT_UNO, 100000);
        }

        log.info("===============================");
        log.info("Listening on {\"host\": \"http://{}:{}}", address, port);
        log.info("Docs addr {\"addr\": \"http://{}:{}/swagger-ui/index.html\"}", address, port);
        log.info("===============================");
    }
}
