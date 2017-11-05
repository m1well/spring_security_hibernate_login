-- mysql script
-- author: Michael Wellner
-- date: 18.10.2017

-- create new app_tokens table @ securitydb
USE securitydb;

DROP TABLE IF EXISTS app_tokens;

CREATE TABLE app_tokens (
    series VARCHAR(64) NOT NULL,
    username VARCHAR(30) NOT NULL,
    token VARCHAR(64) NOT NULL,
    last_used TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    PRIMARY KEY (series)
) ENGINE=InnoDB;
