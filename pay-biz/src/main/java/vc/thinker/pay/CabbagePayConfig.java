package vc.thinker.pay;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

/**
 * 支付配制
 * @author james
 *
 */
public class CabbagePayConfig {

	@Bean
	public DBPayConfigFactory dBPayConfigFactory(){
		return new DBPayConfigFactory();
	}
	
	@Bean
	@Lazy(false)
	public PayHandlerFactory payHandlerFactory(DBPayConfigFactory dBPayConfigFactory){
		PayHandlerFactory pay=new PayHandlerFactory(dBPayConfigFactory);
		return pay;
	}	
}
