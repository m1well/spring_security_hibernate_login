package de.m1well.spring.security.sample.webapp.service;

import de.m1well.spring.security.sample.webapp.model.AppAuthority;

import java.util.List;

/**
 * author: Michael Wellner<br/>
 * date: 26.10.2017<br/>
 */
public interface AppAuthorityService {

    /**
     * provides an authority found by id
     *
     * @param id authority id
     * @return AppAuthority
     */
    AppAuthority findById(Integer id);

    /**
     * provides an authoritiy found by type
     *
     * @param type authority type
     * @return AppAuthority
     */
    AppAuthority findByType(String type);

    /**
     * provides a list of all authorities
     *
     * @return List<AppAuthority>
     */
    List<AppAuthority> findAll();

    /**
     * checks if user is admin
     *
     * @param username user username
     * @return true if is admin, else false
     */
    boolean isAdminByUsername(String username);

}
