package pesticide.server.repository;

import pesticide.server.entity.Defect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DefectRepository extends JpaRepository<Defect, Integer> {

    List<Defect> findDefectsByProjectId(int projectId);//通过项目Id找到该项目的所有缺陷

    List<Defect> findDefectsBySubmitUserId(int submitUserId);//通过提交人员Id找到该人员提交的所有缺陷

    List<Defect> findDefectsByResolveUserId(int resolveUserId);//通过处理人员Id找到该人员处理的所有缺陷
}
