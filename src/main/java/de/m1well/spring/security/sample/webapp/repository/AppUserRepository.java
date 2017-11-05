package de.m1well.spring.security.sample.webapp.repository;

import de.m1well.spring.security.sample.webapp.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * author: Michael Wellner<br/>
 * date: 26.10.2017<br/>
 */
public interface AppUserRepository extends JpaRepository<AppUser, Integer> {

    /**
     * provides user by username
     *
     * @param username username
     * @return AppUser
     */
    AppUser findByUsername(String username);

    /**
     * provides count of admins
     *
     * @return Long
     */
    @Query(value = "SELECT COUNT(*) FROM app_users u WHERE u.id IN " +
            "(SELECT u2a.app_user FROM app_users_2_authorities u2a JOIN app_authorities a " +
            "ON u2a.app_authority = a.id WHERE a.type = 'ADMIN')", nativeQuery = true)
    Long countAllAdmins();

}