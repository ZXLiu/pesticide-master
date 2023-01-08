package pesticide.server.service;

import pesticide.server.entity.Defect;

import java.util.List;

public interface DefectService {

    Defect getDefect(Integer id);//获取缺陷信息

    List<Defect> getDefectsOfProject(Integer projectId);//通过项目Id获取该项目的全部缺陷

    List<Defect> getDefectsOfSubmitUser(Integer submitUserId);//通过提交人员Id获取已提交的全部缺陷

    List<Defect> getDefectsOfResolveUser(Integer resolveUserId);//通过解决人员Id获取已解决的全部缺陷

    void addDefect(Defect defect);//添加缺陷

    void updateDefect(Defect defect);//更新缺陷

    void removeDefect(Integer id);//删除缺陷
}
