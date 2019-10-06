package com.jba.opencms.file;

import com.jba.opencms.base.AbstractService;
import com.jba.opencms.dao.GenericDao;
import com.jba.opencms.dao.ifs.FileDao;
import com.jba.opencms.type.file.File;
import com.jba.opencms.type.file.projection.FileProjection;

import java.util.List;

public class FileServiceImpl extends AbstractService<File> implements FileService {

    private FileDao dao;

    public FileServiceImpl(FileDao dao) {
        super(dao);
        this.dao=dao;
    }

    @Override
    public File get(String path) {
        return dao.get(path);
    }

    @Override
    public List<String> list(String path) throws IllegalArgumentException {
        if(!path.endsWith("/")){
            throw new IllegalArgumentException("Path "+path+" provided is not a directory!");
        }

        return dao.list(path);
    }

    @Override
    public boolean exists(String path) {
        return get(path) != null;
    }

    @Override
    public List<FileProjection> getImagesMetadata() {
        return dao.getImagesMetadata();
    }

    @Override
    public List<FileProjection> getFileMetadata() {
        return dao.getFileMetadata();
    }

    @Override
    public List<File> findAll(List<String> mimes) {
        return dao.findAll(mimes);
    }
}