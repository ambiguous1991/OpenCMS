package com.jba.opencms.dao;

import com.jba.opencms.BaseSpringIntegrationTest;
import com.jba.opencms.configuration.DaoConfiguration;
import com.jba.opencms.configuration.DataSourceConfig;
import com.jba.opencms.type.user.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {DataSourceConfig.class, DaoConfiguration.class})
public class UserDaoIntegrationTest extends BaseSpringIntegrationTest {

    @Autowired
    private GenericDao<User> userDao;

    @Test
    public void addUser(){
        User user = new User();
        user.setEmail("jakub.bartusiak@gmail.com");
        user.setFirstName("Jakub");
        user.setPassword("password");
        user.setSalt("AWD");
        user.setUsername("satanicus1991");
        userDao.create(user);
    }

    @Test
    public void findAll() {
        userDao.findAll().stream()
        .forEach(user -> logger.info(user));
    }

}