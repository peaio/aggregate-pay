package vc.thinker.sys.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import vc.thinker.sys.bo.UserRoleBO;
import vc.thinker.sys.model.UserRole;
import vc.thinker.sys.model.UserRoleExample;

public interface UserRoleMapper {
    int countByExample(UserRoleExample example);

    int deleteByExample(UserRoleExample example);

    int deleteByPrimaryKey(@Param("roleId") Long roleId, @Param("userId") Long userId);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    List<UserRoleBO> selectByExample(UserRoleExample example);

    int updateByExampleSelective(@Param("record") UserRole record, @Param("example") UserRoleExample example);

    int updateByExample(@Param("record") UserRole record, @Param("example") UserRoleExample example);
}