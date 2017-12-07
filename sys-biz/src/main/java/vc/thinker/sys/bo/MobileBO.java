package vc.thinker.sys.bo;

import com.sinco.common.area.Country;
import com.sinco.common.area.CountryUtil;

/**
 * 手机号
 * 区分所属国家和号码
 * @author james
 *
 */
public class MobileBO {

	private String mobile;
	
	private Country country;
	
	/**
	 * 格式为 86-18575541919
	 * 如未找到"-"默认为中国
	 * @param mobile 原始手机号
	 */
	public MobileBO(String mobile){
		if(mobile.indexOf("-") > 0){
			String[] array=mobile.split("-");
			country=CountryUtil.get(array[0]);
			if(country == null){
				throw new IllegalArgumentException(mobile+"格式错误，未找到对应的国家");
			}
			this.mobile=array[1];
		}else{
			country=CountryUtil.get("86");
			this.mobile=mobile;
		}
	}
	
	public MobileBO(String mobile,String countryCode){
		country=CountryUtil.get(countryCode);
		if(country == null){
			throw new IllegalArgumentException(mobile+"格式错误，未找到对应的国家");
		}
		this.mobile=mobile;
	}
	
	/**
	 * 得到完整手机号
	 * @return
	 */
	public String getFullMobile(){
		return new StringBuilder(country.getCode()).append("-").append(this.getMobile()).toString();
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}
}
