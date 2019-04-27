package com.jba.opencms.dao.ifs;

import com.jba.opencms.dao.GenericDao;
import com.jba.opencms.type.menu.Menu;

import java.util.List;

public interface MenuDao extends GenericDao<Menu> {
    Menu findActiveMenu();

    @Override
    List<Menu> findAll();
}
