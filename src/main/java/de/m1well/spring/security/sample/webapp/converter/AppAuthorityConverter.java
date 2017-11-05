package de.m1well.spring.security.sample.webapp.converter;

import de.m1well.spring.security.sample.webapp.model.AppAuthority;
import de.m1well.spring.security.sample.webapp.service.AppAuthorityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * author: Michael Wellner<br/>
 * date: 26.10.2017<br/>
 */
@Component
public class AppAuthorityConverter implements Converter<Object, AppAuthority> {

    static final Logger LOG = LoggerFactory.getLogger(AppAuthorityConverter.class);

    @Autowired
    AppAuthorityService appAuthorityService;

    /**
     * provides the converted authority
     * (convert the frontend string to the authority object)
     *
     * @param element role
     * @return AppAuthority
     */
    public AppAuthority convert(Object element) {
        Integer id = Integer.parseInt((String) element);
        AppAuthority appAuthority = appAuthorityService.findById(id);
        LOG.info("### appAuthority: ", appAuthority);
        return appAuthority;
    }

}