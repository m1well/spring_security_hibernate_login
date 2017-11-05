package de.m1well.spring.security.sample.webapp.repository;

import de.m1well.spring.security.sample.webapp.model.AppAuthority;
import de.m1well.spring.security.sample.webapp.model.AppAuthorityType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * author: Michael Wellner<br/>
 * date: 26.10.2017<br/>
 */
public interface AppAuthorityRepository extends JpaRepository<AppAuthority, Integer> {

    /**
     * provides authority by type
     *
     * @param type authority type
     * @return AppAuthority
     */
    AppAuthority findByType(String type);

    /**
     * provides all authorities by username
     *
     * @param username username
     * @return List<AppAuthorityType>
     */
    @Query(value = "SELECT a.type FROM app_authorities a WHERE a.id IN " +
            "(SELECT u2a.app_authority FROM app_users_2_authorities u2a JOIN app_users u " +
            "ON u2a.app_user = u.id WHERE u.username = :username)", nativeQuery = true)
    List<AppAuthorityType> findAllAuthorityTypesByUsername(@Param("username") String username);

}
