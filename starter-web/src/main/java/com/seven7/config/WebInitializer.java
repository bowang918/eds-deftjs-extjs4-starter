package com.seven7.config;

import javax.servlet.FilterRegistration.Dynamic;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.eclipse.jetty.servlets.GzipFilter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { WebConfig.class, ComponentConfig.class, DataConfig.class ,SecurityConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);
		servletContext.addFilter("characterEncodingFilter", characterEncodingFilter).addMappingForUrlPatterns(null, false, "/*");

		
		//gzip those js/img/css/json/html files
		GzipFilter gzipFilter = new GzipFilter();
		Dynamic gzipFilterDynamic = servletContext.addFilter("gzipFilter", gzipFilter);
		gzipFilterDynamic.setInitParameter("mimeTypes", "text/html,text/plain,text/xml,application/xhtml+xml,text/css,application/javascript,image/svg+xml,application/json");
		gzipFilterDynamic.addMappingForUrlPatterns(null, false, "/*");
		super.onStartup(servletContext);
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/services","/services/*" };
	}

}
