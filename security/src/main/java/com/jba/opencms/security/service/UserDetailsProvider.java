package com.jba.opencms.security.service;

import com.jba.opencms.dao.UserDao;
import com.jba.opencms.security.principal.UserPrincipal;
import com.jba.opencms.type.user.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsProvider implements UserDetailsServicePasswordChangeCapable {

    private UserDao userDao;
    private PasswordEncoder passwordEncoder;

    public UserDetailsProvider(UserDao userDao, PasswordEncoder passwordEncoder){
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new UserPrincipal(userDao.findUserByEmail(username));
    }

    @Override
    public void changePassword(String username, String newPassword) {
        User user = userDao.findUserByEmail(username);
        String passwordEncoded = passwordEncoder.encode(newPassword);
        user.setPassword(passwordEncoded);
        userDao.update(user);
    }
}