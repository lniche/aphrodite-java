package top.threshold.aphrodite.app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.threshold.aphrodite.pkg.entity.KtResult;

@RestController
public class HealthController {

    @RequestMapping("/")
    public KtResult<String> root() {
        return KtResult.ok("Thank you for using Aphrodite!");
    }

    @RequestMapping("/ping")
    public KtResult<String> ping() {
        return KtResult.ok("pong");
    }
}
