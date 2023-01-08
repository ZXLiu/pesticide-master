package pesticide.server.service;

import pesticide.server.entity.Personnel;
import pesticide.server.entity.Project;
import pesticide.server.entity.RoleType;
import pesticide.server.repository.PersonnelRepository;
import pesticide.server.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private PersonnelRepository personnelRepository;
    @Autowired
    private HttpServletRequest request;

    private Comparator<Project> createTimeDescComparator = new Comparator<Project>() {//按照创建时间进行比较
        @Override
        public int compare(Project o1, Project o2) {
            return -(int) (o1.getCreateTime().getTime() - o2.getCreateTime().getTime());
        }
    };

    @Override
    public Project getProject(Integer projectId) {//通过项目Id获取该项目
        return projectRepository.findById(projectId).orElseThrow(() ->
                new EntityNotFoundException("project of id = " + projectId + " is not found"));
    }

    @Override
    public List<Project> getProjectList(Integer userId) {//通过人员Id获取到该人员参与的全部项目
        return projectRepository.findProjectListByUserId(userId)
                .stream()
                .sorted(createTimeDescComparator)
                .collect(Collectors.toList());//返回通过项目创建时间排序后的项目列表
    }

    @Override
    public void addProject(Project project) {//添加项目
        Integer userId = (Integer) request.getSession().getAttribute("userId");//获取人员Id
        project.setManagerId(userId);//将该人员设置为管理员
        project.setCreateTime(new Date());//设置项目创建时间
        project = projectRepository.save(project);//通过save方法把临时对象转变为持久化对象project
        Personnel personnel = new Personnel();//创建人员对象
        personnel.setProjectId(project.getId());//设置项目Id
        personnel.setUserId(project.getManagerId());//设置人员Id
        personnel.setRoleType(RoleType.ADMINISTRATOR);//设置职位(管理员)
        personnelRepository.save(personnel);//通过save方法把临时对象转变为持久化对象personnel
    }

    @Override
    public void updateProject(Project project) {//更新项目
        if (!projectRepository.existsById(project.getId())) {//待更新的项目不存在
            throw new IllegalArgumentException("the updating project is not exist.");
        }
        projectRepository.save(project);//通过save方法把临时对象转变为持久化对象project
    }

    @Override
    public void removeProject(Integer projectId) {
        projectRepository.deleteById(projectId);
    }//通过项目Id删除项目
}
