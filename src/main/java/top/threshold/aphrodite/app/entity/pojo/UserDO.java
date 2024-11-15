package top.threshold.aphrodite.app.entity.pojo;

import com.mybatisflex.annotation.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import top.threshold.aphrodite.pkg.entity.BaseDO;

import java.time.OffsetDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Table("t_user")
public class UserDO extends BaseDO {

    private String userCode;

    private Long userNo;

    private String username;

    private String nickname;

    private String password;

    private String salt;

    private String email;

    private String phone;

    private String clientIp;

    private OffsetDateTime loginAt;

    private String loginToken;

    private String avatar;

    private Integer status;

    private String createdBy;

    private String updatedBy;

}
