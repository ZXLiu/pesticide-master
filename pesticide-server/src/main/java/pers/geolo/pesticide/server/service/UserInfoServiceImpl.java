package pers.geolo.pesticide.server.service;

import pers.geolo.pesticide.server.entity.MultipartFileInfo;
import pers.geolo.pesticide.server.entity.UserInfo;
import pers.geolo.pesticide.server.repository.FileInfo;
import pers.geolo.pesticide.server.repository.FileRepository;
import pers.geolo.pesticide.server.repository.UserInfoRepository;
import pers.geolo.pesticide.server.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private FileRepository fileRepository;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;

    @Override
    public UserInfo getUserInfo(Integer id) {//通过Id获取单一人员信息
        return userInfoRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("user info of id =" + id + " is not found."));
    }

    @Override
    public List<UserInfo> getAllUserInfo() {
        return userInfoRepository.findAll();
    }//获取所有人员信息

    @Override
    public void updateUserInfo(UserInfo userInfo) {
        userInfoRepository.save(userInfo);
    }//通过save方法把临时对象转变为持久化对象userInfo

    @Override
    public void updateAvatar(Integer userId, MultipartFile avatarFile) {//更新文件数据
        FileInfo fileInfo = new MultipartFileInfo(avatarFile);//获取文件信息
        try {
            UserInfo userInfo = getUserInfo(userId);//获取人员信息
            String avatarId = fileRepository.saveFile(fileInfo);//文件路径
            String currentUrl = request.getScheme() + "://" + request.getServerName()//当前访问文件的url
                    + ":" + request.getServerPort()
                    + request.getServletPath() + "?avatarId=" + avatarId;
            userInfo.setAvatarUrl(currentUrl);//设置url
            updateUserInfo(userInfo);//更新用户信息
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public FileInfo getAvatar(String avatarId) {//通过Id获取文件信息
        try {
            FileInfo fileInfo = fileRepository.getFile(avatarId);//获取文件信息
            IOUtils.connectStream(fileInfo.getInputStream(), response.getOutputStream());//将获取到的文件信息写入当前文件
            return fileInfo;//返回文件信息
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<UserInfo> getUserInfoOfProject(Integer projectId) {//获取同一项目的所有项目人员信息
        return userInfoRepository.getUserInfoOfProject(projectId);
    }
}
