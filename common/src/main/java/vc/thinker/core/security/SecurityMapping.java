package vc.thinker.core.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * <p>
 * Title: SecurityMapping.java
 * </p>
 * 
 * <p>
 * Description:系统权限资源标签，
 * 作用 shiro 为基础，目前配合 @RequiresPermissions 使用
 * 
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2015
 * </p>
 * 
 * <p>
 * Company: 深圳市新科聚合网络技术有限公司 www.thinker.vc
 * </p>
 * 
 * @author thinker
 * 
 * @date 2015-5-20
 * 
 * @version 1.0.1
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SecurityMapping {
	String name();// 权限名称

	int sequence() default 0;// 角色编码序号

	String permGroup() default "";// 权限分组

	int userType();// 用户类型
}
