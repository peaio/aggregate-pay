package vc.thinker.actuator.loggers;

import org.springframework.boot.actuate.condition.ConditionalOnEnabledEndpoint;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.logging.LoggingSystem;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnWebApplication
public class ActuatorConfiguration {
	
	@Bean
	@ConditionalOnMissingBean
	public LoggersEndpoint loggersEndpoint(LoggingSystem loggingSystem) {
		return new LoggersEndpoint(loggingSystem);
	}

	@Bean
	@ConditionalOnBean(LoggersEndpoint.class)
	@ConditionalOnEnabledEndpoint(value = "logger")
	public LoggersMvcEndpoint loggersMvcEndpoint(LoggersEndpoint delegate) {
		return new LoggersMvcEndpoint(delegate);
	}
}
