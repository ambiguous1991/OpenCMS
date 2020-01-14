package com.jba.opencms.configuration;

import com.jba.opencms.dao.GenericDao;
import com.jba.opencms.dao.ifs.*;
import com.jba.opencms.file.*;
import com.jba.opencms.file.preprocessor.FilePreprocessor;
import com.jba.opencms.file.preprocessor.ImagePreprocessor;
import com.jba.opencms.globals.GlobalsService;
import com.jba.opencms.globals.GlobalsServiceImpl;
import com.jba.opencms.menu.EntryService;
import com.jba.opencms.menu.EntryServiceImpl;
import com.jba.opencms.menu.MenuService;
import com.jba.opencms.menu.MenuServiceImpl;
import com.jba.opencms.page.*;
import com.jba.opencms.type.page.Page;
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

    @Bean
    public EntryService entryService(EntryDao entryDao){
        return new EntryServiceImpl(entryDao);
    }

    @Bean
    public PageService pageService(GenericDao<Page> pageDao){
        return new PageServiceImpl(pageDao);
    }

    @Bean
    public GlobalsService globalsService(SystemVariableDao systemVariableDao){
        return new GlobalsServiceImpl(systemVariableDao);
    }

    @Bean
    public FileService fileService(FileDao fileDao){
        return new FileServiceImpl(fileDao);
    }

    @Bean
    public FilePreprocessor imagePreprocessor(){
        return new ImagePreprocessor();
    }

    @Bean
    public TemplateService templateService(TemplateDao templateDao){
        return new TemplateServiceImpl(templateDao);
    }
}
