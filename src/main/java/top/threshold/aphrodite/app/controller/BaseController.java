package top.threshold.aphrodite.app.controller;

import cn.dev33.satoken.stp.StpUtil;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public abstract class BaseController {

    private boolean isLogin() {
        return StpUtil.isLogin();
    }

    protected String login(String uid) {
        StpUtil.login(uid);
        return StpUtil.getTokenValue();
    }

    protected String loginUid() {
        return StpUtil.getLoginIdAsString();
    }

    protected String getRealIpAddress() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            String realIpAddress = attributes.getRequest().getHeader("realIpAddress");
            return realIpAddress != null ? realIpAddress : "127.0.0.1";
        }
        return "127.0.0.1";
    }
}
