package vc.thinker.pay.bo;


import com.sinco.dic.client.model.DicBase;

import vc.thinker.pay.model.PayConfig;
/**
 * 
 * BO 用于返回数据
 *
 */
public class PayConfigBO extends PayConfig implements DicBase{

	@Override
	public String getCode() {
		return this.getMark();
	}

}