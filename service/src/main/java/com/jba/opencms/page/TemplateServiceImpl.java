package com.jba.opencms.page;

import com.jba.opencms.base.AbstractService;
import com.jba.opencms.dao.ifs.TemplateDao;
import com.jba.opencms.type.page.ThymeleafTemplate;

import javax.persistence.NoResultException;

public class TemplateServiceImpl extends AbstractService<ThymeleafTemplate> implements TemplateService {

    public TemplateServiceImpl(TemplateDao dao) {
        super(dao);
    }

    @Override
    public ThymeleafTemplate byName(String name) {
        try {
            return ((TemplateDao) dao).byName(name);
        }
        catch (NoResultException e){
            return null;
        }
    }
}
