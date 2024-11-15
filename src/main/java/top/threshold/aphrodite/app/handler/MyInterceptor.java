package top.threshold.aphrodite.app.handler;

import cn.dev33.satoken.stp.StpUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import top.threshold.aphrodite.pkg.constant.Const;
import top.threshold.aphrodite.pkg.helper.RequestDataHelper;

import java.util.Map;

@Component
public class MyInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String userCode = StpUtil.getLoginIdAsString();
        RequestDataHelper.setRequestData(Map.of(Const.CODE, userCode));
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        RequestDataHelper.clear();
    }
}
