package com.jba.opencms.dao.menu;

import com.jba.opencms.BaseSpringIntegrationTest;
import com.jba.opencms.configuration.DaoConfiguration;
import com.jba.opencms.configuration.DataSourceConfig;
import com.jba.opencms.dao.GenericDao;
import com.jba.opencms.dao.ifs.MenuDao;
import com.jba.opencms.type.menu.Entry;
import com.jba.opencms.type.menu.Menu;
import com.jba.opencms.type.menu.MenuEntry;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
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
    private GenericDao<MenuEntry> menuEntryDao;

    @Autowired
    private GenericDao<Entry> entryDao;

    @Test
    @Transactional
    public void test(){
        Menu activeMenu = menuDao.findActiveMenu();
        logger.info(activeMenu);
    }

    @Test
    public void addTest(){
        Menu menu = new Menu();

        menuDao.create(menu);
        logger.info("Created menu: "+menu);

        List<Entry> entries = Arrays.asList(
                Entry.of("Newtest1"),
                Entry.of("NewTest2"),
                Entry.of("NewTest3")
        );
        entries.forEach(entry->{
            entryDao.create(entry);
            logger.info("Added entry: "+entry);
        });


        List<MenuEntry> menuEntries = Arrays.asList(
                MenuEntry.of(menu, entries.get(0), true),
                MenuEntry.of(menu, entries.get(1), true),
                MenuEntry.of(menu, entries.get(2), true)
        );

        menuEntries.forEach(entry->{
            menuEntryDao.create(entry);
            logger.info("Added menu entry: "+entry);
        });
    }
}