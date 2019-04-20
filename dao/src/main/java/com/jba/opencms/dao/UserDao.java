package com.jba.opencms.dao;

import com.jba.opencms.type.user.User;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;

public class UserDao extends HibernateDao<User> {
    public UserDao(Class<User> clazz, SessionFactory sessionFactory) {
        super(clazz, sessionFactory);
    }

    public User findUserByEmail(String email){
        User user = getCurrentSession().createQuery("from User u where u.email=:email", User.class)
                .setParameter("email", email)
                .getSingleResult();
        if(user!=null) {
            Hibernate.initialize(user.getAuthorities());
        }
        return user;
    }
}