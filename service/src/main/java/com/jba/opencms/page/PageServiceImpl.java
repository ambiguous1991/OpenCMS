package com.jba.opencms.page;

import com.jba.opencms.base.AbstractService;
import com.jba.opencms.dao.GenericDao;
import com.jba.opencms.type.page.Page;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class PageServiceImpl extends AbstractService<Page> implements PageService {

    public PageServiceImpl(GenericDao<Page> dao) {
        super(dao);
        logger = LoggerFactory.getLogger(getClass());
    }

    @Override
    public void updateContents(Long pageId, String content) {
        logger.info("Updating contents of page: "+pageId);
        try {
            Page pageToUpdate = findOne(pageId, true);
            pageToUpdate.setContent(content);
            update(pageToUpdate);
            logger.info("Page update successful!");
        }
        catch (Exception e){
            logger.error("Error during page update", e);
        }
    }
}
