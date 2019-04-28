package com.jba.opencms.dao.menu;

import com.jba.opencms.BaseSpringIntegrationTest;
import com.jba.opencms.configuration.DaoConfiguration;
import com.jba.opencms.configuration.DataSourceConfig;
import com.jba.opencms.dao.GenericDao;
import com.jba.opencms.dao.ifs.MenuDao;
import com.jba.opencms.type.menu.Entry;
import com.jba.opencms.type.menu.Menu;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {DataSourceConfig.class, DaoConfiguration.class})
@EnableTransactionManagement
public class EntryDaoTest extends BaseSpringIntegrationTest {

    @Autowired
    private MenuDao menuDao;

    @Autowired
    private GenericDao<Entry> entryDao;

    @Test
    @Transactional
    public void findActive(){
        Menu activeMenu = menuDao.findActiveMenu();
        if(activeMenu!=null) {
            logger.info(activeMenu);
        }
    }

    @Test
    @Transactional
    public void addTest(){
        Menu menu = new Menu();

        menuDao.create(menu);
        logger.info("Created menu: "+menu);

        Entry entry1 = new Entry();
        entry1.setLabel("Menu item 1");

        Entry entry2 = new Entry();
        entry2.setLabel("Menu item 2");

        Entry entry3 = new Entry();
        entry3.setLabel("Menu item 3");

        List<Entry> entries = Arrays.asList(entry1, entry2, entry3);
        entries.forEach(e->entryDao.create(e));
    }
}