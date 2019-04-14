package com.jba.opencms.type.base;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.OffsetDateTime;

@MappedSuperclass
@Data
public abstract class BaseTypeSimpleKey<T extends BaseTypeSimpleKey> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    protected Long id;

    @Column(name = "CDATE")
    protected OffsetDateTime created;

    @Column(name = "UDATE")
    protected OffsetDateTime updated;

    @PrePersist
    protected void initialize(){
        if(created==null){
            created = OffsetDateTime.now();
        }
        if(updated==null){
            updated = OffsetDateTime.now();
        }
    }

    @PreUpdate
    protected void preUpdate(){
        updated=OffsetDateTime.now();
    }
}
