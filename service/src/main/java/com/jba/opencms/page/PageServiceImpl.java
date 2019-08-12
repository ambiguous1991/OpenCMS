package com.jba.opencms.page;

import com.jba.opencms.base.AbstractService;
import com.jba.opencms.dao.GenericDao;
import com.jba.opencms.type.page.Page;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Transactional
public class PageServiceImpl extends AbstractService<Page> implements PageService {

    public PageServiceImpl(GenericDao<Page> dao) {
        super(dao);
        logger = LoggerFactory.getLogger(getClass());
    }

    private CriteriaQuery<Page> createIdentifierQuery(String identifier){
        CriteriaBuilder builder = dao.createBuilder();
        CriteriaQuery<Page> query = builder.createQuery(Page.class);
        Root<Page> root = query.from(Page.class);

        return query.select(root)
                .where(builder.equal(root.get("identifier"), identifier));
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
    public Page findByIdentifier(String identifier) {
        return dao.findFiltered(createIdentifierQuery(identifier)).stream().findFirst().orElse(null);
    }

    @Override
    public boolean identifierAvailable(String identifier, Long id) {
        return dao.findFiltered(createIdentifierQuery(identifier)).stream().allMatch(el -> el.getId().equals(id));
    }
}
