package com.jba.opencms.type.file;

import com.jba.opencms.type.base.BaseTypeSimpleKey;
import com.jba.opencms.type.page.Page;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "file")
@Data
public class File extends BaseTypeSimpleKey<File> {

    @Column(name = "NAME", length = 100)
    private String name;

    @Column(name = "PATH")
    private String path;

    @Column(name = "DATA", nullable = false, columnDefinition = "MEDIUMBLOB")
    @Lob
    @ToString.Exclude
    private byte[] data;

    @Column(name = "MIME")
    private String mime;

    @Column(name = "DESCRIPTION", length = 200)
    private String description;

    @ManyToMany(mappedBy = "scripts")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Page> pages = new ArrayList<>();
}
