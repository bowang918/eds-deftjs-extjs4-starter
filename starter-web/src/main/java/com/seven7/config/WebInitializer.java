package com.seven7.config;

import javax.servlet.FilterRegistration.Dynamic;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.eclipse.jetty.servlets.GzipFilter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	
	private static final Logger logger = LogManager.getLogger(WebInitializer.class);

	@Override
	protected Class<?>[] getRootConfigClasses() {
		logger.info("****************************WebInitializer start to instance component**************************************");
		return new Class[] { WebConfig.class, ComponentConfig.class, DataConfig.class ,SecurityConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		//@formatter:off
		logger.info("****************************WebInitializer starting**************************************");
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
		logger.info("****************************WebInitializer started**************************************");
		//@formatter:on
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/services/*" };
	}

}
