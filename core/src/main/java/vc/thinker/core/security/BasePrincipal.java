package vc.thinker.core.security;

import java.io.Serializable;

/**
 * 认证主体
 * @author james
 *
 */
public interface BasePrincipal extends Serializable{
	
	public String getId();
	
	public String getLoginName();
}
