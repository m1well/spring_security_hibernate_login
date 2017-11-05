package de.m1well.spring.security.sample.webapp.service.impl;

import de.m1well.spring.security.sample.webapp.model.AppAuthority;
import de.m1well.spring.security.sample.webapp.model.AppAuthorityType;
import de.m1well.spring.security.sample.webapp.repository.AppAuthorityRepository;
import de.m1well.spring.security.sample.webapp.service.AppAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * author: Michael Wellner<br/>
 * date: 26.10.2017<br/>
 */
@Service
@Transactional
public class AppAuthorityServiceImpl implements AppAuthorityService {

    @Autowired
    AppAuthorityRepository appAuthorityRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public AppAuthority findById(Integer id) {
        return appAuthorityRepository.findOne(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AppAuthority findByType(String type) {
        return appAuthorityRepository.findByType(type);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<AppAuthority> findAll() {
        return appAuthorityRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAdminByUsername(String username) {
        List<AppAuthorityType> authorityTypes = appAuthorityRepository.findAllAuthorityTypesByUsername(username);
        return authorityTypes.contains(AppAuthorityType.ADMIN.getAppAuthorityType());
    }
}
