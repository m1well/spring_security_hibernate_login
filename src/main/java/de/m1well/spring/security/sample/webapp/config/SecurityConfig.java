package de.m1well.spring.security.sample.webapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

/**
 * author: Michael Wellner<br/>
 * date: 26.10.2017<br/>
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public static final int TOKEN_VALIDITY_SECONDS = 60 * 10; // 10 minutes

    @Autowired
    @Qualifier("customUserDetailsService")
    UserDetailsService userDetailsService;

    @Autowired
    @Qualifier("customPersistentTokenRepository")
    PersistentTokenRepository persistentTokenRepository;

    /**
     * provides authenticationmanager builder with own userdetails service and authentication provider
     *
     * @param auth AuthenticationManagerBuilder
     * @throws Exception
     */
    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
        auth.authenticationProvider(authenticationProvider());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/", "/list").access("hasRole('USER') or hasRole('ADMIN')")
                    .antMatchers("/newuser/**", "/delete-user-*").access("hasRole('ADMIN')")
                    .antMatchers("/edit-user-*").access("hasRole('ADMIN')")
                .and()
                    .formLogin().loginPage("/login")
                    .loginProcessingUrl("/login").usernameParameter("username").passwordParameter("password")
                .and()
                    .rememberMe().rememberMeParameter("remember-me")
                    .tokenRepository(persistentTokenRepository)
                    .tokenValiditySeconds(TOKEN_VALIDITY_SECONDS)
                .and()
                    .csrf()
                .and()
                    .exceptionHandling().accessDeniedPage("/access-denied");
    }

    /**
     * provides a bean of password encoder for password encryption
     *
     * @return BCryptPasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * provides a bean of dao authentication provider with own userdetails service and password encoder
     *
     * @return DaoAuthenticationProvider
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    /**
     * provides a bean of "remember me" service based on a persistent token
     *
     * @return PersistentTokenBasedRememberMeServices
     */
    @Bean
    public PersistentTokenBasedRememberMeServices getPersistentTokenBasedRememberMeServices() {
        return new PersistentTokenBasedRememberMeServices("remember-me", userDetailsService, persistentTokenRepository);
    }

    /**
     * provides a bean of an authentication trustresolver
     *
     * @return AuthenticationTrustResolver
     */
    @Bean
    public AuthenticationTrustResolver getAuthenticationTrustResolver() {
        return new AuthenticationTrustResolverImpl();
    }

}