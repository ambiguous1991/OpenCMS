package com.jba.opencms.web.configuration;

import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
public class FlywayConfiguration {
    @Bean
    @Profile("local")
    public Flyway flyway(DataSource dataSource) {
        Flyway flyway = Flyway.configure().dataSource(dataSource).validateOnMigrate(true).cleanOnValidationError(true).load();
        flyway.migrate();
        return flyway;
    }
}