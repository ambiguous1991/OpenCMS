package com.jba.opencms.dao.ifs;

import com.jba.opencms.dao.GenericDao;
import com.jba.opencms.type.file.File;

import java.util.List;

public interface FileDao extends GenericDao<File> {
    File get(String path);
    List<String> list(String path);
}
