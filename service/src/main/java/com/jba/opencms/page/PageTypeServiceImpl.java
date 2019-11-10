package com.jba.opencms.page;

import com.jba.opencms.base.AbstractService;
import com.jba.opencms.dao.GenericDao;

public class PageTypeServiceImpl extends AbstractService<PageType> implements PageTypeService {
    public PageTypeServiceImpl(GenericDao<PageType> dao) {
        super(dao);
    }
}
