package com.jba.opencms.type.file;

import com.jba.opencms.type.base.BaseTypeSimpleKey;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "file")
public class File extends BaseTypeSimpleKey<File> {

    @Column(name = "NAME", length = 100)
    private String name;

    @Column(name = "DESCRIPTION", length = 200)
    private String description;

    @Column(name = "ENABLED", nullable = false)
    private Boolean enabled;

    @Column(name = "DATA", nullable = false, columnDefinition = "LONGBLOB")
    @Lob
    private byte[] data;

    @Column(name = "EXTENSION", length = 5)
    private String extension;
}
