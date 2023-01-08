package pesticide.server.service;

import pesticide.server.entity.UserAuth;

public interface AuthService {

    void register(UserAuth userAuth);//用户注册

    UserAuth login(UserAuth userAuth);//用户登录
}
