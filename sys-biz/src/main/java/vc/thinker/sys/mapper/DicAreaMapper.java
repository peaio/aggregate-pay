package vc.thinker.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import vc.thinker.sys.bo.DicAreaBO;
import vc.thinker.sys.model.DicArea;
import vc.thinker.sys.model.DicAreaExample;

public interface DicAreaMapper {
    int countByExample(DicAreaExample example);

    int deleteByExample(DicAreaExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DicArea record);

    int insertSelective(DicArea record);

    List<DicAreaBO> selectByExample(DicAreaExample example);

    DicAreaBO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DicArea record, @Param("example") DicAreaExample example);

    int updateByExample(@Param("record") DicArea record, @Param("example") DicAreaExample example);

    int updateByPrimaryKeySelective(DicArea record);

    int updateByPrimaryKey(DicArea record);
}