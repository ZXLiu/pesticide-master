package pers.geolo.pesticide.server.repository;

import pers.geolo.pesticide.server.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {

    @Query("select userInfo from UserInfo userInfo, Personnel personnel" +
            " where personnel.projectId = :projectId and personnel.userId = userInfo.id")
    List<UserInfo> getUserInfoOfProject(@Param("projectId") Integer projectId);//获取同一项目的所有项目人员信息
}
