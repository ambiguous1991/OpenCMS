package com.jba.opencms.type.base;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.io.Serializable;
import java.time.OffsetDateTime;

@MappedSuperclass
public class BaseType implements Serializable {

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
