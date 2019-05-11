package com.jba.opencms.page;

import com.jba.opencms.base.AbstractService;
import com.jba.opencms.dao.GenericDao;
import com.jba.opencms.type.page.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class PageServiceImpl extends AbstractService<Page> implements PageService {

    public PageServiceImpl(GenericDao<Page> dao) {
        super(dao);
    }
}
