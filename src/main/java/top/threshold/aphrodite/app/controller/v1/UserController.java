package top.threshold.aphrodite.app.controller.v1;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.threshold.aphrodite.app.controller.BaseController;
import top.threshold.aphrodite.app.repository.UserRepository;
import top.threshold.aphrodite.pkg.constant.CacheKey;
import top.threshold.aphrodite.pkg.entity.R;
import top.threshold.aphrodite.pkg.utils.RedisUtil;

import java.time.OffsetDateTime;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/user")
@Tag(name = "User Module")
public class UserController extends BaseController {

    private final UserRepository userRepository;
    private final RedisUtil redisUtil;

    @Operation(
        summary = "User Info",
        security = @SecurityRequirement(name = "Authorization")
    )
    @Parameters(
        @Parameter(name = "userCode", description = "User Code", in = ParameterIn.PATH)
    )
    @GetMapping("/{userCode}")
    public R<GetUserResponse> getUser(@PathVariable String userCode) {
        String actualUserCode = Objects.nonNull(userCode) && !userCode.isBlank() ? loginUid() : userCode;
        String redisKey = CacheKey.USER + actualUserCode;
        GetUserResponse getUserResponse = redisUtil.getObj(redisKey, GetUserResponse.class);
        if (getUserResponse == null) {
            var userDO = userRepository.getByCode(actualUserCode);
            if (userDO != null) {
                redisUtil.set(redisKey, userDO, 60);
                getUserResponse = new GetUserResponse();
                BeanUtils.copyProperties(userDO, getUserResponse);
            }
        }
        if (getUserResponse != null) {
            getUserResponse.setEmail(getUserResponse.getEmail());
            getUserResponse.setPhone(getUserResponse.getPhone());
        }
        return R.ok(getUserResponse);
    }

    @Operation(
        summary = "User Update",
        security = @SecurityRequirement(name = "Authorization")
    )
    @PutMapping("")
    public R<Void> updateUser(@Validated @RequestBody UpdateUserRequest updateUserRequest) {
        var userDO = userRepository.getByCode(loginUid());
        if (userDO == null) {
            return R.err("User does not exist");
        }
        BeanUtils.copyProperties(updateUserRequest, userDO, "userCode");
        userRepository.updateById(userDO);
        return R.ok();
    }

    @Operation(
        summary = "User Delete",
        security = @SecurityRequirement(name = "Authorization")
    )
    @DeleteMapping("")
    public R<Void> deleteUser() {
        var userDO = userRepository.getByCode(loginUid());
        if (userDO == null) {
            return R.err("User is not valid");
        }
        userDO.setStatus(3);
        userDO.setDeletedAt(OffsetDateTime.now());
        userRepository.updateById(userDO);
        return R.ok();
    }

    @Data
    public static class GetUserResponse {
        /**
         * Nickname
         */
        @Schema(description = "User Nickname", example = "john")
        private String nickname;

        /**
         * User Number
         */
        @Schema(description = "User Number", example = "100000")
        private Long userNo;

        /**
         * User Code
         */
        @Schema(description = "User Code", example = "A8000")
        private String userCode;

        /**
         * Email
         */
        @Schema(description = "User Email", example = "john@example.com")
        private String email;

        /**
         * Phone Number
         */
        @Schema(description = "User Phone", example = "13800138000")
        private String phone;
    }

    @Data
    public static class UpdateUserRequest {
        /**
         * Nickname
         */
        @Schema(description = "User Nickname", example = "john")
        private String nickname;

        /**
         * Email
         */
        @Schema(description = "User Email", example = "john@example.com")
        private String email;
    }
}
