package com.jba.opencms.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;

@Transactional
public abstract class AbstractHibernateDao<T extends Serializable> {

    Class<T> clazz;

    SessionFactory sessionFactory;

    public AbstractHibernateDao(Class<T> clazz, SessionFactory sessionFactory) {
        this.clazz = clazz;
        this.sessionFactory = sessionFactory;
    }

    public T findOne(long id) {
        return (T) getCurrentSession().get(clazz, id);
    }

    public List<T> findFiltered(CriteriaQuery<T> filter){
        return getCurrentSession().createQuery(filter).getResultList();
    }

    public List<T> findAll() {
        return getCurrentSession().createQuery("from "+clazz.getName(), clazz).getResultList();
    }

    public void create(T entity) {
        getCurrentSession().persist(entity);
    }

    public T update(T entity) {
        return (T) getCurrentSession().merge(entity);
    }

    public void delete(T entity) {
        getCurrentSession().delete(entity);
    }

    public void deleteById(long entityId) {
        T entity = findOne(entityId);
        delete(entity);
    }

    public CriteriaBuilder createBuilder(){
        return getCurrentSession().getCriteriaBuilder();
    }

    protected final Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}