package com.jba.opencms.dao.ifs;

import com.jba.opencms.dao.GenericDao;
import com.jba.opencms.type.system.SystemVariable;

import java.util.Map;

public interface SystemVariableDao extends GenericDao<SystemVariable> {
    Map<String, String> getAsMap();
    void update(Map<String, String> map);
    SystemVariable findByKey(String key);
}
