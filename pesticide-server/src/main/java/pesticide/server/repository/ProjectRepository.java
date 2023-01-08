package pesticide.server.repository;

import pesticide.server.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {

    @Query("select project from Project project, Personnel personnel" +
            " where :userId = personnel.userId and personnel.projectId = project.id")
    List<Project> findProjectListByUserId(@Param("userId") Integer userId);//通过项目人员的Id查找到他们参与的所有项目
}
