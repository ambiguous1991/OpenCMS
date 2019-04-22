package com.jba.opencms.dao.ifs;

import com.jba.opencms.dao.GenericDao;
import com.jba.opencms.type.menu.Menu;

public interface MenuDao extends GenericDao<Menu> {
    Menu findActiveMenu();
}
