package vc.thinker.sys.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import vc.thinker.sys.bo.RolePermissionBO;
import vc.thinker.sys.model.RolePermission;
import vc.thinker.sys.model.RolePermissionExample;

public interface RolePermissionMapper {
    int countByExample(RolePermissionExample example);

    int deleteByExample(RolePermissionExample example);

    int deleteByPrimaryKey(@Param("permissionId") Long permissionId, @Param("roleId") Long roleId);

    int insert(RolePermission record);

    int insertSelective(RolePermission record);

    List<RolePermissionBO> selectByExample(RolePermissionExample example);

    int updateByExampleSelective(@Param("record") RolePermission record, @Param("example") RolePermissionExample example);

    int updateByExample(@Param("record") RolePermission record, @Param("example") RolePermissionExample example);
}