package de.m1well.spring.security.sample.webapp.model;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

/**
 * author: Michael Wellner<br/>
 * date: 26.10.2017<br/>
 */
@Entity
@Table(name = "app_users")
public class AppUser {

    private static final String PATTERN_EMAIL = "\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

    @NotBlank
    @Size(min = 3, max = 30)
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @NotBlank
    @Column(name = "password", nullable = false)
    private String password;

    @NotBlank
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotBlank
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotBlank
    @Pattern(regexp = PATTERN_EMAIL)
    @Column(name = "email", nullable = false)
    private String email;

    @NotEmpty
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "app_users_2_authorities",
            joinColumns = {@JoinColumn(name = "app_user")},
            inverseJoinColumns = {@JoinColumn(name = "app_authority")})
    private Set<AppAuthority> appAuthorities = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<AppAuthority> getAppAuthorities() {
        return appAuthorities;
    }

    public void setAppAuthorities(Set<AppAuthority> appAuthorities) {
        this.appAuthorities = appAuthorities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AppUser)) return false;

        AppUser appUser = (AppUser) o;

        if (id != null ? !id.equals(appUser.id) : appUser.id != null) return false;
        if (username != null ? !username.equals(appUser.username) : appUser.username != null) return false;
        if (password != null ? !password.equals(appUser.password) : appUser.password != null) return false;
        if (firstName != null ? !firstName.equals(appUser.firstName) : appUser.firstName != null) return false;
        if (lastName != null ? !lastName.equals(appUser.lastName) : appUser.lastName != null) return false;
        if (email != null ? !email.equals(appUser.email) : appUser.email != null) return false;
        return appAuthorities != null ? appAuthorities.equals(appUser.appAuthorities) : appUser.appAuthorities == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (appAuthorities != null ? appAuthorities.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", appAuthorities=" + appAuthorities +
                '}';
    }

}