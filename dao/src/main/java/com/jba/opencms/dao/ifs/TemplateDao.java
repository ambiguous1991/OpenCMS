package com.jba.opencms.dao.ifs;

import com.jba.opencms.dao.GenericDao;
import com.jba.opencms.type.page.ThymeleafTemplate;

public interface TemplateDao extends GenericDao<ThymeleafTemplate> {
    ThymeleafTemplate byName(String name);
}
