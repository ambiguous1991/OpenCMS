package com.jba.opencms.type.base;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseTypeCompoundKey<T1 extends BaseType, T2 extends BaseType> extends BaseType{

}
