package vc.thinker.sys.contants;

public interface AuthConstants {
	
	// 数据范围（1：所有数据；2：所在公司及以下数据；3：所在公司数据；4：所在部门及以下数据；5：所在部门数据；10：仅本人数据；9：按明细设置）
	String DATA_SCOPE_ALL = "1";
	String DATA_SCOPE_COMPANY = "3";
	String DATA_SCOPE_COMPANY_AND_CHILD = "2";
	String DATA_SCOPE_CUSTOM = "9";
	String DATA_SCOPE_OFFICE = "5";
	String DATA_SCOPE_OFFICE_AND_CHILD = "4";
	String DATA_SCOPE_SELF = "10";
}
