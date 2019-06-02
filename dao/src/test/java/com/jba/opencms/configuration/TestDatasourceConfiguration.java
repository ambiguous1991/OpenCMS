package com.jba.opencms.configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ContextConfiguration(classes = {DataSourceConfiguration.class, DaoConfiguration.class})
@TestPropertySource(properties = "classpath:application-test.properties")
@PropertySource("classpath:application-test.properties")
@EnableTransactionManagement
public class TestDatasourceConfiguration {
    protected Logger logger = LogManager.getLogger(getClass());
}
