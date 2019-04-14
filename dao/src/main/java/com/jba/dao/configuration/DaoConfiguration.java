package com.jba.dao.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.logging.log4j.LogManager;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;

@Configuration
public class DaoConfiguration {

    protected Logger logger = LogManager.getLogger(getClass());

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DaoConfiguration.class);
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource){
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan("com.jba.dao");
        sessionFactory.setMappingResources("hibernate.cfg.xml");
        return sessionFactory;
    }

    @Bean
    public DataSource dataSource(Environment env){
        HikariConfig config = new HikariConfig();
        logger.info("JDBC URL "+env.getProperty("jdbc.url"));
        config.setJdbcUrl(env.getProperty("jdbc.url"));
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setUsername(env.getProperty("jdbc.username"));
        config.setPassword(env.getProperty("jdbc.password"));
        return new HikariDataSource(config);
    }

    @Bean
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory){
        HibernateTransactionManager manager = new HibernateTransactionManager();
        manager.setSessionFactory(sessionFactory);
        return manager;
    }
}
