package com.jba.opencms.type.message;

import com.jba.opencms.type.base.BaseTypeSimpleKey;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "status")
public class Status extends BaseTypeSimpleKey<Status> {

    @Column(name="STATUS_NAME")
    private String statusName;

}
