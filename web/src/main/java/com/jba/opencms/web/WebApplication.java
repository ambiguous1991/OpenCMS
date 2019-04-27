package com.jba.opencms.web;

import com.jba.opencms.configuration.DaoConfiguration;
import com.jba.opencms.configuration.DataSourceConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication(exclude = {HibernateJpaAutoConfiguration.class})
@ComponentScan("com.jba.opencms")
@Import({DataSourceConfiguration.class, DaoConfiguration.class})
public class WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }

}
