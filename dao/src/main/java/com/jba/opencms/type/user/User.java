package com.jba.opencms.type.user;

import com.jba.opencms.type.base.BaseTypeSimpleKey;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "USER")
public class User extends BaseTypeSimpleKey<User> {

    @Column(name = "USERNAME",length = 100, nullable = false, unique = true)
    private String username;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name="SALT", nullable = false)
    private String salt;

    @Column(name= "EMAIL", nullable = false, unique = true)
    private String email;

    @Column(name = "FIRSTNAME", length = 100, nullable = false)
    private String firstName;

    @Column(name = "LASTNAME", length = 100)
    private String lastName;

    @OneToMany(mappedBy = "userId")
    private List<ImageUser> images = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "USER_AUTHORITY",
            joinColumns = {@JoinColumn(name = "FK_AUTORITY_ID")},
            inverseJoinColumns = {@JoinColumn(name = "FK_USER_ID")}
    )
    private List<Authority> authorities = new ArrayList<>();
}