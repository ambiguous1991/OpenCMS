package com.jba.opencms.file;

import com.jba.opencms.base.AbstractService;
import com.jba.opencms.dao.GenericDao;
import com.jba.opencms.type.file.Script;

public class ScriptServiceImpl extends AbstractService<Script> implements ScriptService  {
    public ScriptServiceImpl(GenericDao<Script> dao) {
        super(dao);
    }
}
