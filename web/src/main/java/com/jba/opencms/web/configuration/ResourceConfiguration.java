package com.jba.opencms.web.configuration;

import com.jba.opencms.file.FileFacadeService;
import com.jba.opencms.file.FileService;
import com.jba.opencms.type.file.File;
import com.jba.opencms.type.file.Stylesheet;
import com.jba.opencms.web.message.AbstractConverter;
import com.jba.opencms.web.message.RequestBodyToScriptResourceConverter;
import com.jba.opencms.web.message.RequestBodyToStylesheetResourceConverter;
import com.jba.opencms.web.type.resource.ScriptToResourceWrapperConverter;
import com.jba.opencms.web.type.resource.StylesheetToResourceWrapperConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ResourceConfiguration {

    @Bean
    public AbstractConverter<File> scriptAcceptedConverter(FileService fileService){
        return new RequestBodyToScriptResourceConverter(fileService, "pageResources", "_pageResources");
    }

    @Bean
    public AbstractConverter<File> scriptRejectedConverter(FileService fileService){
        return new RequestBodyToScriptResourceConverter(fileService, "availableResources", "_availableResources");
    }

    @Bean
    public AbstractConverter<Stylesheet> stylesheetAcceptedConverter(FileFacadeService fileFacadeService){
        return new RequestBodyToStylesheetResourceConverter(fileFacadeService, "pageResources", "_pageResources");
    }

    @Bean
    public AbstractConverter<Stylesheet> stylesheetRejectedConverter(FileFacadeService fileFacadeService){
        return new RequestBodyToStylesheetResourceConverter(fileFacadeService, "availableResources", "_availableResources");
    }

    @Bean
    public ScriptToResourceWrapperConverter scriptToResourceWrapperConverter(){
        return new ScriptToResourceWrapperConverter();
    }

    @Bean
    public StylesheetToResourceWrapperConverter stylesheetToResourceWrapperConverter(){
        return new StylesheetToResourceWrapperConverter();
    }
}
