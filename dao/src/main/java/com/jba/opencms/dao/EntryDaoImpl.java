package com.jba.opencms.dao;

import com.jba.opencms.dao.ifs.EntryDao;
import com.jba.opencms.type.menu.Entry;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class EntryDaoImpl extends AbstractHibernateDao<Entry> implements EntryDao{

    public EntryDaoImpl(Class<Entry> clazz, SessionFactory sessionFactory) {
        super(clazz, sessionFactory);
    }

    @Override
    public void setClass(Class clazz) {
        this.clazz=clazz;
    }

    @Override
    public Entry findOne(long id) {
        Entry entry = super.findOne(id);
        Hibernate.initialize(entry.getSubentires());
        return entry;
    }
}


