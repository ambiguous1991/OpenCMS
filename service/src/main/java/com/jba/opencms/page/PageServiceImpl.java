package com.jba.opencms.page;

import com.jba.opencms.base.AbstractService;
import com.jba.opencms.dao.GenericDao;
import com.jba.opencms.type.page.Page;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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

    @Override
    public boolean identifierAvailable(String identifier, Long id) {
        CriteriaBuilder builder = dao.createBuilder();
        CriteriaQuery<Page> query = builder.createQuery(Page.class);
        Root<Page> root = query.from(Page.class);

        CriteriaQuery<Page> identifierQuery =
                query.select(root)
                        .where(builder.equal(root.get("identifier"), identifier));

        return dao.findFiltered(identifierQuery).stream().allMatch(el -> el.getId().equals(id));
    }
}
