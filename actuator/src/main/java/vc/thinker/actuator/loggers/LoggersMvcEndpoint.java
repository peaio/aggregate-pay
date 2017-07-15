package vc.thinker.actuator.loggers;

import org.springframework.boot.actuate.endpoint.mvc.EndpointMvcAdapter;
import org.springframework.boot.actuate.endpoint.mvc.HypermediaDisabled;
import org.springframework.boot.actuate.endpoint.mvc.MvcEndpoint;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.logging.LogLevel;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import vc.thinker.actuator.loggers.LoggersEndpoint.LoggerLevels;

/**
 * Adapter to expose {@link LoggersEndpoint} as an {@link MvcEndpoint}.
 *
 * @author Ben Hale
 * @author Kazuki Shimizu
 * @since 1.5.0
 */
@ConfigurationProperties(prefix = "endpoints.loggers")
public class LoggersMvcEndpoint extends EndpointMvcAdapter {

	private final LoggersEndpoint delegate;

	public LoggersMvcEndpoint(LoggersEndpoint delegate) {
		super(delegate);
		this.delegate = delegate;
	}

	@RequestMapping(value="/{name:.*}",method={RequestMethod.GET})
	@ResponseBody
	@HypermediaDisabled
	public Object get(@PathVariable String name) {
		if (!this.delegate.isEnabled()) {
			// Shouldn't happen - MVC endpoint shouldn't be registered when delegate's
			// disabled
			return getDisabledResponse();
		}
		LoggerLevels levels = this.delegate.invoke(name);
		return (levels == null ? ResponseEntity.notFound().build() : levels);
	}

	@RequestMapping(value="/{name:.*}",method={RequestMethod.POST})
	@ResponseBody
	@HypermediaDisabled
	public Object set(@PathVariable String name,
			@RequestParam String configuredLevel) {
		if (!this.delegate.isEnabled()) {
			// Shouldn't happen - MVC endpoint shouldn't be registered when delegate's
			// disabled
			return getDisabledResponse();
		}
		LogLevel logLevel = configuredLevel == null ? null : LogLevel.valueOf(configuredLevel.toUpperCase());
		this.delegate.setLogLevel(name, logLevel);
		return HttpEntity.EMPTY;
	}

}
