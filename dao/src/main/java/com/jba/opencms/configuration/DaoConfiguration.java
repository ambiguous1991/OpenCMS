package com.jba.opencms.configuration;

import com.jba.opencms.dao.GenericDao;
import com.jba.opencms.dao.HibernateDao;
import com.jba.opencms.dao.MenuDaoImpl;
import com.jba.opencms.dao.UserDao;
import com.jba.opencms.dao.ifs.MenuDao;
import com.jba.opencms.type.image.Image;
import com.jba.opencms.type.menu.Entry;
import com.jba.opencms.type.menu.Menu;
import com.jba.opencms.type.menu.MenuEntry;
import com.jba.opencms.type.message.Message;
import com.jba.opencms.type.message.Status;
import com.jba.opencms.type.page.Page;
import com.jba.opencms.type.user.Authority;
import com.jba.opencms.type.user.ImageUser;
import com.jba.opencms.type.user.User;
import org.hibernate.SessionFactory;
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
    public MenuDao menuDao(SessionFactory sessionFactory){
        return new MenuDaoImpl(Menu.class, sessionFactory);
    }

    @Bean
    public GenericDao<MenuEntry> menuEntryDao(SessionFactory sessionFactory){
        return new HibernateDao<>(MenuEntry.class, sessionFactory);
    }

    @Bean
    public GenericDao<Entry> entryDao(SessionFactory sessionFactory){
        return new HibernateDao<>(Entry.class, sessionFactory);
    }

    @Bean
    public GenericDao<ImageUser> imageUserDao(SessionFactory sessionFactory){
        return new HibernateDao<>(ImageUser.class, sessionFactory);
    }
}
