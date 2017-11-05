package de.m1well.spring.security.sample.webapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * author: Michael Wellner<br/>
 * date: 27.10.2017<br/>
 */
@Entity
@Table(name = "app_tokens")
public class AppToken {

    @Id
    @Column(name = "series", nullable = false, unique = true)
    private String series;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "token", nullable = false)
    private String token;

    @Column(name = "last_used", nullable = false)
    private Date last_used;

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getLast_used() {
        return last_used;
    }

    public void setLast_used(Date last_used) {
        this.last_used = last_used;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AppToken appToken = (AppToken) o;

        if (series != null ? !series.equals(appToken.series) : appToken.series != null) return false;
        if (username != null ? !username.equals(appToken.username) : appToken.username != null) return false;
        if (token != null ? !token.equals(appToken.token) : appToken.token != null) return false;
        return last_used != null ? last_used.equals(appToken.last_used) : appToken.last_used == null;
    }

    @Override
    public int hashCode() {
        int result = series != null ? series.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (token != null ? token.hashCode() : 0);
        result = 31 * result + (last_used != null ? last_used.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AppToken{" +
                "series='" + series + '\'' +
                ", username='" + username + '\'' +
                ", token='" + token + '\'' +
                ", last_used=" + last_used +
                '}';
    }
}
