package com.jba.opencms.file;

import com.jba.opencms.base.AbstractService;
import com.jba.opencms.dao.GenericDao;
import com.jba.opencms.type.file.File;

public class FileServiceImpl extends AbstractService<File> implements FileService {
    public FileServiceImpl(GenericDao<File> dao) {
        super(dao);
    }
}