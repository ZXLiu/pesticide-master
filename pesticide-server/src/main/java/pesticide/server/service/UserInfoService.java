package pesticide.server.service;

import pesticide.server.entity.UserInfo;
import pesticide.server.repository.FileInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserInfoService {

    UserInfo getUserInfo(Integer id);//通过Id获取单一人员信息

    List<UserInfo> getAllUserInfo();//获取所有人员信息

    void updateUserInfo(UserInfo userInfo);//更新用户信息

    void updateAvatar(Integer userId, MultipartFile avatarFile);//更新文件数据

    FileInfo getAvatar(String avatarId);//通过文件Id获取该文件的详细信息

    List<UserInfo> getUserInfoOfProject(Integer projectId);//通过项目Id获取参与该项目的所有项目人员信息
}
