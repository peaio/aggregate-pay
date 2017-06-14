package vc.thinker.sys.web.action;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.collect.Lists;
import com.sinco.dic.client.DicContent;
import com.sinco.dic.client.model.DicBase;
import com.sinco.dic.client.model.DicParentBase;

import vc.thinker.core.mapper.JsonMapper;
import vc.thinker.sys.bo.DicAreaBO;
import vc.thinker.sys.model.DicArea;


@Controller
@RequestMapping(value = "/dic")
public class AreaDicController {

	@Autowired
	private DicContent dicContent;
	
	private static final Logger log=LoggerFactory.getLogger(AreaDicController.class);
	
	@RequestMapping(value = "getAreaJsPath", produces = "text/plain")
	public @ResponseBody String getAreaJsPath(HttpServletRequest request){
		String v=dicContent.getDicVersion(DicAreaBO.class);
		return "var dic_area_url='"+request.getContextPath()+"/dic/areas.js?v="+v+"'";
	}
	
		
	@RequestMapping(value = "areas.js")
	public @ResponseBody String getAreas(){
		//只加载中国
		String root="1";
		
		DicAreaBO rootArea=dicContent.getTreeDic(DicAreaBO.class, root);
		if(rootArea == null){
			return "";
		}
		
		List<ScriptEntity> oneEntitys=Lists.newArrayList();
		
		for (DicParentBase oneDic : rootArea.getChildren()) {
			
			ScriptEntity se=new ScriptEntity(oneDic);
			
			oneEntitys.add(se);
			
			Collection<DicAreaBO> towList=oneDic.getChildren();
			
			if(towList != null){
				List<ScriptEntity> towEntitys=Lists.newArrayList();
				se.setChild(towEntitys);
				for (DicParentBase towDic : towList) {
					ScriptEntity towSe=new ScriptEntity(towDic);
					towEntitys.add(towSe);
					Collection<DicAreaBO> threeList=towDic.getChildren();
					if(threeList != null){
						List<ScriptEntity> threeEntitys=Lists.newArrayList();
						towSe.setChild(threeEntitys);
						for (DicBase threeDic : threeList) {
							ScriptEntity threeSe=new ScriptEntity(threeDic);
							threeEntitys.add(threeSe);
						}
					}
				}
			}
		}
		try {
			return "var dic_areas="+JsonMapper.getInstance().writeValueAsString(oneEntitys);
		} catch (JsonProcessingException e) {
			log.error("",e);
		}
		return "";
	}
	
	public static class ScriptEntity{
		private String id;
		private String text;
		
		private List<ScriptEntity> child;
		
		public ScriptEntity(DicBase dic){
			this.id=dic.getCode();
			this.text=dic.getName();
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}

		public List<ScriptEntity> getChild() {
			return child;
		}

		public void setChild(List<ScriptEntity> child) {
			this.child = child;
		}
		
		
	}
}
