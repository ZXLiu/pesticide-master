package pers.geolo.pesticide.server.controller;

import pers.geolo.pesticide.server.annotation.Auth;
import pers.geolo.pesticide.server.entity.Project;
import pers.geolo.pesticide.server.entity.ResponseEntity;
import pers.geolo.pesticide.server.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping
    public ResponseEntity<Object> getProject(Integer id, Integer userId) {
        if (id != null) {//项目Id不为空
            Project project = projectService.getProject(id);
            return new ResponseEntity<>(0, project, null);
        } else if (userId != null) {//用户Id不为空
            List<Project> projectList = projectService.getProjectList(userId);//通过用户Id获取该用户参与的全部项目
            return new ResponseEntity<>(0, projectList, null);
        }
        return null;
    }

    @Auth
    @PostMapping
    public ResponseEntity<Void> addProject(@RequestBody Project project) {//添加项目
        projectService.addProject(project);
        return new ResponseEntity<>(0);
    }

    @PutMapping
    public ResponseEntity<Void> updateProject(@RequestBody Project project) {//更新项目
        projectService.updateProject(project);
        return new ResponseEntity<>(0);
    }

    @DeleteMapping
    public ResponseEntity<Void> removeProject(Integer id) {//删除项目
        projectService.removeProject(id);
        return new ResponseEntity<>(0);
    }
}
