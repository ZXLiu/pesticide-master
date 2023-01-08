package pesticide.server.controller;

import pesticide.server.entity.DefectModification;
import pesticide.server.entity.ResponseEntity;
import pesticide.server.service.DefectModificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("defectModification")
public class DefectModificationController {

    @Autowired
    private DefectModificationService defectModificationService;

    @GetMapping
    public ResponseEntity<Object> getDefectModifications(Integer id, Integer defectId) {//获取缺陷修改信息
        Object defectModifications = null;
        if (id != null) {//Id不为空
            defectModifications = defectModificationService.getDefectModification(id);
        } else if (defectId != null) {//缺陷Id不为空
            defectModifications = defectModificationService.getDefectModificationTablesOfDefect(defectId);
        }
        return new ResponseEntity<>(0, defectModifications, null);
    }

    @PostMapping
    public ResponseEntity<Void> addDefectModification(@RequestBody DefectModification defectModification) {//添加缺陷修改信息
        defectModificationService.addDefectModification(defectModification);
        return new ResponseEntity<>(0);
    }
}
