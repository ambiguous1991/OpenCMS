package com.jba.opencms.type.page;

import com.jba.opencms.type.base.BaseTypeSimpleKey;
import com.jba.opencms.type.user.Authority;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "PAGE")
public class Page extends BaseTypeSimpleKey<Page> {

    @Column(name = "TITLE", nullable = false, length = 200)
    private String title;

    @Column(name = "CONTENT", nullable = false)
    private String content;

    @Column(name = "VISIBLE", nullable = false)
    private Boolean visible;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "page_authority",
            joinColumns = {@JoinColumn(name = "FK_AUTHORITY_ID")},
            inverseJoinColumns = {@JoinColumn(name="FK_PAGE_ID")}
    )
    protected List<Authority> pageAuthorities = new ArrayList<>();

    public void addAuthority(Authority authority){
        if(!pageAuthorities.contains(authority)) {
            pageAuthorities.add(authority);
        }
        else throw new IllegalArgumentException("This page already contains authority: "+authority.getRole());
    }
}
