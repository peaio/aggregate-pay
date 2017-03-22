package vc.thinker.cabbage.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.sinco.dic.client.DicContent;
import com.sinco.dic.client.DicNameMappingHandle;
import com.sinco.dic.client.cache.RemoteDataCached;


@ConfigurationProperties(prefix = "redis")
public class DicConfig {
	
	@Value("${redis.host}")
	private String host;
	
	@Value("${redis.port}")
	private Integer port;
	
	@Value("${redis.password}")
	private String password;
	
	
	@Bean public RemoteDataCached dataCached(){
		return new RemoteDataCached(host,port,password);
	}
	
	@Bean public DicContent dicContent(){
		return new DicContent(dataCached());
	}
	@Bean public DicNameMappingHandle dicNameMappingHandle(){
		return new DicNameMappingHandle(dicContent());
	}
}
