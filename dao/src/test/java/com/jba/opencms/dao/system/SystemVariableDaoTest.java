package com.jba.opencms.dao.system;

import com.jba.opencms.BaseSpringIntegrationTest;
import com.jba.opencms.configuration.DaoConfiguration;
import com.jba.opencms.configuration.DataSourceConfig;
import com.jba.opencms.dao.ifs.SystemVariableDao;
import com.jba.opencms.type.system.SystemVariable;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.PersistenceException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {DataSourceConfig.class, DaoConfiguration.class})
@EnableTransactionManagement
public class SystemVariableDaoTest extends BaseSpringIntegrationTest {

    @Autowired
    private SystemVariableDao systemVariableDao;

    @Test(expected = PersistenceException.class)
    public void shouldPreventFromAddingDuplicates(){
        SystemVariable systemVariable = new SystemVariable();
        systemVariable.setKey("page.name");
        systemVariable.setValue("OpenCMS");
        systemVariableDao.create(systemVariable);
    }

    @Test
    public void addNew(){
        logger.info("Not adding anything atm");
    }

    @Test
    public void findAll(){
        Map<String, String> asMap = systemVariableDao.getAsMap();
        asMap.forEach((s, s2) -> logger.info(s+":"+s2));
    }
}