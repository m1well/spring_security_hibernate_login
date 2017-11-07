package de.m1well.spring.security.sample.webapp.security;

import de.m1well.spring.security.sample.webapp.model.AppAuthority;
import de.m1well.spring.security.sample.webapp.model.AppUser;
import de.m1well.spring.security.sample.webapp.service.AppUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * author: Michael Wellner<br/>
 * date: 26.10.2017<br/>
 */
@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    static final Logger LOG = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Autowired
    private AppUserService appUserService;

    /**
     * provides spring userdetails by logged in username
     *
     * @param username logged in username
     * @return UserDetails
     * @throws UsernameNotFoundException
     */
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = appUserService.findByUsername(username);
        LOG.info("### appUser who wants to login: ", appUser.toString());
        if (appUser == null) {
            LOG.info("### appUser not found");
            throw new UsernameNotFoundException("### username not found");
        }
        return new User(appUser.getUsername(), appUser.getPassword(), true, true, true, true, getGrantedAuthorities(appUser));
    }

    /**
     * provides list of granted authorities from a user
     *
     * @param appUser user
     * @return List<GrantedAuthority>
     */
    private List<GrantedAuthority> getGrantedAuthorities(AppUser appUser) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        for (AppAuthority appAuthority : appUser.getAppAuthorities()) {
            LOG.info("### appAuthority: ", appAuthority);
            authorities.add(new SimpleGrantedAuthority("ROLE_" + appAuthority.getType()));
        }
        LOG.info("### authorities: ", authorities);
        return authorities;
    }

}