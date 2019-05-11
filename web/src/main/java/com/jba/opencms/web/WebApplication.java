package com.jba.opencms.web;

import com.jba.opencms.configuration.DataSourceConfiguration;
import com.jba.opencms.configuration.ServicesInitializr;
import com.jba.opencms.web.interceptor.BreadcrumbInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication(exclude = {HibernateJpaAutoConfiguration.class, LiquibaseAutoConfiguration.class})
@ComponentScan("com.jba.opencms")
@Import({DataSourceConfiguration.class, ServicesInitializr.class})
public class WebApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new BreadcrumbInterceptor());
    }
}
