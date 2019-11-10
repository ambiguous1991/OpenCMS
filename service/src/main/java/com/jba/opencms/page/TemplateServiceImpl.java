package com.jba.opencms.page;

import com.jba.opencms.base.AbstractService;
import com.jba.opencms.dao.ifs.TemplateDao;
import com.jba.opencms.type.page.Template;

import javax.persistence.NoResultException;

public class TemplateServiceImpl extends AbstractService<Template> implements TemplateService {

    public TemplateServiceImpl(TemplateDao dao) {
        super(dao);
    }

    @Override
    public Template byName(String name) {
        try {
            return ((TemplateDao) dao).byName(name);
        }
        catch (NoResultException e){
            return null;
        }
    }
}
