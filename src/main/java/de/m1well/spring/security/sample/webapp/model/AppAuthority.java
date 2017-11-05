package de.m1well.spring.security.sample.webapp.model;

import javax.persistence.*;

/**
 * author: Michael Wellner<br/>
 * date: 26.10.2017<br/>
 */
@Entity
@Table(name = "app_authorities")
public class AppAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

    // default value - type 'USER'
    @Column(name = "type", length = 20, nullable = false)
    private String type = AppAuthorityType.USER.getAppAuthorityType();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AppAuthority)) return false;

        AppAuthority that = (AppAuthority) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return type != null ? type.equals(that.type) : that.type == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AppAuthority{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }
}
