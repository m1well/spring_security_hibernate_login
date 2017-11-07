# Spring Security Hibernate Login - WebApp

This is just a sample Spring Security Login WebApp with Hibernate.<br/>

## Usage
* first build the app: `mvn clean install`
* create a database with the startup scripts (i recommend a mysql docker container)
* then deploy it to a tomcat server
* start with [http://localhost:8080/security-webapp](http://localhost:8080/security-webapp)
* login with username "scott" and password "scotty123"

## Screenshots
* Login
![Login](screenshots/01_login.PNG "Login")

* Users List
![Login](screenshots/02_users_list.PNG "Users List")

* New User
![Login](screenshots/03_new_user.PNG "New User")

* Edit User
![Login](screenshots/04_edit_user.PNG "Edit User")

## Technologies
This WebApp shouldn't win a design award. but it brings some technologies together:<br/>
* Frontend is written in JSP with bootstrap CSS<br/>
* Communication via REST-Endpoints<br/>
* Backend is written in Spring<br/>
* Login functionality via Spring Security<br/>
* Remember me functionality via HibernateTokenRepository (store token in database)<br/>
* ORM mapping via Hibernate/JPA<br/>
* mysql database<br/>

## Contribution
You are welcome to contribute this project! Please follow the standard rules.<br/>
If you find a bug or have an idea for improvement, then please firstly open an issue.<br/>
Thank you.<br/>

## P.S.
This project is just to improve my spring/jsp skills and to "store" some best practices<br/>
like PersistenceConfig/SecurityConfig or the behaviour of the JPA repositories.<br/>

## Copyright and License
Copyright :copyright: 2017 Michael Wellner ([@m1well](http://www.twitter.m1well.de))<br/>
Code released under the [MIT License](/LICENSE).<br/>
