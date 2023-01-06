package pers.geolo.pesticide.server.repository;

import pers.geolo.pesticide.server.entity.Personnel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonnelRepository extends JpaRepository<Personnel, Integer> {

    List<Personnel> findPersonnelsByProjectId(int projectId);//通过项目Id找到对应项目的所有项目人员

    List<Personnel> findPersonnelsByUserIdAndProjectId(Integer userId, Integer projectId);//通过人员Id和项目Id找到对应项目的所有项目人员
}
