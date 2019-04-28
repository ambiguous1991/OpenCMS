package com.jba.opencms.base;

import java.util.List;

public interface BaseService<T> {
    void create(T entity);
    void update(T entity);
    T findOne(Long id, boolean initialize);
    List<T> findAll(boolean initialize);
    void delete(T entity);
    void delete(Long id);
}
