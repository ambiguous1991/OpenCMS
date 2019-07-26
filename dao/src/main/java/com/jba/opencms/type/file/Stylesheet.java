package com.jba.opencms.type.file;

import com.jba.opencms.type.base.BaseTypeSimpleKey;
import com.jba.opencms.type.page.Page;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "stylesheet")
public class Stylesheet extends BaseTypeSimpleKey<Stylesheet> {

    @Column(name = "TITLE", nullable = false, length = 200)
    private String title;

    @Column(name = "PATH", length = 200)
    private String path;

    @Column(name = "VALUE")
    private String value;

    @ManyToMany
    @ToString.Exclude
    @JoinTable(
            name = "page_stylesheet",
            joinColumns = {@JoinColumn(name = "FK_PAGE_ID")},
            inverseJoinColumns = {@JoinColumn(name = "FK_STYLESHEET_ID")}
    )
    private Set<Page> pages = new HashSet<>();

}
