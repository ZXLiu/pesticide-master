package pesticide.server.controller;

import pesticide.server.annotation.Auth;
import pesticide.server.entity.Defect;
import pesticide.server.entity.ResponseEntity;
import pesticide.server.service.DefectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Auth
@RestController
@RequestMapping("defect")
public class DefectController {

    @Autowired
    private DefectService defectService;

    @GetMapping
    public ResponseEntity<Object> getDefects(Integer id, Integer projectId, Integer submitUserId, Integer resolveUserId) {//获取缺陷信息
        Object defects = null;
        if (id != null) {//Id不为空
            defects = defectService.getDefect(id);
        } else if (projectId != null) {//项目Id不为空
            defects = defectService.getDefectsOfProject(projectId);
        } else if (submitUserId != null) {//提交人员Id不为空
            defects = defectService.getDefectsOfSubmitUser(submitUserId);
        } else if (resolveUserId != null) {//处理人员Id不为空
            defects = defectService.getDefectsOfResolveUser(resolveUserId);
        }
        return new ResponseEntity<>(0, defects, null);
    }

    @PostMapping
    public ResponseEntity<Void> addDefect(@RequestBody Defect defect) {//添加缺陷
        defectService.addDefect(defect);
        return new ResponseEntity<>(0);
    }

    @PutMapping
    public ResponseEntity<Void> updateDefect(@RequestBody Defect defect) {//更新缺陷
        defectService.updateDefect(defect);
        return new ResponseEntity<>(0);
    }

    @DeleteMapping
    public ResponseEntity<Void> removeDefect(int id) {//删除缺陷
        defectService.removeDefect(id);
        return new ResponseEntity<>(0);
    }
}
