package pesticide.server.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class DefectModificationTable {//缺陷修改表实体类(比缺陷修改类多一个修改者姓名字段)

    private Integer id;//缺陷修改表Id
    private Integer defectId;//缺陷Id
    private Integer modifyUserId;//缺陷修改者Id
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;//修改提交日期
    private String modifyDescription;//修改描述
    private DefectState resultState;//修改后的状态
    private String modifyUsername;//修改者姓名

    public DefectModificationTable(DefectModification defectModification, String username) {
        this.id = defectModification.getId();
        this.defectId = defectModification.getDefectId();
        this.modifyUserId = defectModification.getModifyUserId();
        this.modifyTime = defectModification.getModifyTime();
        this.modifyDescription = defectModification.getModifyDescription();
        this.resultState = defectModification.getResultState();
        this.modifyUsername = username;
    }

    public DefectModificationTable() {
    }

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

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getModifyDescription() {
        return modifyDescription;
    }

    public void setModifyDescription(String modifyDescription) {
        this.modifyDescription = modifyDescription;
    }

    public DefectState getResultState() {
        return resultState;
    }

    public void setResultState(DefectState resultState) {
        this.resultState = resultState;
    }

    public String getModifyUsername() {
        return modifyUsername;
    }

    public void setModifyUsername(String modifyUsername) {
        this.modifyUsername = modifyUsername;
    }
}
