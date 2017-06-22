package com.multi.test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@EnableWebMvc
public class SpringConfig extends WebMvcConfigurerAdapter {

	public static final String[] WEB_JAR_RESOURCE_PATTERNS = { "css/", "images/", "lib/", "swagger-ui.js" };
	public static final String WEB_JAR_RESOURCE_LOCATION = "classpath:META-INF/resources/";
	public static final String WEB_JAR_VIEW_RESOLVER_PREFIX = "classpath:/resources/";
	public static final String WEB_JAR_VIEW_RESOLVER_SUFFIX = ".jsp";

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler(WEB_JAR_RESOURCE_PATTERNS).addResourceLocations(WEB_JAR_RESOURCE_LOCATION)
				.setCachePeriod(0);
	}

	
	@Bean
	public InternalResourceViewResolver getInternalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix(WEB_JAR_VIEW_RESOLVER_PREFIX);
		resolver.setSuffix(WEB_JAR_VIEW_RESOLVER_SUFFIX);
		return resolver;
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
}