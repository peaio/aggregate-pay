package vc.thinker.sys.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import vc.thinker.sys.bo.RoleBO;
import vc.thinker.sys.model.Role;
import vc.thinker.sys.model.RoleExample;

public interface RoleMapper {
	
	List<RoleBO> findUserRole(@Param("userId")Long userId, @Param("userType")Integer userType);
	
	List<RoleBO> findByDataScope(@Param("userType") Integer userType);
	
    int countByExample(RoleExample example);

    int deleteByExample(RoleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    int insertSelective(Role record);

    List<RoleBO> selectByExample(RoleExample example);

    RoleBO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByExample(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
}