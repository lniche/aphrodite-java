package top.threshold.aphrodite.app.config;

import cn.dev33.satoken.interceptor.SaInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.threshold.aphrodite.app.handler.MDCInterceptor;
import top.threshold.aphrodite.app.handler.MyInterceptor;

import java.util.List;

@Configuration
public class SaTokenConfigure implements WebMvcConfigurer {

    private static final List<String> common = List.of(
        "/",
        "/ping",
        "/v1/login",
        "/v1/send-code"
    );
    private static final List<String> staticPaths = List.of(
        "/swagger-ui/**",
        "/swagger-resources/**",
        "/api-docs/**",
        "/doc.html",
        "/webjars/**"
    );
    private final MDCInterceptor mdcInterceptor;
    private final MyInterceptor myInterceptor;

    public SaTokenConfigure(MDCInterceptor mdcInterceptor, MyInterceptor myInterceptor) {
        this.mdcInterceptor = mdcInterceptor;
        this.myInterceptor = myInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SaInterceptor())
            .addPathPatterns("/**")
            .excludePathPatterns(common)
            .excludePathPatterns(staticPaths);
        registry.addInterceptor(mdcInterceptor).excludePathPatterns(common).excludePathPatterns(staticPaths);
        registry.addInterceptor(myInterceptor).excludePathPatterns(common).excludePathPatterns(staticPaths);
    }
}
