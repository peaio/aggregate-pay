package vc.thinker.sys.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import vc.thinker.sys.bo.UserAccountBO;
import vc.thinker.sys.model.UserAccount;
import vc.thinker.sys.model.UserAccountExample;

public interface UserAccountMapper {
	
	UserAccountBO findLastLogin(Long uid);
	
    int countByExample(UserAccountExample example);

    int deleteByExample(UserAccountExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserAccount record);

    int insertSelective(UserAccount record);

    List<UserAccountBO> selectByExample(UserAccountExample example);

    UserAccountBO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserAccount record, @Param("example") UserAccountExample example);

    int updateByExample(@Param("record") UserAccount record, @Param("example") UserAccountExample example);

    int updateByPrimaryKeySelective(UserAccount record);

    int updateByPrimaryKey(UserAccount record);
}