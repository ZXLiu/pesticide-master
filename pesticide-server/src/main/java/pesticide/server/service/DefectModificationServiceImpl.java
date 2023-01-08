package pesticide.server.service;

import pesticide.server.entity.Defect;
import pesticide.server.entity.DefectModification;
import pesticide.server.entity.DefectModificationTable;
import pesticide.server.entity.UserInfo;
import pesticide.server.repository.DefectModificationRepository;
import pesticide.server.repository.DefectRepository;
import pesticide.server.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefectModificationServiceImpl implements DefectModificationService {

    @Autowired
    private DefectModificationRepository defectModificationRepository;
    @Autowired
    private DefectRepository defectRepository;
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private HttpServletRequest request;

    private Comparator<DefectModification> modifyTimeDescComparator =
            new Comparator<DefectModification>() {//设置缺陷修改时间比较器
                @Override
                public int compare(DefectModification o1, DefectModification o2) {
                    return -(int) (o1.getModifyTime().getTime() - o2.getModifyTime().getTime());
                }
            };

    private Comparator<DefectModificationTable> tableModifyTimeDescComparator =
            new Comparator<DefectModificationTable>() {//设置缺陷修改表的修改时间比较器
                @Override
                public int compare(DefectModificationTable o1, DefectModificationTable o2) {
                    return -(int) (o1.getModifyTime().getTime() - o2.getModifyTime().getTime());
                }
            };

    @Override
    public DefectModification getDefectModification(Integer id) {//获取缺陷修改信息
        return defectModificationRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("DefectModification of id = " + id + "is not found."));
    }

    @Override
    public List<DefectModification> getDefectModificationsOfDefect(Integer defectId) {//通过缺陷Id找到对应的所有修改记录
        return defectModificationRepository.getDefectModificationsByDefectId(defectId)
                .stream()
                .sorted(modifyTimeDescComparator)
                .collect(Collectors.toList());//按照修改时间排序
    }

    @Override
    public List<DefectModificationTable> getDefectModificationTablesOfDefect(Integer defectId) {//通过缺陷Id找到对应的所有修改表记录
        List<DefectModification> modifications = getDefectModificationsOfDefect(defectId);//通过缺陷Id找到对应的所有修改记录
        List<DefectModificationTable> modificationTables = new ArrayList<>();//创建修改记录表对象
        for (int i = 0; i < modifications.size(); i++) {
            String username = userInfoRepository.findById(modifications.get(i).getModifyUserId()).orElse(new UserInfo()).getNickname();//获取修改人姓名
            DefectModificationTable defectModificationTable = new DefectModificationTable(modifications.get(i), username);//创建单个修改记录对象
            modificationTables.add(defectModificationTable);//将单个记录添加到修改表中
        }
        return modificationTables;
    }

    @Override
    public void addDefectModification(DefectModification defectModification) {//添加缺陷修改信息
        Integer userId = (Integer) request.getSession().getAttribute("userId");//获取当前用户Id
        defectModification.setModifyUserId(userId);//设置修改人Id
        defectModification.setModifyTime(new Date());//设置修改时间
        defectModificationRepository.save(defectModification);//通过save方法把临时对象转变为持久化对象defectModification
        Defect defect = defectRepository.findById(defectModification.getDefectId()).orElseThrow(() ->
                new EntityNotFoundException("the defect of modification is not found."));//获取该修改记录的缺陷信息
        defect.setDefectState(defectModification.getResultState());//设置缺陷状态
        defectRepository.save(defect);//通过save方法把临时对象转变为持久化对象defect
    }
}
