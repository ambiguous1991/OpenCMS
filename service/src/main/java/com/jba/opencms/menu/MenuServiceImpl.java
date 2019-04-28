package com.jba.opencms.menu;

import com.jba.opencms.base.AbstractService;
import com.jba.opencms.dao.GenericDao;
import com.jba.opencms.dao.ifs.EntryDao;
import com.jba.opencms.dao.ifs.MenuDao;
import com.jba.opencms.type.menu.Entry;
import com.jba.opencms.type.menu.Menu;
import com.jba.opencms.type.menu.MenuEntry;
import org.hibernate.Hibernate;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class MenuServiceImpl extends AbstractService<Menu> implements MenuService {

    private EntryDao entryDao;
    private GenericDao<MenuEntry> menuEntryDao;

    public MenuServiceImpl(MenuDao menuDao, EntryDao entryDao, GenericDao<MenuEntry> menuEntryDao) {
        super(menuDao);
        this.entryDao = entryDao;
        this.menuEntryDao = menuEntryDao;
    }

    @Override
    public void addMenuEntry(Menu menu, Entry entry) {
        entry.getMenu().add(menu);
        entryDao.create(entry);
        menu.getEntries().add(entry);
        dao.update(menu);
        MenuEntry menuEntry = new MenuEntry();
        menuEntry.setEntry(entry);
        menuEntry.setMenu(menu);
        menuEntryDao.create(menuEntry);
    }

    @Override
    public List<Entry> getMenuEntries(Menu menu) {
        Hibernate.initialize(menu.getEntries());
        Hibernate.initialize(menu.getMenuEntryList());
        return menu.getEntries();
    }

    @Override
    public Menu findActive() {
        return ((MenuDao)dao).findActiveMenu();
    }

    @Override
    public boolean setActive(Menu menu) {
        Menu active = findActive();
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
