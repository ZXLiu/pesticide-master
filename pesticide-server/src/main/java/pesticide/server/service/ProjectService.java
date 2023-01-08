package pesticide.server.service;

import pesticide.server.entity.Project;

import java.util.List;

public interface ProjectService {

    void addProject(Project project);//添加项目

    void updateProject(Project project);//更新项目

    void removeProject(Integer projectId);//删除项目

    Project getProject(Integer projectId);//通过项目Id获取项目

    List<Project> getProjectList(Integer userId);//通过人员Id获取该人员参与的全部项目
}
