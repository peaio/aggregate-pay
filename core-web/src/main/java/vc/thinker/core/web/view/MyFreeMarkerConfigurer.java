/*
 * Copyright 2002-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package vc.thinker.core.web.view;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactory;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;

import com.sinco.common.utils.DateUtils;

import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.ext.jsp.TaglibFactory;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.TemplateException;
import freemarker.template.TemplateHashModel;

/**
 * 为了兼容最新版本 FreeMarker ，改了 spring MyFreeMarkerConfigurer
 * @author james
 *
 */
public class MyFreeMarkerConfigurer extends FreeMarkerConfigurationFactory
		implements FreeMarkerConfig, InitializingBean, ResourceLoaderAware, ServletContextAware {

	private Configuration configuration;

	private TaglibFactory taglibFactory;
	
	/**
	 * 应该是开启 java 原生 list map 方法 调用的设置，该设置与 api_builtin_enabled 配合设置（默认关闭）
	 * 开启后可使用 map?api.get(key) 来调用map方法
	 */
	private boolean useAdaptersForContainers=false;


	/**
	 * Set a preconfigured Configuration to use for the FreeMarker web config, e.g. a
	 * shared one for web and email usage, set up via FreeMarkerConfigurationFactoryBean.
	 * If this is not set, FreeMarkerConfigurationFactory's properties (inherited by
	 * this class) have to be specified.
	 * @see org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean
	 */
	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}

	/**
	 * Initialize the {@link TaglibFactory} for the given ServletContext.
	 */
	@Override
	public void setServletContext(ServletContext servletContext) {
		this.taglibFactory = new TaglibFactory(servletContext);
	}


	/**
	 * Initialize FreeMarkerConfigurationFactory's Configuration
	 * if not overridden by a preconfigured FreeMarker Configuation.
	 * <p>Sets up a ClassTemplateLoader to use for loading Spring macros.
	 * @see #createConfiguration
	 * @see #setConfiguration
	 */
	@Override
	public void afterPropertiesSet() throws IOException, TemplateException {
		if (this.configuration == null) {
			this.configuration = createConfiguration();
			DefaultObjectWrapper objectWrapper= (DefaultObjectWrapper) this.getConfiguration().getObjectWrapper();
			objectWrapper.setUseAdaptersForContainers(isUseAdaptersForContainers());
			this.taglibFactory.setObjectWrapper(this.getConfiguration().getObjectWrapper());
		}
	}

	/**
	 * This implementation registers an additional ClassTemplateLoader
	 * for the Spring-provided macros, added to the end of the list.
	 */
	@Override
	protected void postProcessTemplateLoaders(List<TemplateLoader> templateLoaders) {
		templateLoaders.add(new ClassTemplateLoader(MyFreeMarkerConfigurer.class, ""));
		logger.info("ClassTemplateLoader for Spring macros added to FreeMarker configuration");
	}


	/**
	 * Return the Configuration object wrapped by this bean.
	 */
	@Override
	public Configuration getConfiguration() {
		return this.configuration;
	}

	/**
	 * Return the TaglibFactory object wrapped by this bean.
	 */
	@Override
	public TaglibFactory getTaglibFactory() {
		return this.taglibFactory;
	}

	public boolean isUseAdaptersForContainers() {
		return useAdaptersForContainers;
	}

	public void setUseAdaptersForContainers(boolean useAdaptersForContainers) {
		this.useAdaptersForContainers = useAdaptersForContainers;
	}
}
