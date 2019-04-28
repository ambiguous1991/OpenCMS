package com.jba.opencms.menu;

import com.jba.opencms.base.AbstractService;
import com.jba.opencms.dao.ifs.EntryDao;
import com.jba.opencms.dao.ifs.MenuDao;
import com.jba.opencms.type.menu.Entry;
import com.jba.opencms.type.menu.Menu;
import org.hibernate.Hibernate;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class MenuServiceImpl extends AbstractService<Menu> implements MenuService {

    private EntryDao entryDao;

    public MenuServiceImpl(MenuDao menuDao, EntryDao entryDao) {
        super(menuDao);
        this.entryDao = entryDao;
    }

    @Override
    public void addMenuEntry(Menu menu, Entry entry) {
        entry.setMenu(menu);
        entryDao.create(entry);
        menu.getEntries().add(entry);
        dao.update(menu);
    }

    @Override
    public List<Entry> getMenuEntries(Menu menu) {
        Hibernate.initialize(menu.getEntries());
        return menu.getEntries();
    }

    @Override
    public Menu findActive(boolean initialize) {
        Menu activeMenu = ((MenuDao) dao).findActiveMenu();
        if(activeMenu!=null && initialize) {
            initialize(activeMenu);
            activeMenu.getEntries().forEach(this::initialize);
        }
        return activeMenu;
    }

    @Override
    public List<Menu> findAll(boolean initialize) {
        List<Menu> all = dao.findAll();
        if(initialize) {
            all.forEach(e->{
                initialize(e);
                e.getEntries().forEach(entry->initialize(entry));
            });
        }
        return all;
    }

    @Override
    public boolean setActive(Menu menu) {
        Menu active = findActive(false);
        if(active.equals(menu)){
            return false;
        }
        else{
            active.setIsActive(false);
            dao.update(active);
            menu.setIsActive(true);
            dao.update(menu);
            return true;
        }
    }
}
