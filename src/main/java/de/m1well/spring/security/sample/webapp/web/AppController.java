package de.m1well.spring.security.sample.webapp.web;

import de.m1well.spring.security.sample.webapp.model.AppAuthority;
import de.m1well.spring.security.sample.webapp.model.AppUser;
import de.m1well.spring.security.sample.webapp.service.AppAuthorityService;
import de.m1well.spring.security.sample.webapp.service.AppUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * author: Michael Wellner<br/>
 * date: 26.10.2017<br/>
 */
@RestController
@RequestMapping("")
@SessionAttributes("appAuthorities")
public class AppController {

    static final Logger LOG = LoggerFactory.getLogger(AppController.class);

    @Autowired
    AppUserService appUserService;

    @Autowired
    AppAuthorityService appAuthorityService;

    @Autowired
    MessageSource messageSource;

    @Autowired
    PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;

    @Autowired
    AuthenticationTrustResolver authenticationTrustResolver;

    /**
     * rest endpoint provides list of all users
     *
     * @return users_list jsp page
     */
    @GetMapping(value = {"/", "/list"})
    public ModelAndView listUsers(@ModelAttribute("success") final Object success) {

        ModelAndView mav = new ModelAndView();
        mav.addObject("appUsers", appUserService.findAll());
        mav.addObject("loggedinuser", getPrincipal());
        mav.addObject("success", success);
        mav.setViewName("users_list");
        return mav;

    }

    /**
     * rest endpoint provides registration of a new user
     *
     * @return registration jsp page
     */
    @GetMapping(value = "/newuser")
    public ModelAndView newUser() {

        LOG.info("### get registration jsp page");

        ModelAndView mav = new ModelAndView();
        mav.addObject("appUser", new AppUser());
        mav.addObject("edit", false);
        mav.addObject("loggedinuser", getPrincipal());
        mav.addObject("enableAuthorityList", true);
        mav.setViewName("registration");
        return mav;

    }

    /**
     * rest endpoint provides submission of the new user
     *
     * @param appUser the new user
     * @param result  validation result
     * @return if validation has no error - registration_success jsp page
     */
    @PostMapping(value = "/newuser")
    public ModelAndView saveUser(@Valid AppUser appUser, BindingResult result, RedirectAttributes redirectAttributes) {

        ModelAndView mav = new ModelAndView();
        mav.setViewName("registration");
        if (result.hasErrors()) {
            LOG.info("### update validation errors: " + result);
            mav.addObject("enableAuthorityList", true);
            return mav;
        }

        if (!appUserService.isUsernameUnique(appUser.getId(), appUser.getUsername())) {
            FieldError ssoError = new FieldError("appUser", "username", "Username " + appUser.getUsername() + " already exists!");
            result.addError(ssoError);
            return mav;
        }

        LOG.info("### appUser to create: " + appUser);

        appUserService.save(appUser);

        redirectAttributes.addFlashAttribute("success", "User " + appUser.getUsername() + " registered successfully");

        mav.setViewName("redirect:/list?newuser");

        return mav;

    }

    /**
     * rest endpoint provides an existing user which can be updated
     *
     * @param username username to search for update
     * @return registration jsp page
     */
    @GetMapping(value = "/edit-user-{username}")
    public ModelAndView editUser(@PathVariable String username) {

        AppUser appUser = appUserService.findByUsername(username);

        boolean enableAuthorityList = false;
        if (appAuthorityService.isAdminByUsername(username)) {
            enableAuthorityList = appUserService.isMoreThanOneAdmin();
        } else {
            enableAuthorityList = true;
        }

        LOG.info("### enable authority list: " + enableAuthorityList);

        ModelAndView mav = new ModelAndView();
        mav.addObject("appUser", appUser);
        mav.addObject("edit", true);
        mav.addObject("loggedinuser", getPrincipal());
        mav.addObject("enableAuthorityList", enableAuthorityList);
        mav.setViewName("registration");
        return mav;

    }

    /**
     * rest endpoint provides submission to update an existing user
     *
     * @param appUser  the updated user
     * @param result   validation result
     * @param username username of updated user
     * @return if validation has no error - registration_success jsp page
     */
    @PostMapping(value = "/edit-user-{username}")
    public ModelAndView updateUser(@Valid AppUser appUser, BindingResult result, @PathVariable String username, RedirectAttributes redirectAttributes) {

        LOG.info("### appUser for update: " + appUser);

        ModelAndView mav = new ModelAndView();
        if (result.hasErrors()) {
            LOG.info("### update validation errors: " + result);
            return mav;
        }

        if (!appUserService.isUsernameUnique(appUser.getId(), appUser.getUsername())) {
            FieldError ssoError = new FieldError("appUser", "username", "Username " + appUser.getUsername() + " already exists!");
            result.addError(ssoError);
            return mav;
        }

        appUserService.update(appUser);

        redirectAttributes.addFlashAttribute("success", "User " + appUser.getUsername() + " edited successfully");

        mav.setViewName("redirect:/list?useredited");

        return mav;
    }

    /**
     * rest endpoint provides the deletion of an existing user
     *
     * @param username username for the user to delete
     * @return redirect to the users_list jsp page
     */
    @RequestMapping(value = {"/delete-user-{username}"}, method = RequestMethod.GET)
    public ModelAndView deleteUser(@PathVariable String username, RedirectAttributes redirectAttributes) {

        ModelAndView mav = new ModelAndView();
        appUserService.deleteByUsername(username);

        redirectAttributes.addFlashAttribute("success", "User " + username + " deleted successfully");

        mav.setViewName("redirect:/list?userdeleted");

        return mav;

    }

    /**
     * method provides authorities for the views
     *
     * @return list of authorities
     */
    @ModelAttribute("appAuthorities")
    public List<AppAuthority> initializeAppAuthorites() {
        return appAuthorityService.findAll();
    }

    /**
     * rest endpoint provides access denied page
     *
     * @return access_denied jsp page
     */
    @GetMapping(value = "/access-denied")
    public ModelAndView accessDeniedPage() {

        ModelAndView mav = new ModelAndView();
        mav.addObject("loggedinuser", getPrincipal());
        mav.setViewName("access_denied");
        return mav;

    }

    /**
     * rest endpoint provides the login
     *
     * @return if user is anonymous the login jsp page - else redirect to the users_list jsp page
     */
    @GetMapping(value = "/login")
    public ModelAndView loginPage() {

        ModelAndView mav = new ModelAndView();
        if (isCurrentAuthenticationAnonymous()) {
            mav.setViewName("login");
        } else {
            mav.setViewName("redirect:/list");
        }
        return mav;

    }

    /**
     * rest endpoint provides the logout
     * (if there is a rememberme token, then delete it)
     *
     * @param request  logout request
     * @param response logout response
     * @return redirect to login jsp page
     */
    @GetMapping(value = "/logout")
    public ModelAndView logoutPage(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView mav = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
            persistentTokenBasedRememberMeServices.logout(request, response, auth);
            SecurityContextHolder.getContext().setAuthentication(null);
        }
        mav.setViewName("redirect:/login?logout");
        return mav;

    }

    /**
     * method provides the principal (username) of a logged in user
     *
     * @return username of user
     */
    private String getPrincipal() {

        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;

    }

    /**
     * method provides a check if users is already logged in
     *
     * @return if user is logged in true, else false
     */
    private boolean isCurrentAuthenticationAnonymous() {

        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authenticationTrustResolver.isAnonymous(authentication);

    }

}