package vc.thinker.sys.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sinco.data.core.Page;

import vc.thinker.sys.bo.LogBO;
import vc.thinker.sys.model.Dict;
import vc.thinker.sys.model.Log;
import vc.thinker.sys.model.LogExample;

public interface LogMapper {
    int countByExample(LogExample example);

    int deleteByExample(LogExample example);

    int deleteByPrimaryKey(String id);

    int insert(Log record);

    int insertSelective(Log record);

    List<Log> selectByExample(LogExample example);

    Log selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Log record, @Param("example") LogExample example);

    int updateByExample(@Param("record") Log record, @Param("example") LogExample example);

    int updateByPrimaryKeySelective(Log record);

    int updateByPrimaryKey(Log record);
    
    List<LogBO> findByPage(@Param("page") Page<LogBO> page,@Param("param") Map<String, Object> param);
}