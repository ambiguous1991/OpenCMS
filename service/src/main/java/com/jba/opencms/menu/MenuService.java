package com.jba.opencms.menu;

import com.jba.opencms.base.BaseService;
import com.jba.opencms.type.menu.Entry;
import com.jba.opencms.type.menu.Menu;

import java.util.List;

public interface MenuService extends BaseService<Menu> {
    void addMenuEntry(Menu menu, Entry entry);
    List<Entry> getMenuEntries(Menu menu);
    Menu findActive();
    boolean setActive(Menu menu);
}
