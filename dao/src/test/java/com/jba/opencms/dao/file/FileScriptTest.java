package com.jba.opencms.dao.file;

import com.jba.opencms.BaseSpringIntegrationTest;
import com.jba.opencms.configuration.DaoConfiguration;
import com.jba.opencms.configuration.TestDatasourceConfiguration;
import com.jba.opencms.dao.GenericDao;
import com.jba.opencms.type.file.File;
import com.jba.opencms.type.page.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestDatasourceConfiguration.class, DaoConfiguration.class})
@EnableTransactionManagement
public class FileScriptTest extends BaseSpringIntegrationTest {

    @Autowired
    private GenericDao<File> fileDao;

    @Test
    @Transactional
    public void getTest(){
        List<File> scripts = fileDao.findAll();
        scripts.forEach(logger::info);
        List<Page> pages = scripts.get(0).getPages();
        pages.forEach(logger::info);

        CriteriaBuilder builder = fileDao.createBuilder();
        CriteriaQuery<File> query = builder.createQuery(File.class);
        Root<File> root = query.from(File.class);
        CriteriaQuery<File> title = query.select(root).where(builder.like(root.get("name"), "%script 1%"));

        fileDao.findFiltered(title).forEach(logger::info);
    }

    @Test
    public void saveTest(){
        File script = new File();
        script.setName("New script");
        script.setPath("/test/awd");
        script.setData(new byte[0]);
        fileDao.create(script);
    }
}