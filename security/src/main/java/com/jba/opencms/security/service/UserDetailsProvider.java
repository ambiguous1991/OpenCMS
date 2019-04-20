package com.jba.opencms.security.service;

import com.jba.opencms.dao.UserDao;
import com.jba.opencms.security.principal.UserPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsProvider implements UserDetailsService {

    private UserDao userDao;

    public UserDetailsProvider(UserDao userDao){
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new UserPrincipal(userDao.findUserByEmail(username));
    }
}