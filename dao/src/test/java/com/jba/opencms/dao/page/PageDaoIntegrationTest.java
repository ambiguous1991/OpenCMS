package com.jba.opencms.dao.page;

import com.jba.opencms.BaseSpringIntegrationTest;
import com.jba.opencms.configuration.DaoConfiguration;
import com.jba.opencms.configuration.TestDatasourceConfiguration;
import com.jba.opencms.dao.GenericDao;
import com.jba.opencms.type.page.Page;
import com.jba.opencms.type.user.Authority;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestDatasourceConfiguration.class, DaoConfiguration.class})
@EnableTransactionManagement
public class PageDaoIntegrationTest extends BaseSpringIntegrationTest {

    @Autowired private GenericDao<Page> pageDao;
    @Autowired private GenericDao<Authority> authorityDao;

    @Test
    public void saveTest(){
        Page page = new Page();
        page.setContent("Test");
        page.setVisible(true);
        page.setTitle("TestTitle");
        page.setIdentifier("testtitle");
        pageDao.create(page);

        List<Authority> all = authorityDao.findAll();
        Authority authority = all.get(0);

        pageDao.update(page);
    }

    @Test
    public void updateTest(){
        List<Page> all = pageDao.findAll();
        all.stream().forEach(page -> logger.info(page));
        Page page = all.get(0);
        page.setTitle("New title!");
        pageDao.update(page);
    }
}