package com.jba.opencms.menu;

import com.jba.opencms.base.ServiceIntegrationTest;
import com.jba.opencms.type.menu.Menu;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
public class MenuServiceTest extends ServiceIntegrationTest {

    @Autowired
    private MenuService menuService;

    @Test
    public void test(){
        List<Menu> all = menuService.findAll(true);
        all.forEach(e->{
            logger.info(e);
            e.getEntries().forEach(entry -> logger.info(entry));
        });
    }


    @Test
    public void active(){
        Menu active = menuService.findActive(true);
        if(active!=null){
            active.getEntries().forEach(e->logger.info(e));
        }

    }
}