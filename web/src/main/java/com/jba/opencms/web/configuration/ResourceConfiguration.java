package com.jba.opencms.web.configuration;

import com.jba.opencms.file.FileFacadeService;
import com.jba.opencms.type.file.Script;
import com.jba.opencms.web.message.AbstractConverter;
import com.jba.opencms.web.message.RequestBodyToScriptResourceConverter;
import com.jba.opencms.web.type.resource.ScriptToResourceWrapperConverter;
import com.jba.opencms.web.type.resource.StylesheetToResourceWrapperConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ResourceConfiguration {

    @Bean
    public AbstractConverter<Script> scriptAcceptedConverter(FileFacadeService fileFacadeService){
        return new RequestBodyToScriptResourceConverter(fileFacadeService, "pageResources", "_pageResources");
    }

    @Bean
    public AbstractConverter<Script> scriptRejectedConverter(FileFacadeService fileFacadeService){
        return new RequestBodyToScriptResourceConverter(fileFacadeService, "availableResources", "_availableResources");
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
