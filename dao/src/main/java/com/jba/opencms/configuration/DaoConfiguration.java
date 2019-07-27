package com.jba.opencms.configuration;

import com.jba.opencms.dao.*;
import com.jba.opencms.dao.ifs.EntryDao;
import com.jba.opencms.dao.ifs.MenuDao;
import com.jba.opencms.dao.ifs.SystemVariableDao;
import com.jba.opencms.type.file.File;
import com.jba.opencms.type.file.Script;
import com.jba.opencms.type.file.Stylesheet;
import com.jba.opencms.type.image.Image;
import com.jba.opencms.type.menu.Entry;
import com.jba.opencms.type.menu.Menu;
import com.jba.opencms.type.message.Message;
import com.jba.opencms.type.message.Status;
import com.jba.opencms.type.page.Page;
import com.jba.opencms.type.page.PageType;
import com.jba.opencms.type.user.Authority;
import com.jba.opencms.type.user.ImageUser;
import com.jba.opencms.type.user.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(DataSourceConfiguration.class)
public class DaoConfiguration {

    @Bean
    public UserDao userDao(SessionFactory sessionFactory){
        return new UserDao(User.class, sessionFactory);
    }

    @Bean
    public GenericDao<Page> pageDao(SessionFactory sessionFactory){
        return new HibernateDao<>(Page.class, sessionFactory);
    }

    @Bean
    public GenericDao<PageType> pageTypeDao(SessionFactory sessionFactory){
        return new HibernateDao<>(PageType.class, sessionFactory);
    }

    @Bean
    public GenericDao<Authority> authorityDao(SessionFactory sessionFactory){
        return new HibernateDao<>(Authority.class, sessionFactory);
    }

    @Bean
    public GenericDao<Message> messageDao(SessionFactory sessionFactory){
        return new HibernateDao<>(Message.class, sessionFactory);
    }

    @Bean
    public GenericDao<Status> statusDao(SessionFactory sessionFactory){
        return new HibernateDao<>(Status.class, sessionFactory);
    }

    @Bean
    public GenericDao<Image> imageDao(SessionFactory sessionFactory){
        return new HibernateDao<>(Image.class, sessionFactory);
    }

    @Bean
    public MenuDao menuDao(@Qualifier("sessionFactory") SessionFactory sessionFactory){
        return new MenuDaoImpl(Menu.class, sessionFactory);
    }

    @Bean
    public EntryDao entryDao(SessionFactory sessionFactory){
        return new EntryDaoImpl(Entry.class, sessionFactory);
    }

    @Bean
    public GenericDao<ImageUser> imageUserDao(SessionFactory sessionFactory){
        return new HibernateDao<>(ImageUser.class, sessionFactory);
    }

    @Bean
    public SystemVariableDao systemVariableDao(SessionFactory sessionFactory){
        return new SystemVariableDaoImpl(sessionFactory);
    }

    @Bean
    public GenericDao<File> fileDao(SessionFactory sessionFactory){
        return new HibernateDao<>(File.class, sessionFactory);
    }

    @Bean
    public GenericDao<Script> scriptDao(SessionFactory sessionFactory){
        return new HibernateDao<>(Script.class, sessionFactory);
    }

    @Bean
    public GenericDao<Stylesheet> stylesheetDao(SessionFactory sessionFactory){
        return new HibernateDao<>(Stylesheet.class, sessionFactory);
    }
}
