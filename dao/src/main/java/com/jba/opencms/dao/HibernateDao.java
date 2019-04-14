package com.jba.opencms.dao;

import org.hibernate.SessionFactory;

import java.io.Serializable;

public class HibernateDao<T extends Serializable> extends AbstractHibernateDao<T> implements GenericDao<T> {

    public HibernateDao(Class<T> clazz, SessionFactory sessionFactory) {
        super(clazz, sessionFactory);
    }

    @Override
    public void setClass(Class clazz) {
        this.clazz=clazz;
    }
}