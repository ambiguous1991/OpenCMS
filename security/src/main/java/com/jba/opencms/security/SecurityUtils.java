package com.jba.opencms.security;

import com.jba.opencms.security.principal.UserPrincipal;
import com.jba.opencms.type.user.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class SecurityUtils {

    private SecurityUtils(){

    }

    public static User getUserFromRequest(Object principal){
        if(principal instanceof UsernamePasswordAuthenticationToken){
            UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) principal;

            UserPrincipal userPrincipal = (UserPrincipal) auth.getPrincipal();

            return userPrincipal.getUser();
        }
        else throw new IllegalArgumentException("Object passed to getUser is not a valid Auth Token");
    }

}
