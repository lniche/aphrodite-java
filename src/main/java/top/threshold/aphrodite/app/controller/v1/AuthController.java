package top.threshold.aphrodite.app.controller.v1;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.threshold.aphrodite.app.controller.BaseController;
import top.threshold.aphrodite.app.entity.pojo.UserDO;
import top.threshold.aphrodite.app.repository.UserRepository;
import top.threshold.aphrodite.pkg.constant.CacheKey;
import top.threshold.aphrodite.pkg.entity.R;
import top.threshold.aphrodite.pkg.utils.RedisUtil;
import top.threshold.aphrodite.pkg.utils.SnowflakeUtil;

import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1")
@Tag(name = "Auth Module")
public class AuthController extends BaseController {

    private final RedisUtil redisUtil;
    private final UserRepository userRepository;

    @Operation(summary = "Send Verification Code")
    @PostMapping("/send-code")
    public R<Void> sendVerifyCode(@Validated @RequestBody SendVerifyCodeRequest sendVerifyCodeRequest) {
        String cacheKey = CacheKey.SMS_CODE + sendVerifyCodeRequest.getPhone();
        if (redisUtil.hasKey(cacheKey)) {
            return R.err("A verification code has already been sent within a minute, please try again later");
        }
        String cacheCode = String.valueOf(ThreadLocalRandom.current().nextInt(1000, 10000));
        log.debug("cache code: {}", cacheCode);
        redisUtil.setStr(cacheKey, cacheCode, 60);
        // TODO fake send
        return R.ok();
    }

    @Operation(summary = "User Registration/Login")
    @PostMapping("/login")
    public R<LoginResponse> login(@Validated @RequestBody LoginRequest loginRequest) {
        String codeKey = CacheKey.SMS_CODE + loginRequest.getPhone();
        String cacheCode = redisUtil.getStr(codeKey);
        if (!loginRequest.getCode().equals(cacheCode))
            return R.err("Verification code is incorrect, please re-enter");

        UserDO userDO = userRepository.getByPhone(loginRequest.getPhone());
        if (Objects.isNull(userDO)) {
            userDO = new UserDO();
            userDO.setUserNo(redisUtil.nextId(CacheKey.NEXT_UNO));
            userDO.setUserCode(SnowflakeUtil.nextId()+"");
            userDO.setClientIp(getRealIpAddress());
            userDO.setNickname("SUGAR_" + loginRequest.getPhone().substring(loginRequest.getPhone().length() - 4));
            userDO.setPhone(loginRequest.getPhone());
            userDO.setLoginAt(OffsetDateTime.now());
            userDO.setLoginToken(login(userDO.getUserCode()));
            userRepository.save(userDO);
        } else {
            userDO.setClientIp(getRealIpAddress());
            userDO.setLoginAt(OffsetDateTime.now());
            userDO.setLoginToken(login(userDO.getUserCode()));
            userRepository.updateById(userDO);
        }

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setAccessToken(userDO.getLoginToken());
        redisUtil.del(codeKey);
        return R.ok(loginResponse);
    }

    @Operation(
        summary = "User Logout",
        security = {@SecurityRequirement(name = "Authorization")}
    )
    @PostMapping("/logout")
    public R<Void> logout() {
        UserDO userDO = userRepository.getByCode(loginUid());
        if (userDO == null) {
            return R.err("User not found");
        }
        userDO.setLoginToken("");
        userRepository.updateById(userDO);
        return R.ok();
    }

    @Data
    public static class SendVerifyCodeRequest {
        /**
         * Phone number
         */
        @NotBlank(message = "Phone number cannot be empty")
        @Schema(description = "User Phone", example = "13800138000", required = true)
        @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Phone number format is incorrect")
        private String phone;
    }

    @Data
    public static class LoginRequest {
        /**
         * Phone number
         */
        @NotBlank(message = "Phone number cannot be empty")
        @Schema(description = "User Phone", example = "13800138000", required = true)
        private String phone;

        /**
         * Verification code
         */
        @NotBlank(message = "Verification code cannot be empty")
        @Schema(description = "Verification Code", example = "1234", required = true)
        private String code;
    }

    @Data
    public static class LoginResponse {
        /**
         * Access token
         */
        @Schema(description = "Access token", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
        private String accessToken;
    }
}
