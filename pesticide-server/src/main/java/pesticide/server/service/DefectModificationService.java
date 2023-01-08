package pesticide.server.service;

import pesticide.server.entity.DefectModification;
import pesticide.server.entity.DefectModificationTable;

import java.util.List;

public interface DefectModificationService {

    DefectModification getDefectModification(Integer id);//获取缺陷修改信息

    List<DefectModification> getDefectModificationsOfDefect(Integer defectId);//根据缺陷Id找到所有缺陷修改记录

    List<DefectModificationTable> getDefectModificationTablesOfDefect(Integer defectId);//根据缺陷Id找到所有缺陷修改表记录

    void addDefectModification(DefectModification defectModification);//添加缺陷修改记录
}
