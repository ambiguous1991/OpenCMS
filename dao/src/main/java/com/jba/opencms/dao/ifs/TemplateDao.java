package com.jba.opencms.dao.ifs;

import com.jba.opencms.dao.GenericDao;
import com.jba.opencms.type.page.Template;

public interface TemplateDao extends GenericDao<Template> {
    Template byName(String name);
}
