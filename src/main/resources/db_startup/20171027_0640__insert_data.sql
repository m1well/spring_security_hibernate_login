-- mysql script
-- author: Michael Wellner
-- date: 27.10.2017

USE securitydb;

INSERT INTO app_authorities(type)
VALUES ('ADMIN');

INSERT INTO app_authorities(type)
VALUES ('USER');

-- password is 'scotty123'
INSERT INTO app_users(username, password, first_name, last_name, email)
VALUES ('scott','$2a$10$/zRf5vCUfC8058klSndm4.TIeQ5L6iwSrmZjUOIDGprKQ/z7g1XPK', 'Scott','Harper','s.harper@company.com');

INSERT INTO app_users_2_authorities (app_user, app_authority)
SELECT users.id, authorities.id FROM app_users users, app_authorities authorities
WHERE users.username='scott' AND authorities.type='ADMIN';
