package de.m1well.spring.security.sample.webapp.config;

import de.m1well.spring.security.sample.webapp.converter.AppAuthorityConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * author: Michael Wellner<br/>
 * date: 26.10.2017<br/>
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"de.m1well.spring.security.sample.webapp"})
public class WebAppConfig extends WebMvcConfigurerAdapter {

    @Autowired
    AppAuthorityConverter appAuthorityConverter;

    /**
     * provides a bean for view resolver
     *
     * @return InternalResourceViewResolver
     */
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    /**
     * provides a bean for message source
     *
     * @return ResourceBundleMessageSource
     */
    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.setBasename("classpath:messages");
        source.setDefaultEncoding("UTF-8");
        return source;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/assets/**").addResourceLocations("/assets/");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(appAuthorityConverter);
    }

}