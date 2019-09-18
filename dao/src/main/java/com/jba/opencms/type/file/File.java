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

    @Column(name = "PATH")
    private String path;

    @Column(name = "DATA", nullable = false, columnDefinition = "MEDIUMBLOB")
    @Lob
    private byte[] data;

    @Column(name = "MIME")
    private String mime;

    @Column(name = "DESCRIPTION", length = 200)
    private String description;
}
