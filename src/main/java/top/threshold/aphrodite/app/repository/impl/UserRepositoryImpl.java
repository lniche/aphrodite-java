package top.threshold.aphrodite.app.repository.impl;

import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.threshold.aphrodite.app.entity.pojo.UserDO;
import top.threshold.aphrodite.app.mapper.UserMapper;
import top.threshold.aphrodite.app.repository.UserRepository;

@Service
public class UserRepositoryImpl extends ServiceImpl<UserMapper, UserDO> implements UserRepository {

    @Override
    public UserDO getByPhone(String phone) {
        return getOne(new QueryWrapper().eq(UserDO::getPhone, phone));
    }

    @Override
    public UserDO getByCode(String code) {
        return getOne(new QueryWrapper().eq(UserDO::getUserCode, code));
    }
}
