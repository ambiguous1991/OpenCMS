package com.jba.opencms;

import com.jba.opencms.configuration.DaoConfiguration;
import com.jba.opencms.configuration.TestDatasourceConfiguration;
import com.jba.jfiller.JFill;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Random;


@ContextConfiguration(classes = {TestDatasourceConfiguration.class, DaoConfiguration.class})
@EnableTransactionManagement
public abstract class BaseSpringIntegrationTest {
    protected Logger logger = LogManager.getLogger(getClass());
    protected Random random = new Random();
    protected JFill jFill = JFill.instance();

    protected long randomLong(){
        return randomLong(100);
    }

    protected long randomLong(int bound){
        return Integer.toUnsignedLong(random.nextInt(bound));
    }

}
