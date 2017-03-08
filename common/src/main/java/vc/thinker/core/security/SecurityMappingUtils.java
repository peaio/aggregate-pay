package vc.thinker.core.security;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import vc.thinker.core.security.SecurityMapping;
import vc.thinker.core.security.SecurityMappingModel;
import vc.thinker.utils.ClassUtils;

/**
 * 用于权限初始化处理
 * @author james
 *
 */
public class SecurityMappingUtils {
	
	public static final Logger log= LoggerFactory.getLogger(SecurityMappingUtils.class);

	/**
	 * 查找所有权限
	 * @return
	 */
	public static List<SecurityMappingModel> findSecurity(String pack){
		//统一加载所有 action class
		Set<Class<?>> clzs = ClassUtils.getClasses(pack,Controller.class);
		
		log.info("扫描到的 Controller class:{}",clzs);
		List<SecurityMappingModel> mappings = new ArrayList<SecurityMappingModel>();
		for (Class clz : clzs) {
			Method[] ms = clz.getMethods();
			for (Method m : ms) {
				Annotation[] annotation = m.getAnnotations();
				SecurityMapping sm=null;
				RequiresPermissions rp=null;
				for (Annotation tag : annotation) {
					if (SecurityMapping.class.isAssignableFrom(tag.annotationType())) {
						sm=(SecurityMapping)tag;
					}
					if(RequiresPermissions.class.isAssignableFrom(tag.annotationType())){
						rp=(RequiresPermissions)tag;
					}
				}
				if(sm!=null && rp!=null){
					SecurityMappingModel smm=new SecurityMappingModel();
					smm.setName(sm.name());
					smm.setValue(rp.value()[0]);
					smm.setSequence(sm.sequence());
					smm.setUserType(sm.userType());
					smm.setPermGroup(sm.permGroup());
					mappings.add(smm);
				}
			}
		}
		return mappings;
	}
}
