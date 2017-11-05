package de.m1well.spring.security.sample.webapp.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * author: Michael Wellner<br/>
 * date: 26.10.2017<br/>
 */
public class QuickPasswordGenerator {

    /**
     * provides a encoded password - to store it in the database
     *
     * @param args main args
     */
    public static void main(String[] args) {
        String password = "scotty123";
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode(password));
    }

}
