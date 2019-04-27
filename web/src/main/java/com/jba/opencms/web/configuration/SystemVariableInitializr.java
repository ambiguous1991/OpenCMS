package com.jba.opencms.web.configuration;

import com.jba.opencms.dao.ifs.SystemVariableDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.Map;

@Configuration
public class SystemVariableInitializr {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public Map<String, String> systemVariables(SystemVariableDao systemVariableDao){

        Map<String, String> systemVariables = systemVariableDao.getAsMap();

        logger.info("Initializing system variables...");

        systemVariables.forEach((key, value)->{
            logger.info(key+":"+value);
        });

        return systemVariables;
    }
}
