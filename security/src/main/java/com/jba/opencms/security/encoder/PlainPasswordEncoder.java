package com.jba.opencms.security.encoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PlainPasswordEncoder implements PasswordEncoder {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public boolean upgradeEncoding(String encodedPassword) {
        return false;
    }

    @Override
    public String encode(CharSequence rawPassword) {
        return rawPassword.toString();
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        if(rawPassword == null || rawPassword.length()==0){
            logger.warn("Input password is empty!");
        }

        return rawPassword.equals(encodedPassword);
    }
}
