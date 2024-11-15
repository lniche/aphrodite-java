package top.threshold.aphrodite.app.repository;

import com.mybatisflex.core.service.IService;
import top.threshold.aphrodite.app.entity.pojo.UserDO;

public interface UserRepository extends IService<UserDO> {

    UserDO getByPhone(String phone);

    UserDO getByCode(String code);
}
