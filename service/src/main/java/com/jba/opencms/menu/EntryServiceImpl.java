package com.jba.opencms.menu;

import com.jba.opencms.base.AbstractService;
import com.jba.opencms.dao.GenericDao;
import com.jba.opencms.type.menu.Entry;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class EntryServiceImpl extends AbstractService<Entry> implements EntryService{

    public EntryServiceImpl(GenericDao<Entry> dao) {
        super(dao);
    }

}
