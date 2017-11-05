package de.m1well.spring.security.sample.webapp.security;

import de.m1well.spring.security.sample.webapp.model.AppToken;
import de.m1well.spring.security.sample.webapp.repository.AppTokenRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * author: Michael Wellner<br/>
 * date: 27.10.2017<br/>
 */
@Repository("customPersistentTokenRepository")
@Transactional
public class CustomPersistentTokenRepository implements PersistentTokenRepository {

    static final Logger LOG = LoggerFactory.getLogger(CustomPersistentTokenRepository.class);

    @Autowired
    AppTokenRepository appTokenRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public void createNewToken(PersistentRememberMeToken token) {
        LOG.info("### creating appToken for user: ", token.getUsername());
        AppToken appToken = new AppToken();
        appToken.setUsername(token.getUsername());
        appToken.setSeries(token.getSeries());
        appToken.setToken(token.getTokenValue());
        appToken.setLast_used(token.getDate());
        appTokenRepository.save(appToken);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PersistentRememberMeToken getTokenForSeries(String series) {
        LOG.info("### fetch appToken if any available for series: ", series);
        try {
            AppToken appToken = appTokenRepository.findBySeries(series);
            return new PersistentRememberMeToken(appToken.getUsername(), appToken.getSeries(),
                    appToken.getToken(), appToken.getLast_used());
        } catch (Exception e) {
            LOG.info("### appToken was not found...");
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeUserTokens(String username) {
        LOG.info("### removing appTokens if any available for user: ", username);
        List<AppToken> appTokens = appTokenRepository.findAllByUsername(username);
        if (appTokens != null) {
            for (AppToken appToken : appTokens) {
                appTokenRepository.delete(appToken);
            }
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateToken(String series, String tokenValue, Date lastUsed) {
        LOG.info("### updating app token for series: ", series);
        AppToken appToken = appTokenRepository.findBySeries(series);
        appToken.setToken(tokenValue);
        appToken.setLast_used(lastUsed);
        appTokenRepository.save(appToken);
    }

}
