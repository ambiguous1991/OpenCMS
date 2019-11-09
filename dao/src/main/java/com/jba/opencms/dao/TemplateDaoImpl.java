package com.jba.opencms.dao;

import com.jba.opencms.dao.ifs.TemplateDao;
import com.jba.opencms.type.page.ThymeleafTemplate;
import org.hibernate.NonUniqueResultException;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class TemplateDaoImpl extends HibernateDao<ThymeleafTemplate> implements TemplateDao {

    public TemplateDaoImpl(SessionFactory sessionFactory) {
        super(ThymeleafTemplate.class, sessionFactory);
    }

    @Override
    @Transactional
    public ThymeleafTemplate byName(String name) {
        List<ThymeleafTemplate> results = sessionFactory.getCurrentSession()
                .createQuery("from " + clazz.getName() + " where name=:name", ThymeleafTemplate.class)
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
