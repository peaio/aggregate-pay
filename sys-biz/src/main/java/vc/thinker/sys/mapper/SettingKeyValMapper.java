package vc.thinker.sys.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import vc.thinker.sys.bo.SettingKeyValBO;
import vc.thinker.sys.model.SettingKeyVal;
import vc.thinker.sys.model.SettingKeyValExample;

public interface SettingKeyValMapper {
	
	int insertUpdate(SettingKeyVal record);
	
    int countByExample(SettingKeyValExample example);

    int deleteByExample(SettingKeyValExample example);

    int deleteByPrimaryKey(String type);

    int insert(SettingKeyVal record);

    int insertSelective(SettingKeyVal record);

    List<SettingKeyValBO> selectByExample(SettingKeyValExample example);

    SettingKeyValBO selectByPrimaryKey(String type);

    int updateByExampleSelective(@Param("record") SettingKeyVal record, @Param("example") SettingKeyValExample example);

    int updateByExample(@Param("record") SettingKeyVal record, @Param("example") SettingKeyValExample example);

    int updateByPrimaryKeySelective(SettingKeyVal record);

    int updateByPrimaryKey(SettingKeyVal record);
}