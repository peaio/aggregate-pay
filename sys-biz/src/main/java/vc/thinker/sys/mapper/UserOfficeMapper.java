package vc.thinker.sys.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import vc.thinker.sys.bo.UserOfficeBO;
import vc.thinker.sys.model.UserOffice;
import vc.thinker.sys.model.UserOfficeExample;

public interface UserOfficeMapper {
    int countByExample(UserOfficeExample example);

    int deleteByExample(UserOfficeExample example);

    int deleteByPrimaryKey(@Param("officeId") Long officeId, @Param("userId") Long userId);

    int insert(UserOffice record);

    int insertSelective(UserOffice record);

    List<UserOfficeBO> selectByExample(UserOfficeExample example);

    int updateByExampleSelective(@Param("record") UserOffice record, @Param("example") UserOfficeExample example);

    int updateByExample(@Param("record") UserOffice record, @Param("example") UserOfficeExample example);
}