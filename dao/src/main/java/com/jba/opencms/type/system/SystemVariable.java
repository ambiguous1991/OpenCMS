package com.jba.opencms.type.system;

import com.jba.opencms.type.base.BaseTypeSimpleKey;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "SYSTEM_VARIABLE")
public class SystemVariable extends BaseTypeSimpleKey<SystemVariable>{

    @Column(name = "KEY")
    public String key;

    @Column(name = "VALUE")
    public String value;

}
