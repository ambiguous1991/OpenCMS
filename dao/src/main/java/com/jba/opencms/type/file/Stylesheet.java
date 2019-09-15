package com.jba.opencms.type.file;

import com.jba.opencms.type.base.BaseTypeSimpleKey;
import com.jba.opencms.type.page.Page;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

    @ManyToMany(mappedBy = "stylesheets")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Page> pages = new ArrayList<>();

}
