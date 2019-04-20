package com.jba.opencms.security.encoder;

import org.springframework.security.crypto.password.PasswordEncoder;

public class PlainPasswordEncoder implements PasswordEncoder {
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
        return rawPassword.equals(encodedPassword);
    }
}
