package com.jba.opencms.dao.file;

import com.jba.opencms.BaseSpringIntegrationTest;
import com.jba.opencms.configuration.DaoConfiguration;
import com.jba.opencms.configuration.TestDatasourceConfiguration;
import com.jba.opencms.dao.GenericDao;
import com.jba.opencms.type.file.Script;
import com.jba.opencms.type.page.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestDatasourceConfiguration.class, DaoConfiguration.class})
@EnableTransactionManagement
public class ScriptTest extends BaseSpringIntegrationTest {

    @Autowired
    private GenericDao<Script> scriptDao;

    @Test
    @Transactional
    public void getTest(){
        List<Script> scripts = scriptDao.findAll();
        scripts.forEach(logger::info);
        Set<Page> pages = scripts.get(0).getPages();
        pages.forEach(logger::info);
    }

    @Test
    public void saveTest(){
        Script script = new Script();
        script.setTitle("New script");
        script.setPath("/test/awd");
        scriptDao.create(script);
    }
}