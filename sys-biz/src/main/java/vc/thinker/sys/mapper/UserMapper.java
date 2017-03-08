package vc.thinker.sys.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import vc.thinker.sys.bo.UserBO;
import vc.thinker.sys.model.User;
import vc.thinker.sys.model.UserExample;

public interface UserMapper {
	
	List<UserBO> findUserByRole(@Param("rid") Long rid,@Param("accountType") String accountType);
	
	List<UserBO> findByOffice(@Param("officeId") Long officeId,@Param("accountType") String accountType);
	
    int countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    List<UserBO> selectByExample(UserExample example);

    UserBO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}