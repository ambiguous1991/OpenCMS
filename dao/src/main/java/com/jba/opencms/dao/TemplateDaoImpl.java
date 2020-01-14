package com.jba.opencms.dao;

import com.jba.opencms.dao.ifs.TemplateDao;
import com.jba.opencms.type.page.Template;
import org.hibernate.NonUniqueResultException;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class TemplateDaoImpl extends HibernateDao<Template> implements TemplateDao {

    public TemplateDaoImpl(SessionFactory sessionFactory) {
        super(Template.class, sessionFactory);
    }

    @Override
    @Transactional
    public Template byName(String name) {
        List<Template> results = sessionFactory.getCurrentSession()
                .createQuery("from " + clazz.getName() + " where layoutName=:name", Template.class)
                .setParameter("name", name)
                .getResultList();
        if(results.size()==1){
            return results.get(0);
        }
        else if (results.size()>1){
            throw new NonUniqueResultException(results.size());
        }
        else return null;
    }
}
