package com.jba.opencms.web.configuration;

import com.jba.opencms.file.FileFacadeService;
import com.jba.opencms.type.file.Script;
import com.jba.opencms.web.message.AbstractConverter;
import com.jba.opencms.web.message.RequestBodyToScriptResourceConverter;
import com.jba.opencms.web.type.resource.ScriptToResourceWrapperConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ResourceConvertersConfiguration {

    @Bean
    public AbstractConverter<Script> scriptConverter(FileFacadeService fileFacadeService){
        return new RequestBodyToScriptResourceConverter("page_sources", fileFacadeService);
    }

    @Bean
    public ScriptToResourceWrapperConverter scriptToResourceWrapperConverter(){
        return new ScriptToResourceWrapperConverter();
    }

}
