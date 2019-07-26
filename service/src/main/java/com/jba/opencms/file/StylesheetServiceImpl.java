package com.jba.opencms.file;

import com.jba.opencms.base.AbstractService;
import com.jba.opencms.dao.GenericDao;
import com.jba.opencms.type.file.Stylesheet;

public class StylesheetServiceImpl extends AbstractService<Stylesheet> implements StylesheetService {
    public StylesheetServiceImpl(GenericDao<Stylesheet> dao) {
        super(dao);
    }
}
