package com.jba.opencms.web.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import liquibase.integration.spring.SpringLiquibase;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

import static org.slf4j.LoggerFactory.getLogger;

@Configuration
public class LiquibaseConfiguration {

    private Logger logger = getLogger(LiquibaseConfiguration.class);

    @Bean
    public SpringLiquibase liquibase(@Qualifier("liquibaseDataSource") DataSource dataSource){
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog("classpath:liquibase-changelog.xml");
        liquibase.setDataSource(dataSource);
        liquibase.setDropFirst(false);
        liquibase.setShouldRun(false);
        return liquibase;
    }

    @Bean
    public DataSource liquibaseDataSource(Environment env){
        HikariConfig config = new HikariConfig();
        String url = "jdbc:mysql://localhost:3306/opencmsliquibase?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
        logger.info("Liquibase connection URL - "+url);
        config.setJdbcUrl(url);
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setUsername(env.getProperty("jdbc.username"));
        config.setPassword(env.getProperty("jdbc.password"));
        return new HikariDataSource(config);
    }
}
