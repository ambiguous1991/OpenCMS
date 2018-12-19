package com.jba.dao.property.type;

import com.jba.dao.base.type.DAOBase;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name="properties")
@Data
@ToString(callSuper = true)
public class Property extends DAOBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    long id;

    @Column(name="NAME")
    String name;

    @Column(name = "VALUE")
    String value;
}
