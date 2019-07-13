package com.jba.opencms.web.repository;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class MockFileRepository implements FileRepository {

    public MockFileRepository(){

    }

    @Override
    public InputStream get(String path) throws FileNotFoundException, IllegalArgumentException {
        return new ByteArrayInputStream ("*{\n\tdisplay: none\n}".getBytes());
    }

    @Override
    public void save(String path, InputStream input, FileAccessMode mode) throws IllegalArgumentException {

    }

    @Override
    public void update(String path, InputStream input, FileAccessMode mode) throws FileNotFoundException, IllegalArgumentException {

    }

    @Override
    public boolean delete(String path) throws FileNotFoundException, IllegalArgumentException {
        return true;
    }

    @Override
    public List<String> list(String path) throws IllegalArgumentException {
        return Arrays.asList("resources/css/test.css");
    }
}
