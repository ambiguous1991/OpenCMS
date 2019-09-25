package com.jba.opencms.dao.ifs;

import com.jba.opencms.dao.GenericDao;
import com.jba.opencms.type.file.File;
import com.jba.opencms.type.file.projection.FileProjection;

import java.util.List;

public interface FileDao extends GenericDao<File> {
    File get(String path);
    List<String> list(String path);
    List<FileProjection> getImageProjections();
    List<FileProjection> getFileProjections();
}
