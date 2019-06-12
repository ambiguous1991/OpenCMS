package com.jba.opencms.web.configuration;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
public class LiquibaseConfiguration {

    @Bean
    @Profile("local")
    public SpringLiquibase liquibaseLocal(DataSource dataSource){
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog("classpath:liquibase-changelog.xml");
        liquibase.setDataSource(dataSource);
        liquibase.setDropFirst(true);
        liquibase.setShouldRun(true);
        return liquibase;
    }

    @Bean
    @Profile("aws")
    public SpringLiquibase liquibaseAws(DataSource dataSource){
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog("classpath:liquibase-changelog.xml");
        liquibase.setDataSource(dataSource);
        liquibase.setDropFirst(false);
        liquibase.setShouldRun(true);
        return liquibase;
    }

}
