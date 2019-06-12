package com.jba.opencms.web.configuration;

import org.jboss.logging.Logger;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoderTest {

    private Logger logger = Logger.getLogger(PasswordEncoderTest.class);

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Test
    public void encodePassword(){
        String toEncode = "test123";
        String encoded = passwordEncoder.encode(toEncode);
        logger.info("Password is " + encoded);
    }
}