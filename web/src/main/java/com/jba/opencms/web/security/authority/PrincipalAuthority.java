package com.jba.opencms.web.security.authority;

import com.jba.opencms.type.user.Authority;
import org.springframework.security.core.GrantedAuthority;

public class PrincipalAuthority implements GrantedAuthority {

    private Authority authority;

    public PrincipalAuthority(Authority authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority.toString();
    }
}
