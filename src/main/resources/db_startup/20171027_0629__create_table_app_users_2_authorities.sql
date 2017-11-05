-- mysql script
-- author: Michael Wellner
-- date: 27.10.2017

-- create new app_users_2_authorities table @ securitydb
USE securitydb;

DROP TABLE IF EXISTS app_users_2_authorities;

CREATE TABLE app_users_2_authorities (
    app_user BIGINT NOT NULL,
    app_authority BIGINT NOT NULL,
    PRIMARY KEY (app_user, app_authority),
    CONSTRAINT fk_2_app_users FOREIGN KEY (app_user) REFERENCES app_users(id),
    CONSTRAINT fk_2_app_authorities FOREIGN KEY (app_authority) REFERENCES app_authorities(id)
) ENGINE=InnoDB;
