package com.jba.opencms.security.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface UserDetailsServicePasswordChangeCapable extends UserDetailsService {
    void changePassword(String username, String newPassword);
}
