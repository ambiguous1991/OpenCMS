package com.jba.opencms.dao;

import com.jba.opencms.dao.ifs.MenuDao;
import com.jba.opencms.type.menu.Menu;
import com.jba.opencms.type.menu.MenuEntry;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class MenuDaoImpl extends HibernateDao<Menu> implements MenuDao {
    public MenuDaoImpl(Class<Menu> clazz, SessionFactory sessionFactory) {
        super(clazz, sessionFactory);
    }

    public Menu findActiveMenu(){
        Menu menu = getCurrentSession()
                .createQuery("from Menu m where m.isActive=true", clazz)
                .getSingleResult();

        if(menu!=null) {
            Hibernate.initialize(menu.getMenuEntryList());
            for(MenuEntry menuEntry: menu.getMenuEntryList()){
                Hibernate.initialize(menuEntry.getEntry().getSubentires());
            }
        }
        return menu;
    }

    @Override
    @Transactional
    public List<Menu> findAll() {
        List<Menu> all = super.findAll();
        all.forEach(e-> Hibernate.initialize(e.getMenuEntryList()));
        return all;
    }

    @Override
    @Transactional
    public Menu findOne(long id) {
        Menu one = super.findOne(id);
        Hibernate.initialize(one.getMenuEntryList());
        Hibernate.initialize(one.getEntries());
        return one;
    }
}
