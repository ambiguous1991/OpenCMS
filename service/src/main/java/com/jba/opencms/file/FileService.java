package com.jba.opencms.file;

import com.jba.opencms.base.BaseService;
import com.jba.opencms.type.file.File;

import java.util.List;

public interface FileService extends BaseService<File> {
    File get(String path);
    List<String> list(String path) throws IllegalArgumentException;
    boolean exists(String path);
}
