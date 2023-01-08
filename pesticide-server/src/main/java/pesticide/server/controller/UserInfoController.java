package pesticide.server.controller;

import pesticide.server.annotation.Auth;
import pesticide.server.entity.ResponseEntity;
import pesticide.server.entity.UserInfo;
import pesticide.server.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("userInfo")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private HttpSession session;

    @Auth
    @GetMapping
    public ResponseEntity<UserInfo> getUserInfo(Integer userId) {//获取用户信息
        if (userId == null) {//用户Id为空
            userId = (Integer) session.getAttribute("userId");//获取用户Id
        }
        UserInfo userInfo = userInfoService.getUserInfo(userId);//获取用户信息
        return new ResponseEntity<>(0, userInfo, null);
    }

    @Auth
    @GetMapping("project")
    public ResponseEntity<List<UserInfo>> getUserInfoOfProject(Integer projectId) {//获取同一项目的所有项目人员信息
        List<UserInfo> userInfoList = userInfoService.getUserInfoOfProject(projectId);
        return new ResponseEntity<>(0, userInfoList, null);
    }

    @Auth
    @GetMapping("all")
    public ResponseEntity<List<UserInfo>> getAllUserInfo() {//获取全部人员信息
        List<UserInfo> userInfoList = userInfoService.getAllUserInfo();
        return new ResponseEntity<>(0, userInfoList, null);
    }

    @Auth
    @PutMapping
    public ResponseEntity<Void> updateUserInfo(@RequestBody UserInfo userInfo) {//更新用户信息
        userInfoService.updateUserInfo(userInfo);
        return new ResponseEntity<>(0);
    }

    @GetMapping("/avatar")
    public void getAvatar(String avatarId) {
        userInfoService.getAvatar(avatarId);
    }//获取用户照片

    @Auth
    @PostMapping(value = "/avatar")
    public ResponseEntity<Void> uploadAvatar(@SessionAttribute("userId") Integer userId,
                                             MultipartFile avatar) throws IOException {//更新用户照片
        userInfoService.updateAvatar(userId, avatar);
        return new ResponseEntity<>(0);
    }
}
