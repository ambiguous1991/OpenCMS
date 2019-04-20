package com.jba.opencms.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@ComponentScan("com.jba.opencms")
@PropertySource("classpath:application.properties")
@EnableTransactionManagement
public class DataSourceConfig {
    protected Logger logger = LogManager.getLogger(getClass());

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DataSourceConfiguration.class);
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource){
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan("com.jba.opencms");
        sessionFactory.setMappingResources("hibernate.cfg.xml");
        return sessionFactory;
    }

    @Bean
    public DataSource dataSource(Environment env){
        HikariConfig config = new HikariConfig();
        logger.info("JDBC URL "+env.getProperty("jdbc.test.url"));
        config.setJdbcUrl(env.getProperty("jdbc.test.url"));
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
