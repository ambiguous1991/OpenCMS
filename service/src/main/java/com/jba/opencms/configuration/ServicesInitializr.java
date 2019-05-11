package com.jba.opencms.configuration;

import com.jba.opencms.dao.ifs.EntryDao;
import com.jba.opencms.dao.ifs.MenuDao;
import com.jba.opencms.menu.MenuService;
import com.jba.opencms.menu.MenuServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@Import(DaoConfiguration.class)
@EnableTransactionManagement
public class ServicesInitializr {

    @Bean
    public MenuService menuService(MenuDao menuDao, EntryDao entryDao){
        return new MenuServiceImpl(menuDao, entryDao);
    }

}
