package com.jba.opencms.dao;

import com.jba.opencms.dao.ifs.TemplateDao;
import com.jba.opencms.type.page.ThymeleafTemplate;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class TemplateDaoImpl extends HibernateDao<ThymeleafTemplate> implements TemplateDao {

    public TemplateDaoImpl(SessionFactory sessionFactory) {
        super(ThymeleafTemplate.class, sessionFactory);
    }

    @Override
    public ThymeleafTemplate byName(String name) {
        ThymeleafTemplate singleResult = sessionFactory.getCurrentSession()
                .createQuery("from " + clazz.getName() + " where name=:name", ThymeleafTemplate.class)
                .getSingleResult();
        return singleResult;
    }
}
