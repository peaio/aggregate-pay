package vc.thinker.actuator.loggers;


import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.impl.StaticLoggerBinder;
import org.springframework.boot.actuate.endpoint.AbstractEndpoint;
import org.springframework.boot.actuate.endpoint.Endpoint;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.logging.LogLevel;
import org.springframework.boot.logging.LoggingSystem;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;

/**
 * {@link Endpoint} to expose a collection of {@link LoggerConfiguration}s.
 *
 * @author Ben Hale
 * @author Phillip Webb
 * @since 1.5.0
 */
@ConfigurationProperties(prefix = "endpoints.loggers")
public class LoggersEndpoint extends AbstractEndpoint<Map<String, Object>> {

	private final LoggingSystem loggingSystem;

	/**
	 * Create a new {@link LoggersEndpoint} instance.
	 * @param loggingSystem the logging system to expose
	 */
	public LoggersEndpoint(LoggingSystem loggingSystem) {
		super("loggers");
		Assert.notNull(loggingSystem, "LoggingSystem must not be null");
		this.loggingSystem = loggingSystem;
	}

	@Override
	public Map<String, Object> invoke() {
		List<ch.qos.logback.classic.Logger> configurations = this.getLogger();
		if (configurations == null) {
			return Collections.emptyMap();
		}
		Map<String, Object> result = new LinkedHashMap<String, Object>();
		result.put("loggers", getLoggers(configurations));
		return result;
	}
	
	private ch.qos.logback.classic.Logger getLogger(String name) {
		LoggerContext factory = getLoggerContext();
		return factory
				.getLogger(StringUtils.isEmpty(name) ? Logger.ROOT_LOGGER_NAME : name);

	}
	private List<ch.qos.logback.classic.Logger> getLogger() {
		LoggerContext factory = getLoggerContext();
		return factory.getLoggerList();
	}

	private LoggerContext getLoggerContext() {
		ILoggerFactory factory = StaticLoggerBinder.getSingleton().getLoggerFactory();
		Assert.isInstanceOf(LoggerContext.class, factory,
				String.format(
						"LoggerFactory is not a Logback LoggerContext but Logback is on "
								+ "the classpath. Either remove Logback or the competing "
								+ "implementation (%s loaded from %s). If you are using "
								+ "WebLogic you will need to add 'org.slf4j' to "
								+ "prefer-application-packages in WEB-INF/weblogic.xml",
						factory.getClass(), getLocation(factory)));
		return (LoggerContext) factory;
	}
	
	private Object getLocation(ILoggerFactory factory) {
		try {
			ProtectionDomain protectionDomain = factory.getClass().getProtectionDomain();
			CodeSource codeSource = protectionDomain.getCodeSource();
			if (codeSource != null) {
				return codeSource.getLocation();
			}
		}
		catch (SecurityException ex) {
			// Unable to determine location
		}
		return "unknown location";
	}

	private Map<String, LoggerLevels> getLoggers(List<ch.qos.logback.classic.Logger> configurations) {
		Map<String, LoggerLevels> loggers = new LinkedHashMap<String, LoggerLevels>(
				configurations.size());
		for (ch.qos.logback.classic.Logger configuration : configurations) {
			loggers.put(configuration.getName(), new LoggerLevels(configuration));
		}
		return loggers;
	}

	public LoggerLevels invoke(String name) {
		Assert.notNull(name, "Name must not be null");
		ch.qos.logback.classic.Logger configuration = getLogger(name);
		return (configuration == null ? null : new LoggerLevels(configuration));
	}

	public void setLogLevel(String name, LogLevel level) {
		Assert.notNull(name, "Name must not be empty");
		this.loggingSystem.setLogLevel(name, level);
	}

	/**
	 * Levels configured for a given logger exposed in a JSON friendly way.
	 */
	public static class LoggerLevels {

		private String configuredLevel;

		private String effectiveLevel;

		public LoggerLevels(ch.qos.logback.classic.Logger configuration) {
			this.configuredLevel = getName(configuration.getLevel());
			this.effectiveLevel = getName(configuration.getEffectiveLevel());
		}

		private String getName(Level level) {
			return (level == null ? null : level.levelStr);
		}

		public String getConfiguredLevel() {
			return this.configuredLevel;
		}

		public String getEffectiveLevel() {
			return this.effectiveLevel;
		}

	}

}