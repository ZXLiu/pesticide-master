package pesticide.server.repository;

import pesticide.server.entity.UserAuth;
import pesticide.server.entity.UserAuthType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAuthRepository extends JpaRepository<UserAuth, Integer> {

    UserAuth getUserAuthByAuthTypeAndUsername(UserAuthType authType, String username);//通过认证方式和用户名获取用户认证的实体类
}
