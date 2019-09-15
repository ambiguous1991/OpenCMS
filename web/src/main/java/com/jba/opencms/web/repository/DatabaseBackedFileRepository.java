package com.jba.opencms.web.repository;

import com.jba.opencms.file.FileFacadeService;
import com.jba.opencms.type.file.Script;
import com.jba.opencms.type.file.Stylesheet;
import com.jba.opencms.web.utils.ContentType;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.util.CollectionUtils;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DatabaseBackedFileRepository implements FileRepository {

    private FileFacadeService fileFacadeService;

    public DatabaseBackedFileRepository(FileFacadeService fileFacadeService) {
        this.fileFacadeService = fileFacadeService;
    }

    @Override
    public InputStream get(String path) throws FileNotFoundException, IllegalArgumentException {
        if(path.contains(".css")) {
            Stylesheet stylesheet = fileFacadeService.stylesheet().findOne(1L, false);
            return stringValueToInputStream(stylesheet.getValue());
        }
        else if(path.contains(".js")) {
            Script script = fileFacadeService.script().findOne(1L, false);
            return stringValueToInputStream(script.getValue());
        }
        throw new FileNotFoundException();
    }

    public InputStream stringValueToInputStream(String value){
        return new ByteArrayInputStream(value.getBytes());
    }

    @Override
    public void save(String path, InputStream input, FileAccessMode mode, String... contentTypes) throws IllegalArgumentException {
        List<String> content = Arrays.asList(contentTypes);
        if (content.contains(ContentType.TEXT_CSS)){
            Stylesheet stylesheet = new Stylesheet();
            stylesheet.setPath(path);
            stylesheet.setTitle(path);
            stylesheet.setValue(readInput(input));
            fileFacadeService.stylesheet().create(stylesheet);
        }
        else if (content.contains(ContentType.TEXT_JAVASCRIPT)){
            Script script = new Script();
            script.setPath(path);
            script.setTitle(path);
            script.setValue(readInput(input));
            fileFacadeService.script().create(script);
        }
        else throw new IllegalArgumentException("Provided filetype is illegal!");
    }

    private String readInput(InputStream inputStream){
        byte aByte = 0;
        List<Byte> bytesArray = new ArrayList<>();
        try {
            while ((aByte = (byte) inputStream.read())!=-1) {
                bytesArray.add(aByte);
            }
        }
        catch (IOException e){
            return null;
        }
        byte[] bytes = new byte[bytesArray.size()];
        for(int i=0; i<bytesArray.size(); i++){
            bytes[i]=bytesArray.get(i);
        }
        return new String(bytes);
    }

    @Override
    public void update(String path, InputStream input, FileAccessMode mode, String... contentTypes) throws FileNotFoundException, IllegalArgumentException {

    }

    @Override
    public boolean delete(String path) throws FileNotFoundException, IllegalArgumentException {
        return false;
    }

    @Override
    public List<String> list(String path) throws IllegalArgumentException {
        return null;
    }
}
