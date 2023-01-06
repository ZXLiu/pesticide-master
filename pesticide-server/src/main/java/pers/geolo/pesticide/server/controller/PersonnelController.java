package pers.geolo.pesticide.server.controller;

import pers.geolo.pesticide.server.entity.Personnel;
import pers.geolo.pesticide.server.entity.ResponseEntity;
import pers.geolo.pesticide.server.service.PersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("personnel")
public class PersonnelController {

    @Autowired
    private PersonnelService personnelService;

    @GetMapping
    public ResponseEntity<Object> getPersonnel(Integer personnelId, Integer projectId, Integer userId) {//获取项目人员信息
        if (personnelId != null) {//项目人员Id不为空
            Personnel personnel = personnelService.getPersonnel(personnelId);
            return new ResponseEntity<>(0, personnel, null);
        } else if (projectId != null && userId != null) {//项目Id和用户Id都不为空
            List<Personnel> personnelList = personnelService.getUserPersonnelOfProject(userId, projectId);
            //TODO 支持一个用户在一个项目多个角色
            return new ResponseEntity<>(0, personnelList.get(0), null);
        } else {//通过项目Id获取参与该项目的所有项目人员信息
            List<Personnel> personnelList = personnelService.getPersonnels(projectId);
            return new ResponseEntity<>(0, personnelList, null);
        }
    }

    @PostMapping
    public ResponseEntity<Void> addPersonnel(@RequestBody Personnel personnel) {//添加项目人员
        personnelService.addPersonnel(personnel);
        return new ResponseEntity<>(0);
    }

    @PutMapping
    public ResponseEntity<Void> updatePersonnel(@RequestBody Personnel personnel) {//更新项目人员
        personnelService.updatePersonnel(personnel);
        return new ResponseEntity<>(0);
    }

    @DeleteMapping
    public ResponseEntity<Void> removePersonnel(Integer personnelId) {//移除项目人员
        personnelService.removePersonnel(personnelId);
        return new ResponseEntity<>(0);
    }
}
