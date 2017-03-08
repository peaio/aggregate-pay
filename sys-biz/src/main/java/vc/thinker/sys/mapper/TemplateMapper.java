package vc.thinker.sys.mapper;


import java.util.List;
import org.apache.ibatis.annotations.Param;

import vc.thinker.core.dal.MyPage;
import vc.thinker.sys.bo.TemplateBO;
import vc.thinker.sys.model.Template;
import vc.thinker.sys.model.TemplateExample;
import vc.thinker.sys.vo.TemplateVO;

public interface TemplateMapper {
    int countByExample(TemplateExample example);

    int deleteByExample(TemplateExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Template record);

    int insertSelective(Template record);

    List<TemplateBO> selectByExampleWithBLOBs(TemplateExample example);

    List<TemplateBO> selectByExample(TemplateExample example);

    TemplateBO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Template record, @Param("example") TemplateExample example);

    int updateByExampleWithBLOBs(@Param("record") Template record, @Param("example") TemplateExample example);

    int updateByExample(@Param("record") Template record, @Param("example") TemplateExample example);

    int updateByPrimaryKeySelective(Template record);

    int updateByPrimaryKeyWithBLOBs(Template record);

    int updateByPrimaryKey(Template record);
    
    List<TemplateBO> selectListByVO(MyPage<TemplateBO> page,@Param("vo") TemplateVO vo);
}