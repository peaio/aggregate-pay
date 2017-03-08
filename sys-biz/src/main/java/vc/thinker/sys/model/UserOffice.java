package vc.thinker.sys.model;

import com.sinco.mybatis.dal.model.BaseModel;

public class UserOffice extends BaseModel {
    /** 机构id **/
    private Long officeId;

    /** 用户ID **/
    private Long userId;

    public Long getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Long officeId) {
        this.officeId = officeId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}