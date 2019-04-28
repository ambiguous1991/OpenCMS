package com.jba.opencms.dao;

import com.jba.opencms.dao.ifs.MenuDao;
import com.jba.opencms.type.menu.Menu;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.List;

@Transactional
public class MenuDaoImpl extends HibernateDao<Menu> implements MenuDao {
    public MenuDaoImpl(Class<Menu> clazz, SessionFactory sessionFactory) {
        super(clazz, sessionFactory);
    }

    public Menu findActiveMenu(){
        try {
            Menu menu = getCurrentSession()
                    .createQuery("from Menu m where m.isActive=true", clazz)
                    .getSingleResult();

            return menu;
        }
        catch (NoResultException e){
            return null;
        }
    }

    @Override
    @Transactional
    public List<Menu> findAll() {
        List<Menu> all = super.findAll();
        all.forEach(e-> Hibernate.initialize(e.getEntries()));
        return all;
    }

    @Override
    @Transactional
    public Menu findOne(long id) {
        Menu one = super.findOne(id);
        return one;
    }
}
