package de.m1well.spring.security.sample.webapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * author: Michael Wellner<br/>
 * date: 26.10.2017<br/>
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"de.m1well.spring.security.sample.webapp.repository"})
@ComponentScan({"de.m1well.spring.security.sample.webapp.config"})
@PropertySource(value = {"classpath:application.properties"})
public class PersistenceConfig {

    @Autowired
    private Environment environment;

    /**
     * provides a bean of database connection - details in the application.properties file
     *
     * @return DriverManagerDataSource
     */
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
        dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
        dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
        dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
        return dataSource;
    }

    /**
     * provides a bean of entitymanager factory
     *
     * @return LocalContainerEntityManagerFactoryBean
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(new String[]{"de.m1well.spring.security.sample.webapp.model"});
        em.setJpaVendorAdapter(jpaVendorAdapter());
        em.setJpaProperties(hibernateProperties());
        return em;
    }

    /**
     * provides a bean of jpa vendoradapter
     *
     * @return HibernateJpaVendorAdapter
     */
    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        return new HibernateJpaVendorAdapter();
    }

    /**
     * provides a bean of database transaction manager with entitymanager factory
     *
     * @param emf EntityManagerFactory
     * @return JpaTransactionManager
     */
    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }

    /**
     * provides hibernate properties from application.properties file
     *
     * @return Properties
     */
    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
        return properties;
    }

}