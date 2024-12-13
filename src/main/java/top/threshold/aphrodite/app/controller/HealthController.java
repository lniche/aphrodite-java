package top.threshold.aphrodite.app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.threshold.aphrodite.pkg.entity.R;

@RestController
public class HealthController {

    @RequestMapping("/")
    public R<String> root() {
        return R.ok("Thank you for using Aphrodite!");
    }

    @RequestMapping("/ping")
    public R<String> ping() {
        return R.ok("pong");
    }
}
