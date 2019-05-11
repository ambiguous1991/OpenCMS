package com.jba.opencms.base;

import com.jba.opencms.configuration.DaoConfiguration;
import com.jba.opencms.configuration.ServicesInitializr;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.test.context.ContextConfiguration;


@ContextConfiguration(classes = {DaoConfiguration.class, ServiceDataSourceTestConfig.class, ServicesInitializr.class})
public class ServiceIntegrationTest {
    protected Logger logger = LogManager.getLogger(getClass());
}
