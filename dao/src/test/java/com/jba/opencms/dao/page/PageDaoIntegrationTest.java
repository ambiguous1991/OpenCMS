package com.jba.opencms.dao.page;

import com.jba.opencms.BaseSpringIntegrationTest;
import com.jba.opencms.configuration.DaoConfiguration;
import com.jba.opencms.configuration.DataSourceConfig;
import com.jba.opencms.dao.GenericDao;
import com.jba.opencms.type.page.Page;
import com.jba.opencms.type.user.Authority;
import com.jba.opencms.type.user.enu.AuthorityEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {DataSourceConfig.class, DaoConfiguration.class})
public class PageDaoIntegrationTest extends BaseSpringIntegrationTest {

    @Autowired
    private GenericDao<Page> pageDao;

    @Autowired
    private GenericDao<Authority> authorityDao;

    @Test
    public void saveTest(){
        Page page = new Page();
        page.setContent("Test");
        page.setVisible(true);
        page.setTitle("TestTitle");
        pageDao.create(page);

        Authority authority = new Authority();
        authority.setRole(AuthorityEnum.ADMINISTRATOR);
        authorityDao.create(authority);

        page.addAuthority(authority);

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