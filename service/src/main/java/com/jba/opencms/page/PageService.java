package com.jba.opencms.page;

import com.jba.opencms.base.BaseService;
import com.jba.opencms.type.file.Script;
import com.jba.opencms.type.page.Page;

import java.util.List;

public interface PageService extends BaseService<Page> {
    void updateContents(Long pageId, String content);
    Page findByIdentifier(String identifier);
    boolean identifierAvailable(String identifier, Long id);
}
