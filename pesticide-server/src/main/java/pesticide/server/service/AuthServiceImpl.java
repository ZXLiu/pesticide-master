package pesticide.server.service;

import pesticide.server.entity.UserAuth;
import pesticide.server.entity.UserInfo;
import pesticide.server.exception.AuthException;
import pesticide.server.repository.UserAuthRepository;
import pesticide.server.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserAuthRepository userAuthRepository;
    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public void register(UserAuth userAuth) {//注册功能
        UserAuth existUserAuth = userAuthRepository
                .getUserAuthByAuthTypeAndUsername(userAuth.getAuthType(), userAuth.getUsername());//通过认证方式和用户名获取用户认证的实体类
        if (existUserAuth != null) {//该用户已被注册
            throw new AuthException(-1, "register identity is exist!");
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setNickname(userAuth.getUsername());// 设置默认用户名
        userInfo = userInfoRepository.save(userInfo);//通过save方法把临时对象转变为持久化对象userInfo
        userAuth.setUserInfoId(userInfo.getId());//设置用户信息Id
        userAuthRepository.save(userAuth);//通过save方法把临时对象转变为持久化对象userAuth
    }

    @Override
    public UserAuth login(UserAuth userAuth) {//登录功能
        UserAuth existUserAuth = userAuthRepository
                .getUserAuthByAuthTypeAndUsername(userAuth.getAuthType(), userAuth.getUsername());//通过认证方式和用户名获取用户认证的实体类
        if (existUserAuth == null) {//该用户未被注册
            throw new AuthException(-2, "the user is not exist!");
        }
        if (!existUserAuth.getPassword().equals(userAuth.getPassword())) {//登录时输入密码与注册密码不一致
            throw new AuthException(-3, "authIdentity or authPassword is error!");
        }
        return existUserAuth;
    }
}
