package com.jba.opencms.web.configuration;

import com.jba.opencms.globals.GlobalsService;
import com.jba.opencms.type.system.enu.SystemVariableKeys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class SystemVariableInitializr {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Bean
    public Map<String, String> systemVariables(GlobalsService systemVariableDao){

        Map<String, String> systemVariables = systemVariableDao.getAsMap();

        logger.info("Initializing system variables...");

        systemVariables.forEach((key, value)->{
            logger.info(key+":"+value);
        });

        return systemVariables;
    }

    @Bean
    public SystemVariableKeys varkey(){
        return new SystemVariableKeys();
    }
}
