package com.smart119.system.config;

import com.smart119.system.filter.XssFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.schema.Maps;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class XssConfig{

	/**
	 * xss过滤拦截器
	 */
	@Bean
	public FilterRegistrationBean xssFilterRegistrationBean() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new XssFilter());
		filterRegistrationBean.setOrder(1);
		filterRegistrationBean.setEnabled(false);
		filterRegistrationBean.addUrlPatterns("/*");
		Map<String, String> initParameters = new HashMap<>();
		initParameters.put("excludes", "/img/*,/js/*,/css/*");
		initParameters.put("isIncludeRichText", "true");
		filterRegistrationBean.setInitParameters(initParameters);
		return filterRegistrationBean;
	}
}
