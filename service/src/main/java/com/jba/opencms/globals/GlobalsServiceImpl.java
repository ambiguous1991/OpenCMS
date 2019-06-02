package com.jba.opencms.globals;

import com.jba.opencms.base.AbstractService;
import com.jba.opencms.dao.GenericDao;
import com.jba.opencms.dao.ifs.SystemVariableDao;
import com.jba.opencms.type.system.SystemVariable;

import java.util.Map;
import java.util.stream.Collectors;

public class GlobalsServiceImpl extends AbstractService<SystemVariable> implements GlobalsService{
    public GlobalsServiceImpl(GenericDao<SystemVariable> dao) {
        super(dao);
    }

    @Override
    public SystemVariable findByKey(String key) {
        return dao.findAll().stream()
                .filter(item->item.getKey().equals(key))
                .findFirst().orElse(null);
    }

    @Override
    public Map<String, String> getAsMap() {
        if (dao.getClass().isAssignableFrom(SystemVariableDao.class)){
            return ((SystemVariableDao)dao).getAsMap();
        }
        else return dao.findAll().stream()
                .collect(Collectors.toMap(SystemVariable::getKey, SystemVariable::getValue));
    }
}
