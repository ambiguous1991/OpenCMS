package com.jba.opencms.configuration;

import com.jba.opencms.dao.GenericDao;
import com.jba.opencms.dao.ifs.EntryDao;
import com.jba.opencms.dao.ifs.MenuDao;
import com.jba.opencms.dao.ifs.SystemVariableDao;
import com.jba.opencms.file.*;
import com.jba.opencms.globals.GlobalsService;
import com.jba.opencms.globals.GlobalsServiceImpl;
import com.jba.opencms.image.ImageService;
import com.jba.opencms.image.ImageServiceImpl;
import com.jba.opencms.menu.EntryService;
import com.jba.opencms.menu.EntryServiceImpl;
import com.jba.opencms.menu.MenuService;
import com.jba.opencms.menu.MenuServiceImpl;
import com.jba.opencms.page.PageService;
import com.jba.opencms.page.PageServiceImpl;
import com.jba.opencms.page.PageTypeService;
import com.jba.opencms.page.PageTypeServiceImpl;
import com.jba.opencms.type.file.File;
import com.jba.opencms.type.file.Script;
import com.jba.opencms.type.file.Stylesheet;
import com.jba.opencms.type.image.Image;
import com.jba.opencms.type.page.Page;
import com.jba.opencms.type.page.PageType;
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
    public PageTypeService pageTypeService(GenericDao<PageType> pageTypeDao) {
        return new PageTypeServiceImpl(pageTypeDao);
    }

    @Bean
    public ImageService imageService(GenericDao<Image> imageDao){
        return new ImageServiceImpl(imageDao);
    }

    @Bean
    public GlobalsService globalsService(SystemVariableDao systemVariableDao){
        return new GlobalsServiceImpl(systemVariableDao);
    }

    @Bean
    public FileService fileService(GenericDao<File> fileDao){
        return new FileServiceImpl(fileDao);
    }

    @Bean
    public StylesheetService stylesheetService(GenericDao<Stylesheet> stylesheetDao){
        return new StylesheetServiceImpl(stylesheetDao);
    }

    @Bean
    public ScriptService scriptService(GenericDao<Script>scriptDao){
        return new ScriptServiceImpl(scriptDao);
    }

    @Bean
    public FileFacadeService fileFacadeService(FileService fileService, StylesheetService stylesheetService, ScriptService scriptService){
        return new FileFacadeServiceImpl(scriptService, stylesheetService, fileService);
    }
}
