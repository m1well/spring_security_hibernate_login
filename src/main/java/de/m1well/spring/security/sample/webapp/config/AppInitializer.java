package de.m1well.spring.security.sample.webapp.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * author: Michael Wellner<br/>
 * date: 26.10.2017<br/>
 */
public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     * {@inheritDoc}
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{WebAppConfig.class};
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

}