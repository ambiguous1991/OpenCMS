package com.jba.opencms.type.page;

import com.jba.opencms.type.base.BaseTypeSimpleKey;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "script")
public class Script extends BaseTypeSimpleKey<Script> {

    @Column(name = "TITLE", nullable = false, length = 200)
    private String title;

    @Column(name = "PATH", length = 200)
    private String path;

    @Column(name = "VALUE")
    private String value;

    @ManyToMany
    @ToString.Exclude
    @JoinTable(
            name = "page_script",
            joinColumns = {@JoinColumn(name = "FK_PAGE_ID")},
            inverseJoinColumns = {@JoinColumn(name = "FK_SCRIPT_ID")}
    )
    private Set<Page> pages = new HashSet<>();
}
