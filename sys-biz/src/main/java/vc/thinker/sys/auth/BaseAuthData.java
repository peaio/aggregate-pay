package vc.thinker.sys.auth;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sinco.data.core.auth.AuthData;

import vc.thinker.sys.bo.RoleBO;
import vc.thinker.sys.bo.UserBO;
import vc.thinker.sys.contants.AuthConstants;
import vc.thinker.sys.utils.AdminUtils;

/**
 * 用户数据权限过滤基类
 * @author james
 *
 */
public abstract class BaseAuthData implements AuthData{
	
	private static final Logger log=LoggerFactory.getLogger(BaseAuthData.class);

	private UserBO user;
	
	
	
	public BaseAuthData(UserBO user) {
		this.user = user;
	}

	@Override
	public String authHandle(String sql) {
		
		
		StringBuilder where=new StringBuilder();

		// 超级管理员，跳过权限过滤
		if (AdminUtils.isAdmin(user.getId())){
			log.info("超级管理员,未进行权限控制");
			return sql;
		}
		
		RoleBO r=getDataAuthRole();
		
		if(r == null){
			log.info("role is null,未进行权限控制");
			return sql;
		}

		boolean isDataScopeAll = false;
		
		if (AuthConstants.DATA_SCOPE_ALL.equals(r.getDataScope())) {
			isDataScopeAll = true;

		} else if (AuthConstants.DATA_SCOPE_COMPANY_AND_CHILD.equals(r
				.getDataScope())) {
			where.append(" (so.parent_ids like ").append("'%,")
					.append(user.getCompanyId()).append(",%'");
		} else if (AuthConstants.DATA_SCOPE_COMPANY.equals(r.getDataScope())) {
			where.append(" (so.id=").append(user.getCompanyId())
					.append(" or so.parent_id = ")
					.append(user.getCompanyId());

		} else if (AuthConstants.DATA_SCOPE_OFFICE_AND_CHILD.equals(r.getDataScope())) {
			where.append(" (so.parent_ids like ").append("'%,")
					.append(user.getOffice().getId()).append(",%'");

		} else if (AuthConstants.DATA_SCOPE_OFFICE.equals(r.getDataScope())) {
			where.append(" (so.id = ").append(user.getOfficeId());

		} else if (AuthConstants.DATA_SCOPE_SELF.equals(r.getDataScope())) {
			where.append(" (su.id =").append(user.getId());
		}
//		if (AuthConstants.DATA_SCOPE_CUSTOM.equals(r.getDataScope())) {
//			where.append(" so.id in (").append(StringUtils.join(r.getOfficeIdList(), ","))
//					;
//		}
		if(StringUtils.isNotBlank(where.toString())){
			where.append(") ");
		}
		
		//如果所有权限
		if(isDataScopeAll){
			return sql;
		}
		
		return assembledSql(sql, where.toString());
	}
	
	private String assembledSql(String oldSql,String where){
		 oldSql=oldSql.toUpperCase();
		 //是否找到FROM
		 if(oldSql.lastIndexOf("FROM") <= 0){
			 return oldSql;
		 }
		 String sqlSelect=oldSql.substring(0, oldSql.lastIndexOf("FROM"));
		 
		 String sqlFrom=oldSql.substring(oldSql.lastIndexOf("FROM"));
		 
		 String sqlWhere="";
		 if(sqlFrom.lastIndexOf("WHERE") > 0){
			 sqlWhere=sqlFrom.substring(sqlFrom.lastIndexOf("WHERE"));
			 sqlFrom=sqlFrom.substring(0,sqlFrom.lastIndexOf("WHERE"));
			 
		 }else if(sqlFrom.lastIndexOf("ORDER") > 0){
			 sqlWhere=new StringBuilder(" WHERE ").append(sqlFrom.substring(sqlFrom.lastIndexOf("ORDER"))).toString();
			 sqlFrom=sqlFrom.substring(0,sqlFrom.lastIndexOf("ORDER"));
			 
		 }else if(sqlFrom.lastIndexOf("GROUP") > 0){
			 sqlWhere=new StringBuilder(" WHERE ").append(sqlFrom.substring(sqlFrom.lastIndexOf("GROUP"))).toString();
			 sqlFrom=sqlFrom.substring(0,sqlFrom.lastIndexOf("GROUP"));
			 
		 }else if(sqlFrom.lastIndexOf("LIMIT") > 0){
			 sqlWhere=new StringBuilder(" WHERE ").append(sqlFrom.substring(sqlFrom.lastIndexOf("LIMIT"))).toString();
			 sqlFrom=sqlFrom.substring(0,sqlFrom.lastIndexOf("LIMIT"));
		 }
		 
		 //join 组合
		 String joinTable=joinTable(sqlFrom);
		 
		 sqlFrom=new StringBuilder(sqlFrom).append(joinTable).toString();
		
		 //where 组合 
		 sqlWhere=sqlWhere.replace("WHERE",  new StringBuilder("WHERE ").append(where).append(" AND ").toString());
		 
		 
		 return new StringBuilder(sqlSelect).append(sqlFrom).append(sqlWhere).toString();
	} 
	
	/**
	 * 使用join table
	 * @param sqlFrom
	 * @return
	 */
	public abstract String joinTable(String sqlFrom);
	
	
	/**
	 * 查找权限最大的角色
	 * @return
	 */
	private RoleBO getDataAuthRole(){
		if(user.getRoleList() == null){
			return null;
		}
		RoleBO role=null;
		for (RoleBO r : user.getRoleList()) {
			if(role== null){
				role=r;
			}else{
				Integer i1= Integer.parseInt(r.getDataScope());
				Integer i2= Integer.parseInt(role.getDataScope());
				if(i1 < i2){
					role=r;
				}
			}
		}
		return role;
	}
}
