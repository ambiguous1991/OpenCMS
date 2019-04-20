package com.jba.opencms.type.image;

import com.jba.opencms.type.base.BaseTypeSimpleKey;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import java.sql.Blob;

@Entity
@Data
@Table(name = "IMAGE")
public class Image extends BaseTypeSimpleKey<Image> {

    @Column(name = "NAME", length = 100)
    private String name;

    @Column(name = "DESCRIPTION", length = 200)
    private String description;

    @Column(name = "DATA", nullable = false)
    @Lob
    private Blob data;

    @Column(name = "EXTENSION", length = 5)
    private String extension;

}
