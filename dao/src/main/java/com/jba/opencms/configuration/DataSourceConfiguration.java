package com.jba.opencms.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.logging.log4j.LogManager;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.apache.logging.log4j.Logger;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.yaml.snakeyaml.Yaml;

import javax.sql.DataSource;
import java.io.InputStream;
import java.util.Map;

@Configuration
@EnableTransactionManagement
public class DataSourceConfiguration {

    protected Logger logger = LogManager.getLogger(getClass());

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
        logger.info("JDBC URL "+env.getProperty("spring.datasource.url"));
        config.setJdbcUrl(env.getProperty("spring.datasource.url"));
        config.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
        config.setUsername(env.getProperty("spring.datasource.username"));
        config.setPassword(env.getProperty("spring.datasource.password"));
        return new HikariDataSource(config);
    }

    @Bean
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory){
        HibernateTransactionManager manager = new HibernateTransactionManager();
        manager.setSessionFactory(sessionFactory);
        return manager;
    }
}
