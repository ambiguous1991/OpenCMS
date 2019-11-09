package com.jba.opencms.web.configuration;

import com.jba.opencms.page.TemplateService;
import com.jba.opencms.web.databasetemplate.DatabaseThymeleafTemplateResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.StringTemplateResolver;

import javax.annotation.PostConstruct;

@Configuration
@Slf4j
public class ThymeleafConfiguration {

    @Autowired SpringTemplateEngine springTemplateEngine;

    @Bean
    public StringTemplateResolver databaseThymeleafTemplateResolver(TemplateService templateService){
        return new DatabaseThymeleafTemplateResolver(templateService);
    }

    @PostConstruct
    public void test(){
        log.info(springTemplateEngine.toString());
    }
}
