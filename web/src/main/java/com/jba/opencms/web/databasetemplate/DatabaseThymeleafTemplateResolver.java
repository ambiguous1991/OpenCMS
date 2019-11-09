package com.jba.opencms.web.databasetemplate;

import com.jba.opencms.page.TemplateService;
import com.jba.opencms.type.page.ThymeleafTemplate;
import lombok.extern.slf4j.Slf4j;
import org.thymeleaf.IEngineConfiguration;
import org.thymeleaf.templateresolver.StringTemplateResolver;
import org.thymeleaf.templateresource.ITemplateResource;

import java.util.HashSet;
import java.util.Map;

@Slf4j
public class DatabaseThymeleafTemplateResolver extends StringTemplateResolver {
    private final static String PREFIX = "";
    protected TemplateService templateService;

    public DatabaseThymeleafTemplateResolver(TemplateService templateService) {
        super();
        HashSet<String> resolvablePatterns = new HashSet<>();
        resolvablePatterns.add(PREFIX+"*");
        setResolvablePatterns(resolvablePatterns);
        this.templateService=templateService;
    }

    @Override
    protected ITemplateResource computeTemplateResource(IEngineConfiguration configuration, String ownerTemplate, String template, Map<String, Object> templateResolutionAttributes) {
        ThymeleafTemplate thymeleafTemplate = templateService.byName(template);
        if (thymeleafTemplate != null) {
            return super.computeTemplateResource(configuration, ownerTemplate, thymeleafTemplate.getContent(), templateResolutionAttributes);
        }
        log.debug("Template "+template+" not found in database. Looking for results in resources...");
        return null;
    }
}
