package vc.thinker.cabbage.core.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;

import com.alibaba.druid.pool.DruidDataSource;

@MapperScan(basePackages="vc.thinker.**.mapper")
@ConfigurationProperties(prefix = "jdbc")
public class DatabaseConfig {

	@Value("${jdbc.url}")
	private String url;
	@Value("${jdbc.username}")
	private String username;
	@Value("${jdbc.password}")
	private String password;
	@Value("${jdbc.pool.initialSize}")
	private int initialSize;
	@Value("${jdbc.pool.minIdle}")
	private int minIdle;
	@Value("${jdbc.pool.maxActive}")
	private int maxActive;
	@Value("${jdbc.driver}")
	private String driverClassName;
	
	@Value("${mybatis.typeAliasesPackage}")
	private String typeAliasesPackage;
	@Value("${mybatis.configLocation}")
	private String mybatisConfigLocation;


    @Bean(initMethod="init",destroyMethod="close")
    public DataSource dataSource() {
    	DruidDataSource datasource = new DruidDataSource();
    	//基本属性 url、user、password
    	datasource.setUrl(url);
    	datasource.setUsername(username);
    	datasource.setPassword(password);
    	//配置初始化大小、最小、最大
    	datasource.setInitialSize(initialSize);
    	datasource.setMinIdle(minIdle);
    	datasource.setMaxActive(maxActive);
    	datasource.setDriverClassName(driverClassName);
    	
    	//配置获取连接等待超时的时间
    	datasource.setMaxWait(60000);
    	//配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
    	datasource.setTimeBetweenEvictionRunsMillis(60000);
    	//配置一个连接在池中最小生存的时间，单位是毫秒
    	datasource.setMinEvictableIdleTimeMillis(300000);
        datasource.setValidationQuery("SELECT 'x'");
        datasource.setTestWhileIdle(true);
        datasource.setTestOnBorrow(true);
        datasource.setTestOnReturn(true);
         //打开PSCache，并且指定每个连接上PSCache的大小
        datasource.setPoolPreparedStatements(true);
        datasource.setMaxPoolPreparedStatementPerConnectionSize(20);
        //配置监控统计拦截的filters
        try {
			datasource.setFilters("stat");
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return datasource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setTypeAliasesPackage(typeAliasesPackage);
        if(StringUtils.isBlank(mybatisConfigLocation)){
        	mybatisConfigLocation="/cabbage-mybatis-config.xml";
        }
        sessionFactory.setConfigLocation(new ClassPathResource(mybatisConfigLocation));
        return sessionFactory.getObject();
    }
}
