package com.jba.opencms.dao;

import org.hibernate.Criteria;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;

public interface GenericDao<T extends Serializable> {

    T findOne(final long id);

    List<T> findAll();

    List<T> findFiltered(CriteriaQuery<T> filter);

    void create(final T entity);

    T update(final T entity);

    void delete(final T entity);

    void deleteById(final long entityId);

    void setClass(final Class clazz);

    CriteriaBuilder createBuilder();

}
