package com.jba.opencms.dao;

import com.jba.opencms.dao.ifs.SystemVariableDao;
import com.jba.opencms.type.system.SystemVariable;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.stream.Collectors;

@Transactional
public class SystemVariableDaoImpl extends HibernateDao<SystemVariable> implements SystemVariableDao {

    public SystemVariableDaoImpl(SessionFactory sessionFactory) {
        super(SystemVariable.class, sessionFactory);
    }

    @Override
    public Map<String, String> getAsMap() {
        return findAll().stream()
                .collect(Collectors.toMap(SystemVariable::getKey, SystemVariable::getValue));
    }

    @Override
    public SystemVariable findByKey(String key) {
        return getCurrentSession()
                .createQuery("from SystemVariable s where s.key=:key", SystemVariable.class)
                .setParameter("key", key)
                .getSingleResult();
    }

    @Override
    public void update(Map<String, String> map) {
        map.forEach((key, value) -> {
            SystemVariable systemVariable = findByKey(key);
            systemVariable.setValue(value);
            update(systemVariable);
        });
    }
}
