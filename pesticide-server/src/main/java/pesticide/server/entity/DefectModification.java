package pesticide.server.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class DefectModification {//缺陷修改实体类

    @Id
    @GeneratedValue
    private Integer id;//缺陷修改Id
    private Integer defectId;//缺陷Id
    private Integer modifyUserId;//缺陷修改者Id
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;//修改提交日期
    private String modifyDescription;//修改描述
    private DefectState resultState;//修改后的状态

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDefectId() {
        return defectId;
    }

    public void setDefectId(Integer defectId) {
        this.defectId = defectId;
    }

    public Integer getModifyUserId() {
        return modifyUserId;
    }

    public void setModifyUserId(Integer modifyUserId) {
        this.modifyUserId = modifyUserId;
    }

    public String getModifyDescription() {
        return modifyDescription;
    }

    public void setModifyDescription(String modifyDescription) {
        this.modifyDescription = modifyDescription;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public DefectState getResultState() {
        return resultState;
    }

    public void setResultState(DefectState resultState) {
        this.resultState = resultState;
    }
}
