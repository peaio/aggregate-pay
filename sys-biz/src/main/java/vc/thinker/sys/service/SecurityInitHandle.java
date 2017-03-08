package vc.thinker.sys.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import vc.thinker.core.security.SecurityMappingModel;
import vc.thinker.core.security.SecurityMappingUtils;

/**
 * 用于权限初始化处理
 * @author james
 *
 */
public class SecurityInitHandle {
	private final static Logger log=LoggerFactory.getLogger(SecurityInitHandle.class);
	/**
	 * 扫描包
	 */
	private String pack;
	/**
	 * 是否启动初始化
	 */
	private boolean runInit=false;
	/**
	 * 用户类型
	 */
	private Integer userType;
	
	private SystemService systemService;
	
	
	private void init(){
		if(this.isRunInit()){
			handle();
		}
	}
	
	/**
	 * 执行
	 */
	public void handle(){
		Assert.hasText(pack,"pack is null");
		Assert.notNull(userType,"userType is null");
		
		List<SecurityMappingModel> list= SecurityMappingUtils.findSecurity(pack);
		
		systemService.initSysPermissions(list, userType);
		
		log.info("init Security Complete");
	}
	
	public String getPack() {
		return pack;
	}
	public void setPack(String pack) {
		this.pack = pack;
	}
	
	public boolean isRunInit() {
		return runInit;
	}

	public void setRunInit(boolean runInit) {
		this.runInit = runInit;
	}

	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public SystemService getSystemService() {
		return systemService;
	}

	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}
}
