package com.jba.opencms.page;

import com.jba.opencms.base.BaseService;
import com.jba.opencms.type.page.ThymeleafTemplate;

public interface TemplateService extends BaseService<ThymeleafTemplate> {
    ThymeleafTemplate byName(String name);
}
