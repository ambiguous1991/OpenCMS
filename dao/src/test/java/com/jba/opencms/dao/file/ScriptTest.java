package com.jba.opencms.dao.file;

import com.jba.opencms.BaseSpringIntegrationTest;
import com.jba.opencms.configuration.DaoConfiguration;
import com.jba.opencms.configuration.TestDatasourceConfiguration;
import com.jba.opencms.dao.GenericDao;
import com.jba.opencms.type.file.Script;
import com.jba.opencms.type.page.Page;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.hibernate.query.criteria.internal.CriteriaBuilderImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
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
        List<Page> pages = scripts.get(0).getPages();
        pages.forEach(logger::info);

        CriteriaBuilder builder = scriptDao.createBuilder();
        CriteriaQuery<Script> query = builder.createQuery(Script.class);
        Root<Script> root = query.from(Script.class);
        CriteriaQuery<Script> title = query.select(root).where(builder.like(root.get("title"), "%script 1%"));

        scriptDao.findFiltered(title).forEach(logger::info);
    }

    @Test
    public void saveTest(){
        Script script = new Script();
        script.setTitle("New script");
        script.setPath("/test/awd");
        scriptDao.create(script);
    }
}