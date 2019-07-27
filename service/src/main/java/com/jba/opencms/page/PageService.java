package com.jba.opencms.page;

import com.jba.opencms.base.BaseService;
import com.jba.opencms.type.page.Page;

public interface PageService extends BaseService<Page> {
    void updateContents(Long pageId, String content);
    boolean identifierAvailable(String identifier, Long id);
}
