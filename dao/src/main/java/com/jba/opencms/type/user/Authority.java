package com.jba.opencms.type.user;

import com.jba.opencms.type.base.BaseTypeSimpleKey;
import com.jba.opencms.type.user.enu.AuthorityEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "authority")
public class Authority extends BaseTypeSimpleKey<Authority> {

    @Column(name = "ROLE", length = 30, nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private AuthorityEnum role;

}
