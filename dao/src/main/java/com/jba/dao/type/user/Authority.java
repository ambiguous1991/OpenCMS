package com.jba.dao.type.user;

import com.jba.dao.type.base.BaseTypeSimpleKey;
import com.jba.dao.type.user.enu.AuthorityEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "AUTHORITY")
public class Authority extends BaseTypeSimpleKey<Authority> {

    @Column(name = "ROLE", length = 30, nullable = false)
    @Enumerated(EnumType.STRING)
    private AuthorityEnum role;

}
