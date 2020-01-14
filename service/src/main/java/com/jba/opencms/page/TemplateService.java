package com.jba.opencms.page;

import com.jba.opencms.base.BaseService;
import com.jba.opencms.type.page.Template;

public interface TemplateService extends BaseService<Template> {
    Template byName(String name);
}
