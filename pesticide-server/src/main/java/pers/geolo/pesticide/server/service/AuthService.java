package pers.geolo.pesticide.server.service;

import pers.geolo.pesticide.server.entity.UserAuth;

public interface AuthService {

    void register(UserAuth userAuth);//用户注册

    UserAuth login(UserAuth userAuth);//用户登录
}
