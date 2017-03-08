package vc.thinker.sys.model;

import com.sinco.mybatis.dal.model.BaseModel;

public class UserRole extends BaseModel {
    /** 角色编号 **/
    private Long roleId;

    /** 用户编号 **/
    private Long userId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}