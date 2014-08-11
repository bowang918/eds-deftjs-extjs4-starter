package com.seven7.config;

import javax.naming.NameNotFoundException;
import javax.naming.NamingException;
import javax.servlet.FilterRegistration.Dynamic;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.eclipse.jetty.servlets.GzipFilter;
import org.springframework.jndi.JndiLocatorDelegate;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import ch.rasc.edsutil.optimizer.WebResourceProcessor;

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
		
		String profile = System.getProperty("spring.profiles.active");
		if (profile == null) {
			try {
				profile = (String) JndiLocatorDelegate.createDefaultResourceRefLocator().lookup("spring.profiles.active");
			} catch (NameNotFoundException ne) {
				// do nothing
			} catch (NamingException e) {
				throw new ServletException(e);
			}
		}

		//process js files with different environment, minify js files in production env
		WebResourceProcessor processor = new WebResourceProcessor(servletContext, !"development".equals(profile));
		processor.setResourceServletPath("/resources/");
		processor.process();
		
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);
		servletContext.addFilter("characterEncodingFilter", characterEncodingFilter).addMappingForUrlPatterns(null, false, "/*");

		
		//gzip those img/json/html files
		GzipFilter gzipFilter = new GzipFilter();
		Dynamic gzipFilterDynamic = servletContext.addFilter("gzipFilter", gzipFilter);
		gzipFilterDynamic.setInitParameter("mimeTypes", "text/html,text/plain,text/xml,application/xhtml+xml,image/svg+xml,application/json");
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
