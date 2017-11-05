package de.m1well.spring.security.sample.webapp.service;

import de.m1well.spring.security.sample.webapp.model.AppUser;

import java.util.List;

/**
 * author: Michael Wellner<br/>
 * date: 26.10.2017<br/>
 */
public interface AppUserService {

    /**
     * provides an user found by id
     *
     * @param id user id
     * @return AppUser
     */
    AppUser findById(Integer id);

    /**
     * provides an user found by username
     *
     * @param username user username
     * @return AppUser
     */
    AppUser findByUsername(String username);

    /**
     * provides the saving of an user
     *
     * @param appUser user to save
     */
    void save(AppUser appUser);

    /**
     * provides an update of an user
     *
     * @param appUser user to update
     */
    void update(AppUser appUser);

    /**
     * provides a deletion of an user
     *
     * @param username username of user to delete
     */
    void deleteByUsername(String username);

    /**
     * provides a list of all users
     *
     * @return List<AppUser>
     */
    List<AppUser> findAll();

    /**
     * checks if the username is unique
     *
     * @param id       user id
     * @param username user username
     * @return true if username is unique, false if not
     */
    boolean isUsernameUnique(Integer id, String username);

    /**
     * checks if there is more than one admin (so you can't change authorities of last admin)
     *
     * @return true if there is more than one, else false
     */
    boolean isMoreThanOneAdmin();

}
