package vc.thinker.sys.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import vc.thinker.sys.bo.PermissionBO;
import vc.thinker.sys.model.Permission;
import vc.thinker.sys.model.PermissionExample;

public interface PermissionMapper {
	
	List<PermissionBO> findByUid(@Param("uid") Long uid,@Param("userType") Integer userType);
	
	List<PermissionBO> findByRid(Long rid);
	
    int countByExample(PermissionExample example);

    int deleteByExample(PermissionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Permission record);

    int insertSelective(Permission record);

    List<PermissionBO> selectByExample(PermissionExample example);

    PermissionBO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Permission record, @Param("example") PermissionExample example);

    int updateByExample(@Param("record") Permission record, @Param("example") PermissionExample example);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);
}