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
@Table(name = "script")
public class Script extends BaseTypeSimpleKey<Script> {

    @Column(name = "TITLE", nullable = false, length = 200)
    private String title;

    @Column(name = "PATH", length = 200)
    private String path;

    @Column(name = "VALUE")
    private String value;

    @ManyToMany
    @JoinTable(
            name = "page_script",
            joinColumns = {@JoinColumn(name = "FK_PAGE_ID")},
            inverseJoinColumns = {@JoinColumn(name = "FK_SCRIPT_ID")}
    )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Page> pages = new HashSet<>();

}
