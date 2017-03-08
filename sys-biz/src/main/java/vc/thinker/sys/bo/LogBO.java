package vc.thinker.sys.bo;


import vc.thinker.sys.model.Log;
/**
 * 
 * BO 用于返回数据
 *
 */
public class LogBO extends Log{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String companyName;
	
	private String officeName;
	
	private String createByName;

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	public String getCreateByName() {
		return createByName;
	}

	public void setCreateByName(String createByName) {
		this.createByName = createByName;
	}
}