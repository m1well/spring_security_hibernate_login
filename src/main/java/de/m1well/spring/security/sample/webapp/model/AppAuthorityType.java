package de.m1well.spring.security.sample.webapp.model;

/**
 * author: Michael Wellner<br/>
 * date: 26.10.2017<br/>
 */
public enum AppAuthorityType {

    ADMIN("ADMIN"),
    USER("USER");

    private String appAuthorityType;

    AppAuthorityType(String appAuthorityType) {
        this.appAuthorityType = appAuthorityType;
    }

    public String getAppAuthorityType() {
        return appAuthorityType;
    }

    @Override
    public String toString() {
        return "AppAuthorityType{" +
                "appAuthorityType='" + appAuthorityType + '\'' +
                '}';
    }
}
