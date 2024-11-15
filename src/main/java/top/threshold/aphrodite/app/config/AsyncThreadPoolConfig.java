package top.threshold.aphrodite.app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import top.threshold.aphrodite.pkg.util.ThreadPoolExecutorMdcWrapper;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableAsync
public class AsyncThreadPoolConfig {

    @Value("${threadpool.core-pool-size:4}")
    private int corePoolSize;

    @Value("${threadpool.max-pool-size:8}")
    private int maxPoolSize;

    @Value("${threadpool.queue-capacity:1000}")
    private int queueCapacity;

    @Value("${threadpool.keep-alive-seconds:3000}")
    private int keepAliveSeconds;

    @Bean("asyncFairExecutor")
    public Executor asyncFairExecutor() {
        return new ThreadPoolExecutorMdcWrapper(
            corePoolSize,
            maxPoolSize,
            keepAliveSeconds,
            TimeUnit.MILLISECONDS,
            new ArrayBlockingQueue<>(queueCapacity, true),
            new CustomizableThreadFactory("asyncFairExecutor-"),
            new ThreadPoolExecutor.CallerRunsPolicy()
        );
    }

    @Bean("asyncNoFairExecutor")
    public Executor asyncNoFairExecutor() {
        return new ThreadPoolExecutorMdcWrapper(
            corePoolSize,
            maxPoolSize,
            keepAliveSeconds,
            TimeUnit.MILLISECONDS,
            new ArrayBlockingQueue<>(queueCapacity, true),
            new CustomizableThreadFactory("asyncNoFairExecutor-"),
            new ThreadPoolExecutor.CallerRunsPolicy()
        );
    }
}
