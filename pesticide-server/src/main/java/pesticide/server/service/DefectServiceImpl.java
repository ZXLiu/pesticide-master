package pesticide.server.service;

import pesticide.server.entity.Defect;
import pesticide.server.entity.DefectModification;
import pesticide.server.entity.DefectState;
import pesticide.server.repository.DefectModificationRepository;
import pesticide.server.repository.DefectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefectServiceImpl implements DefectService {

    @Autowired
    private DefectRepository defectRepository;
    @Autowired
    private DefectModificationRepository defectModificationRepository;
    @Autowired
    private HttpServletRequest request;

    private Comparator<Defect> submitTimeDescComparator = new Comparator<Defect>() {//设置缺陷提交时间比较器
        @Override
        public int compare(Defect o1, Defect o2) {
            return -(int) (o1.getSubmitTime().getTime() - o2.getSubmitTime().getTime());
        }
    };

    @Override
    public Defect getDefect(Integer id) {//获取缺陷信息
        return defectRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("defect of id = " + id + "is not found."));
    }

    @Override
    public List<Defect> getDefectsOfProject(Integer projectId) {//获取项目中的全部缺陷
        return defectRepository.findDefectsByProjectId(projectId)
                .stream()
                .sorted(submitTimeDescComparator)
                .collect(Collectors.toList());//按照缺陷提交时间排序
    }

    @Override
    public List<Defect> getDefectsOfSubmitUser(Integer submitUserId) {//通过提交人员Id找到该人员提交的所有缺陷
        return defectRepository.findDefectsBySubmitUserId(submitUserId)
                .stream()
                .sorted(submitTimeDescComparator)
                .collect(Collectors.toList());//按照缺陷提交时间排序
    }

    @Override
    public List<Defect> getDefectsOfResolveUser(Integer resolveUserId) {//通过处理人员Id找到该人员处理的所有缺陷
        return defectRepository.findDefectsByResolveUserId(resolveUserId)
                .stream()
                .sorted(submitTimeDescComparator)
                .collect(Collectors.toList());//按照缺陷提交时间排序
    }

    @Override
    public void addDefect(Defect defect) {//添加缺陷
        Integer userId = (Integer) request.getSession().getAttribute("userId");//获取当前人员Id
        defect.setSubmitUserId(userId);//设置提交人员Id
        defect.setDefectState(DefectState.SUBMITTED);//设置缺陷的默认状态(已提交)
        defect.setSubmitTime(new Date());//设置提交时间
        defectRepository.save(defect);//通过save方法把临时对象转变为持久化对象defect
        DefectModification defectModification = new DefectModification();//创建缺陷修改对象
        defectModification.setDefectId(defect.getId());//为缺陷修改对象设置缺陷Id
        defectModification.setModifyDescription("提交缺陷");//设置默认描述(提交缺陷)
        defectModification.setModifyUserId(defect.getSubmitUserId());//设置修改者Id
        defectModification.setModifyTime(new Date());//设置修改时间
        defectModification.setResultState(DefectState.SUBMITTED);//设置默认状态(已提交)
        defectModificationRepository.save(defectModification);//通过save方法把临时对象转变为持久化对象defectModification
    }

    @Override
    public void updateDefect(Defect defect) {
        defectRepository.save(defect);
    }//更新缺陷

    @Override
    public void removeDefect(Integer id) {
        defectRepository.deleteById(id);
    }//删除缺陷
}
