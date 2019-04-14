package com.jba.opencms.configuration;

import com.jba.opencms.dao.GenericDao;
import com.jba.opencms.dao.HibernateDao;
import com.jba.opencms.type.user.User;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(DataSourceConfiguration.class)
public class DaoConfiguration {

    @Bean
    public GenericDao<User> userDao(SessionFactory sessionFactory){
        return new HibernateDao<>(User.class, sessionFactory);
    }
}
