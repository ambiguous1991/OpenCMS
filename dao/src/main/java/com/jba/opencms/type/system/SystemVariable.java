package com.jba.opencms.type.system;

import com.jba.opencms.type.base.BaseTypeSimpleKey;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "system_variable")
public class SystemVariable extends BaseTypeSimpleKey<SystemVariable>{

    @Column(name = "SYSTEM_KEY")
    public String key;

    @Column(name = "SYSTEM_VALUE")
    public String value;

}
