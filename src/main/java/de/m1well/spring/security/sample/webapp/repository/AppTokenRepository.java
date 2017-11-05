package de.m1well.spring.security.sample.webapp.repository;

import de.m1well.spring.security.sample.webapp.model.AppToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * author: Michael Wellner<br/>
 * date: 27.10.2017<br/>
 */
public interface AppTokenRepository extends JpaRepository<AppToken, String> {

    /**
     * provides app token by series
     *
     * @param series login series
     * @return AppToken
     */
    AppToken findBySeries(String series);

    /**
     * provides all app tokens by username
     *
     * @param username login username
     * @return List<AppToken>
     */
    List<AppToken> findAllByUsername(String username);

}
