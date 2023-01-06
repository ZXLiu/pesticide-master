package pers.geolo.pesticide.server.repository;

import pers.geolo.pesticide.server.entity.DefectModification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DefectModificationRepository extends JpaRepository<DefectModification, Integer> {

    List<DefectModification> getDefectModificationsByDefectId(Integer defectId);//通过缺陷Id找到对应的所有修改记录

//    @Query("select new DefectModificationTable(d, u) from DefectModification d, " +
//            "UserInfo u where ?1 = d.defectId and d.modifyUserId = u.id")
//    List<DefectModificationTable> getDefectModificationTablesByDefectId( Integer defectId);
}
