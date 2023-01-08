package pesticide.server.service;

import pesticide.server.entity.Personnel;
import pesticide.server.repository.PersonnelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class PersonnelServiceImpl implements PersonnelService {

    @Autowired
    private PersonnelRepository personnelRepository;

    @Override
    public Personnel getPersonnel(Integer personnelId) {//通过人员Id获取项目人员信息
        return personnelRepository.findById(personnelId).orElseThrow(() ->
                new EntityNotFoundException("personnel of id = " + personnelId + " not found."));
    }

    @Override
    public List<Personnel> getPersonnels(Integer projectId) {//通过项目Id获取参与该项目的全部人员信息
        return personnelRepository.findPersonnelsByProjectId(projectId);
    }

    @Override
    public void addPersonnel(Personnel personnel) {//通过save方法把临时对象转变为持久化对象personnel
        personnelRepository.save(personnel);
    }

    @Override
    public void updatePersonnel(Personnel personnel) {//通过save方法把临时对象转变为持久化对象personnel
        personnelRepository.save(personnel);
    }

    @Override
    public void removePersonnel(Integer personnelId) {
        personnelRepository.deleteById(personnelId);
    }//删除项目人员

    @Override
    public List<Personnel> getUserPersonnelOfProject(Integer userId, Integer projectId) {//通过用户Id和项目Id获取项目人员信息
        return personnelRepository.findPersonnelsByUserIdAndProjectId(userId, projectId);
    }
}
