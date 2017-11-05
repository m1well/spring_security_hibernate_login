package de.m1well.spring.security.sample.webapp.service.impl;

import de.m1well.spring.security.sample.webapp.model.AppAuthorityType;
import de.m1well.spring.security.sample.webapp.model.AppUser;
import de.m1well.spring.security.sample.webapp.repository.AppAuthorityRepository;
import de.m1well.spring.security.sample.webapp.repository.AppUserRepository;
import de.m1well.spring.security.sample.webapp.service.AppUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * author: Michael Wellner<br/>
 * date: 26.10.2017<br/>
 */
@Service
@Transactional
public class AppUserServiceImpl implements AppUserService {

    static final Logger LOG = LoggerFactory.getLogger(AppUserServiceImpl.class);

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private AppAuthorityRepository appAuthorityRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public AppUser findById(Integer id) {
        return appUserRepository.findOne(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AppUser findByUsername(String username) {
        LOG.info("### find appUser by username: " + username);
        return appUserRepository.findByUsername(username);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(AppUser appUser) {
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        appUserRepository.save(appUser);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(AppUser appUser) {
        AppUser entity = appUserRepository.findOne(appUser.getId());
        if (entity != null) {
            entity.setUsername(appUser.getUsername());
            if (!appUser.getPassword().equals(entity.getPassword())) {
                entity.setPassword(passwordEncoder.encode(appUser.getPassword()));
            }
            entity.setFirstName(appUser.getFirstName());
            entity.setLastName(appUser.getLastName());
            entity.setEmail(appUser.getEmail());
            entity.setAppAuthorities(appUser.getAppAuthorities());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteByUsername(String username) {
        AppUser entity = appUserRepository.findByUsername(username);
        if (entity != null) {
            appUserRepository.delete(entity.getId());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<AppUser> findAll() {
        return appUserRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isUsernameUnique(Integer id, String username) {
        AppUser entity = appUserRepository.findByUsername(username);
        return (entity == null || ((id != null) && (entity.getId() == id)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isMoreThanOneAdmin() {
        return appUserRepository.countAllAdmins() > 1;
    }

}