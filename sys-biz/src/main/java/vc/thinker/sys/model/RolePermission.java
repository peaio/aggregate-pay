package vc.thinker.sys.model;

import com.sinco.mybatis.dal.model.BaseModel;

public class RolePermission extends BaseModel {
    /** 菜单编号 **/
    private Long permissionId;

    /** 角色编号 **/
    private Long roleId;

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}