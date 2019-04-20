package com.jba.opencms.dao.user;

import com.jba.jfiller.JFill;
import com.jba.opencms.BaseSpringIntegrationTest;
import com.jba.opencms.configuration.DaoConfiguration;
import com.jba.opencms.configuration.DataSourceConfig;
import com.jba.opencms.dao.GenericDao;
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
        JFill fill = JFill.instance();
        user.setEmail(fill.name().male().gen()+randomLong(1000)+"@"+fill.name().lastName().gen()+".pl");
        user.setFirstName(fill.name().male().gen());
        String password = "password";
        user.setSalt("AWD");
        user.setUsername(fill.name().male().gen()+randomLong(1000));
        userDao.create(user);
    }

    @Test
    public void findAll() {
        userDao.findAll().stream()
        .forEach(user -> logger.info(user));
    }

}