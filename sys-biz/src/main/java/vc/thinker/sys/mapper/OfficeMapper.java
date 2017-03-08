package vc.thinker.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import vc.thinker.sys.bo.OfficeBO;
import vc.thinker.sys.model.Office;
import vc.thinker.sys.model.OfficeExample;

public interface OfficeMapper {
	
	List<OfficeBO> findDataScopeOfficeByUid(Long uid);
	
    int countByExample(OfficeExample example);

    int deleteByExample(OfficeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Office record);

    int insertSelective(Office record);

    List<OfficeBO> selectByExample(OfficeExample example);

    OfficeBO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Office record, @Param("example") OfficeExample example);

    int updateByExample(@Param("record") Office record, @Param("example") OfficeExample example);

    int updateByPrimaryKeySelective(Office record);

    int updateByPrimaryKey(Office record);
    
    List<OfficeBO> findByScopeFilter();
    
    List<OfficeBO> findAllChild(@Param("parentId")Long parentId, @Param("likeParentIds")String likeParentIds);
    
    List<OfficeBO> findByRole(@Param("roleId")String roleId);
}