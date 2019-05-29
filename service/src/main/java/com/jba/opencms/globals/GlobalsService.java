package com.jba.opencms.globals;

import com.jba.opencms.base.BaseService;
import com.jba.opencms.type.system.SystemVariable;

import java.util.Map;

public interface GlobalsService extends BaseService<SystemVariable> {
    SystemVariable findByKey(String key);
    Map<String, String> getAsMap();
}
