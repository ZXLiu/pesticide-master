package pesticide.server.service;

import pesticide.server.entity.Personnel;

import java.util.List;

public interface PersonnelService {

    Personnel getPersonnel(Integer personnelId);//通过项目人员Id获取人员信息

    List<Personnel> getPersonnels(Integer projectId);//通过项目Id获取参与该项目的所有人员信息

    void addPersonnel(Personnel personnel);//添加项目人员

    void updatePersonnel(Personnel personnel);//更新项目人员

    void removePersonnel(Integer personnelId);//删除项目人员

    List<Personnel> getUserPersonnelOfProject(Integer userId, Integer projectId);//通过用户Id和项目Id获取项目人员信息
}
