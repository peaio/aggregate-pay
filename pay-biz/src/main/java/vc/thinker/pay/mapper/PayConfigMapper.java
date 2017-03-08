package vc.thinker.pay.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import vc.thinker.pay.bo.PayConfigBO;
import vc.thinker.pay.model.PayConfig;
import vc.thinker.pay.model.PayConfigExample;

public interface PayConfigMapper {
    int countByExample(PayConfigExample example);

    int deleteByExample(PayConfigExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PayConfig record);

    int insertSelective(PayConfig record);

    List<PayConfigBO> selectByExample(PayConfigExample example);

    PayConfigBO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PayConfig record, @Param("example") PayConfigExample example);

    int updateByExample(@Param("record") PayConfig record, @Param("example") PayConfigExample example);

    int updateByPrimaryKeySelective(PayConfig record);

    int updateByPrimaryKey(PayConfig record);
}