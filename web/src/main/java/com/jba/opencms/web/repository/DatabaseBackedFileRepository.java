package com.jba.opencms.web.repository;

import com.amazonaws.util.IOUtils;
import com.jba.opencms.file.FileService;
import com.jba.opencms.type.file.File;
import com.jba.opencms.type.file.Script;
import com.jba.opencms.type.file.Stylesheet;
import com.jba.opencms.web.utils.ContentType;
import lombok.extern.log4j.Log4j2;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Log4j2
public class DatabaseBackedFileRepository implements FileRepository {

    private FileService fileService;

    public DatabaseBackedFileRepository(FileService fileService) {
        this.fileService = fileService;
    }

    private File getFileOrThrow(String path) throws FileNotFoundException{
        File file = fileService.get(path);
        if(file==null) throw new FileNotFoundException("File "+path+" not found");
        return file;
    }

    private byte[] readInput(InputStream inputStream) throws IOException{
        return IOUtils.toByteArray(inputStream);
    }

    @Override
    public InputStream get(String path) throws FileNotFoundException, IllegalArgumentException {
        File fileToBeServed = getFileOrThrow(path);
        return new ByteArrayInputStream(fileToBeServed.getData());
    }

    @Override
    public void save(String path, InputStream input, FileAccessMode mode, String... contentTypes) throws IllegalArgumentException {
        File file = new File();
        if(contentTypes!=null) {
            file.setMime(contentTypes[0]);
        }
        else file.setMime("text");
        file.setName(path);
        file.setPath(path);
        try {
            file.setData(readInput(input));
            fileService.create(file);
        }
        catch (IOException e){
            log.error("Errror reading input for file "+path, e);
        }
    }

    @Override
    public void update(String path, InputStream input, FileAccessMode mode, String... contentTypes) throws FileNotFoundException, IllegalArgumentException {
        File file = getFileOrThrow(path);
        try {
            file.setData(readInput(input));
            fileService.update(file);
        }
        catch (IOException e){
            log.error("Errror reading input for file "+path, e);
        }
    }

    @Override
    public boolean delete(String path) throws FileNotFoundException, IllegalArgumentException {
        File file = getFileOrThrow(path);
        fileService.delete(file.getId());
        return true;
    }

    @Override
    public List<String> list(String path) throws IllegalArgumentException {
        return fileService.list(path);
    }
}
