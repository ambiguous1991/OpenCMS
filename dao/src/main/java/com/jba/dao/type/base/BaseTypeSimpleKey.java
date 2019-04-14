package com.jba.dao.type.base;

import lombok.Data;

import javax.persistence.*;
import java.time.OffsetDateTime;

@MappedSuperclass
@Data
public abstract class BaseTypeSimpleKey<T extends BaseTypeSimpleKey> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    protected Long id;

    @Column(name = "CDATE")
    protected OffsetDateTime created;

    @Column(name = "UDATE")
    protected OffsetDateTime updated;

}
