package com.jba.dao.type.user;

import com.jba.dao.type.base.BaseTypeSimpleKey;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "USER")
public class User extends BaseTypeSimpleKey<User> {

}
